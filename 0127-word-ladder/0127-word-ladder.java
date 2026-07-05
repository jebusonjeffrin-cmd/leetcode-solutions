class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> st = new HashSet<>(wordList);
        if(!st.contains(endWord))return 0;
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int level=1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int ix=0;ix<size;ix++){
                String word = q.poll();
                char[] arr = word.toCharArray();
                if(word.equals(endWord))return level;
                for(int i=0;i<arr.length;i++){
                    char c=arr[i];
                    for(int j='a';j<='z';j++){
                        arr[i] = (char)j;
                        String s = new String(arr);
                        if(st.contains(s) && !visited.contains(s)){
                            q.offer(s);
                            visited.add(s);
                        }
                    }
                    arr[i] = c;
                }
            }
            level++;
        }
        return 0;
    }
}