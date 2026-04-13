#!/usr/bin/env python3
"""
organize_leetcode.py

Reads the raw output of leetcode-export, fetches topic tags from LeetCode's
GraphQL API, organizes solutions into topic/difficulty folders, and generates
README files at each level.

Usage:
    python organize_leetcode.py \
        --raw-dir ./leetcode-export-raw \
        --out-dir ./leetcode-solutions \
        --cookies "LEETCODE_SESSION=...; csrftoken=..." \
        [--skip-fetch]   # reuse existing metadata_cache.json

Directory layout produced:
    leetcode-solutions/
        README.md
        Dynamic_Programming/
            README.md
            Easy/
                0070_climbing-stairs.java
            Medium/
                0198_house-robber.java
            Hard/
                0174_dungeon-game.java
        Arrays/
            README.md
            Easy/  Medium/  Hard/
        ...
"""

import argparse
import json
import os
import re
import shutil
import sys
import time
from collections import Counter, defaultdict
from pathlib import Path
from typing import Optional

import requests

# ---------------------------------------------------------------------------
# Topic → Tag mapping  (priority-ordered: first match wins)
# ---------------------------------------------------------------------------
TOPIC_TAG_MAP = [
    ("Dynamic_Programming",  ["Dynamic Programming"]),
    ("Backtracking",         ["Backtracking"]),
    ("Binary_Search",        ["Binary Search"]),
    ("Sliding_Window",       ["Sliding Window"]),
    ("Two_Pointers",         ["Two Pointers"]),
    ("Graphs",               ["Graph", "BFS", "DFS",
                               "Breadth-First Search", "Depth-First Search"]),
    ("Trees",                ["Tree", "Binary Tree", "Binary Search Tree"]),
    ("Linked_Lists",         ["Linked List"]),
    ("Stack_Queue",          ["Stack", "Queue", "Monotonic Stack"]),
    ("Hashing",              ["Hash Table", "Hash Map"]),
    ("Heap",                 ["Heap (Priority Queue)"]),
    ("Bit_Manipulation",     ["Bit Manipulation"]),
    ("Math",                 ["Math", "Number Theory"]),
    ("Strings",              ["String"]),
    ("Arrays",               ["Array"]),
    ("Sorting",              ["Sorting"]),
    ("Greedy",               ["Greedy"]),
    ("Other",                []),   # catch-all — always last
]

DIFFICULTY_ORDER = ["Easy", "Medium", "Hard", "Unknown"]

DIFFICULTY_EMOJI = {"Easy": "🟢", "Medium": "🟡", "Hard": "🔴", "Unknown": "⚪"}

LEETCODE_GRAPHQL = "https://leetcode.com/graphql/"

PROBLEM_LIST_QUERY = """
query problemsetQuestionList(
  $categorySlug: String
  $limit: Int
  $skip: Int
  $filters: QuestionListFilterInput
) {
  problemsetQuestionList: questionList(
    categorySlug: $categorySlug
    limit: $limit
    skip: $skip
    filters: $filters
  ) {
    total: totalNum
    questions: data {
      frontendQuestionId: questionFrontendId
      title
      titleSlug
      difficulty
      topicTags { name slug }
    }
  }
}
"""

SINGLE_QUESTION_QUERY = """
query getQuestionDetail($titleSlug: String!) {
  question(titleSlug: $titleSlug) {
    questionId
    title
    titleSlug
    difficulty
    topicTags { name slug }
  }
}
"""

TOPIC_DESCRIPTIONS = {
    "Dynamic_Programming": "Classic DP patterns: memoization, tabulation, state machines.",
    "Arrays":              "Array manipulation, prefix sums, and in-place techniques.",
    "Trees":               "Binary trees, BSTs, traversals (inorder, preorder, postorder, level-order).",
    "Graphs":              "Graph traversal: BFS, DFS, topological sort, union-find.",
    "Binary_Search":       "Binary search on sorted arrays and on the answer space.",
    "Sliding_Window":      "Fixed and variable-size sliding window patterns.",
    "Two_Pointers":        "Two-pointer techniques for sorted arrays and partitioning.",
    "Backtracking":        "Combinatorial search: permutations, subsets, N-Queens.",
    "Linked_Lists":        "Singly/doubly linked lists, fast & slow pointer techniques.",
    "Stack_Queue":         "Stack, queue, monotonic stack, and deque patterns.",
    "Hashing":             "Hash maps and hash sets for O(1) lookup problems.",
    "Math":                "Number theory, primes, modular arithmetic.",
    "Strings":             "String manipulation, pattern matching, anagram detection.",
    "Greedy":              "Locally optimal choices that yield globally optimal results.",
    "Bit_Manipulation":    "Bitwise operations, XOR tricks, power-of-two checks.",
    "Heap":                "Min/max heaps, priority queues, K-th element problems.",
    "Sorting":             "Sorting algorithms and sort-based problem solving.",
    "Other":               "Miscellaneous problems that don't fit a single primary category.",
}


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def parse_folder_name(name: str) -> tuple[Optional[str], Optional[str]]:
    """Extract (question_id, title_slug) from '70-climbing-stairs'."""
    m = re.match(r'^(\d+)-(.+)$', name)
    return (m.group(1), m.group(2)) if m else (None, None)


def find_solution_file(problem_dir: Path) -> Optional[Path]:
    """Return the solution code file (skip .md). Prefer 'Accepted' in name."""
    candidates = [f for f in problem_dir.iterdir()
                  if f.is_file() and f.suffix.lower() != '.md']
    if not candidates:
        return None
    accepted = [f for f in candidates if 'accepted' in f.name.lower()]
    return accepted[0] if accepted else candidates[0]


def cookies_to_dict(s: str) -> dict:
    result = {}
    for part in s.split(';'):
        part = part.strip()
        if '=' in part:
            k, _, v = part.partition('=')
            result[k.strip()] = v.strip()
    return result


def lc_link(slug: str, title: str) -> str:
    return f"[{title}](https://leetcode.com/problems/{slug}/)"


def badge(diff: str) -> str:
    return f"{DIFFICULTY_EMOJI.get(diff, '⚪')} {diff}"


# ---------------------------------------------------------------------------
# Metadata fetching
# ---------------------------------------------------------------------------

def build_session(cookies_str: str) -> requests.Session:
    cookies = cookies_to_dict(cookies_str)
    csrf = cookies.get('csrftoken', '')
    s = requests.Session()
    s.cookies.update(cookies)
    s.headers.update({
        "Content-Type": "application/json",
        "Referer":      "https://leetcode.com/",
        "x-csrftoken":  csrf,
        "User-Agent": (
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            "AppleWebKit/537.36 (KHTML, like Gecko) "
            "Chrome/124.0.0.0 Safari/537.36"
        ),
    })
    return s


def fetch_bulk(slugs: set, session: requests.Session) -> dict:
    """Paginated bulk fetch of problemsetQuestionList. Returns {slug: meta}."""
    result = {}
    limit, skip = 100, 0
    print("Fetching problem metadata from LeetCode (bulk)...")
    while True:
        payload = {
            "operationName": "problemsetQuestionList",
            "query": PROBLEM_LIST_QUERY,
            "variables": {"categorySlug": "", "limit": limit,
                          "skip": skip, "filters": {}},
        }
        try:
            resp = session.post(LEETCODE_GRAPHQL, json=payload, timeout=20)
            resp.raise_for_status()
            data = resp.json().get("data", {}).get("problemsetQuestionList", {})
            questions = data.get("questions", [])
            total = data.get("total", 0)
        except Exception as e:
            print(f"  [WARN] Bulk fetch failed at offset {skip}: {e}")
            break

        for q in questions:
            slug = q.get("titleSlug", "")
            if slug in slugs:
                result[slug] = {
                    "questionId": q.get("frontendQuestionId", ""),
                    "title":      q.get("title", slug),
                    "difficulty": q.get("difficulty", "Unknown"),
                    "topicTags":  [t["name"] for t in q.get("topicTags", [])],
                }

        skip += limit
        print(f"  Fetched {min(skip, total)}/{total} problems from LeetCode...")
        if skip >= total or not questions:
            break
        time.sleep(0.3)

    # Per-problem fallback for anything missing
    missing = slugs - set(result.keys())
    if missing:
        print(f"  Fetching {len(missing)} missing problems individually...")
        for slug in missing:
            try:
                resp = session.post(LEETCODE_GRAPHQL, json={
                    "operationName": "getQuestionDetail",
                    "query": SINGLE_QUESTION_QUERY,
                    "variables": {"titleSlug": slug},
                }, timeout=15)
                resp.raise_for_status()
                q = resp.json().get("data", {}).get("question") or {}
                result[slug] = {
                    "questionId": q.get("questionId", ""),
                    "title":      q.get("title", slug),
                    "difficulty": q.get("difficulty", "Unknown"),
                    "topicTags":  [t["name"] for t in q.get("topicTags", [])],
                }
            except Exception as e:
                print(f"    [WARN] {slug}: {e}")
            time.sleep(0.2)
    return result


# ---------------------------------------------------------------------------
# Topic assignment
# ---------------------------------------------------------------------------

def assign_topic(tags: list) -> str:
    tag_set = set(tags)
    for topic, topic_tags in TOPIC_TAG_MAP:
        if topic == "Other":
            return "Other"
        if tag_set & set(topic_tags):
            return topic
    return "Other"


# ---------------------------------------------------------------------------
# Organize
# ---------------------------------------------------------------------------

def organize(raw_dir: Path, out_dir: Path, metadata: dict) -> list:
    problems = []
    for item in sorted(raw_dir.iterdir()):
        if not item.is_dir():
            continue
        q_id, title_slug = parse_folder_name(item.name)
        if q_id is None:
            print(f"  [SKIP] Unrecognized folder: {item.name}")
            continue

        meta = metadata.get(title_slug) or {
            "questionId": q_id,
            "title":      title_slug.replace('-', ' ').title(),
            "difficulty": "Unknown",
            "topicTags":  [],
        }

        topic = assign_topic(meta["topicTags"])
        diff  = meta["difficulty"]
        sol   = find_solution_file(item)

        if sol is None:
            print(f"  [SKIP] No solution file in {item.name}")
            continue

        ext      = sol.suffix
        filename = f"{q_id.zfill(4)}_{title_slug}{ext}"
        dest_dir = out_dir / topic / diff
        dest_dir.mkdir(parents=True, exist_ok=True)
        shutil.copy2(sol, dest_dir / filename)

        record = {
            "questionId":  q_id,
            "title":       meta["title"],
            "titleSlug":   title_slug,
            "difficulty":  diff,
            "topic":       topic,
            "topicTags":   meta["topicTags"],
            "filename":    filename,
            "ext":         ext.lstrip('.'),
        }
        problems.append(record)
        print(f"  [{topic}/{diff}] {q_id}. {meta['title']} → {filename}")

    return sorted(problems, key=lambda p: int(p["questionId"]))


# ---------------------------------------------------------------------------
# README generation
# ---------------------------------------------------------------------------

def gen_topic_readme(topic: str, problems: list, topic_dir: Path):
    display = topic.replace('_', ' ')
    desc    = TOPIC_DESCRIPTIONS.get(topic, "")

    rows = []
    for diff in DIFFICULTY_ORDER:
        group = [p for p in problems if p["difficulty"] == diff]
        if not group:
            continue
        rows.append(f"| | **{badge(diff)}** | | |")
        for p in group:
            rel_path = f"./{diff}/{p['filename']}"
            rows.append(
                f"| {p['questionId']} "
                f"| {lc_link(p['titleSlug'], p['title'])} "
                f"| {badge(p['difficulty'])} "
                f"| [{p['filename']}]({rel_path}) |"
            )

    content = f"""\
# {display} Problems

{desc}

| # | Title | Difficulty | Solution |
|---|-------|------------|----------|
{chr(10).join(rows)}
"""
    (topic_dir / "README.md").write_text(content, encoding="utf-8")
    print(f"  Wrote {topic_dir}/README.md")


def gen_root_readme(problems: list, out_dir: Path, lc_user: str, gh_user: str):
    easy   = sum(1 for p in problems if p["difficulty"] == "Easy")
    medium = sum(1 for p in problems if p["difficulty"] == "Medium")
    hard   = sum(1 for p in problems if p["difficulty"] == "Hard")
    total  = len(problems)

    # Topic summary
    topic_counts = Counter(p["topic"] for p in problems)
    topic_lines  = []
    for topic, _ in TOPIC_TAG_MAP:
        count = topic_counts.get(topic, 0)
        if count:
            display = topic.replace('_', ' ')
            topic_lines.append(
                f"| [{display}](./{topic}/README.md) "
                f"| {sum(1 for p in problems if p['topic']==topic and p['difficulty']=='Easy')} "
                f"| {sum(1 for p in problems if p['topic']==topic and p['difficulty']=='Medium')} "
                f"| {sum(1 for p in problems if p['topic']==topic and p['difficulty']=='Hard')} "
                f"| {count} |"
            )
    topic_table = "\n".join(topic_lines)

    # Full problems table
    all_rows = "\n".join(
        f"| {p['questionId']} "
        f"| {lc_link(p['titleSlug'], p['title'])} "
        f"| {badge(p['difficulty'])} "
        f"| [{p['topic'].replace('_',' ')}](./{p['topic']}/) "
        f"| [{p['filename']}](./{p['topic']}/{p['difficulty']}/{p['filename']}) |"
        for p in problems
    )

    content = f"""\
# LeetCode Solutions

> **LeetCode:** [{lc_user}](https://leetcode.com/{lc_user}/) &nbsp;|&nbsp; **GitHub:** [{gh_user}](https://github.com/{gh_user})

## Stats

| Total Solved | 🟢 Easy | 🟡 Medium | 🔴 Hard |
|:-----------:|:-------:|:---------:|:------:|
| **{total}** | {easy} | {medium} | {hard} |

## Problems by Topic

| Topic | Easy | Medium | Hard | Total |
|-------|:----:|:------:|:----:|:-----:|
{topic_table}

## All Problems

| # | Title | Difficulty | Topic | Solution |
|---|-------|------------|-------|----------|
{all_rows}

---
*Auto-generated · [organize\\_leetcode.py](./organize_leetcode.py)*
"""
    (out_dir / "README.md").write_text(content, encoding="utf-8")
    print(f"  Wrote {out_dir}/README.md")


def gen_gitignore(out_dir: Path):
    (out_dir / ".gitignore").write_text("metadata_cache.json\n", encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    ap = argparse.ArgumentParser(description="Organize LeetCode export by topic + difficulty")
    ap.add_argument("--raw-dir",      required=True,  help="leetcode-export output directory")
    ap.add_argument("--out-dir",      required=True,  help="Destination directory (will be created)")
    ap.add_argument("--cookies",      default="",     help="Browser cookie string for LeetCode")
    ap.add_argument("--skip-fetch",   action="store_true",
                    help="Reuse existing metadata_cache.json (no API calls)")
    ap.add_argument("--lc-username",  default="jeffrin_1111")
    ap.add_argument("--gh-username",  default="jebusonjeffrin-cmd")
    args = ap.parse_args()

    raw_dir    = Path(args.raw_dir).resolve()
    out_dir    = Path(args.out_dir).resolve()
    cache_file = out_dir / "metadata_cache.json"

    if not raw_dir.exists():
        sys.exit(f"[ERROR] Raw directory not found: {raw_dir}")

    out_dir.mkdir(parents=True, exist_ok=True)

    # Collect folders
    problem_dirs = sorted(
        d for d in raw_dir.iterdir()
        if d.is_dir() and re.match(r'^\d+-', d.name)
    )
    print(f"Found {len(problem_dirs)} problem folders in {raw_dir}")

    slugs = set()
    for d in problem_dirs:
        _, slug = parse_folder_name(d.name)
        if slug:
            slugs.add(slug)

    # Fetch or load metadata
    if args.skip_fetch and cache_file.exists():
        print(f"Loading cached metadata from {cache_file}")
        metadata = json.loads(cache_file.read_text(encoding="utf-8"))
    elif args.cookies:
        session  = build_session(args.cookies)
        metadata = fetch_bulk(slugs, session)
        cache_file.write_text(json.dumps(metadata, indent=2), encoding="utf-8")
        print(f"Metadata cached → {cache_file}")
    else:
        print("[WARN] No --cookies provided. Using fallback (no topic tags — all go to Other/).")
        metadata = {}

    # Organize
    print("\nOrganizing solution files...")
    problems = organize(raw_dir, out_dir, metadata)
    print(f"\nOrganized {len(problems)} problems.")

    # READMEs
    print("\nGenerating README files...")
    by_topic = defaultdict(list)
    for p in problems:
        by_topic[p["topic"]].append(p)

    for topic, tp in sorted(by_topic.items()):
        gen_topic_readme(topic, tp, out_dir / topic)

    gen_root_readme(problems, out_dir, args.lc_username, args.gh_username)
    gen_gitignore(out_dir)

    # Copy script into repo
    shutil.copy2(Path(__file__).resolve(), out_dir / "organize_leetcode.py")

    print("\n✅ Done! Next:")
    print(f"   cd \"{out_dir}\"")
    print("   git init && git branch -M main && git add .")
    print('   git commit -m "🧠 Add LeetCode solutions organized by topic"')
    print("   gh repo create jebusonjeffrin-cmd/leetcode-solutions --public --source . --remote origin --push")


if __name__ == "__main__":
    main()
