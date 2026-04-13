class Solution {
    public char findTheDifference(String s, String t) {
        char result=' ';
        for(char c : s.toCharArray()){
            result ^=c;
        }
        for(char a : t.toCharArray()){
            result ^=a;
        }
        
        return Character.toLowerCase(result);

    }
}