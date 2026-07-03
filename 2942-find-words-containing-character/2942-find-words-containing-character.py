class Solution:
    def findWordsContaining(self, words: List[str], x: str) -> List[int]:
        ls=[]
        for i in range(len(words)):
            if x in words[i]:
                ls.append(i)
        return ls