class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder();
        char[] arr = s.toCharArray();
        for(char c : arr){
            if(Character.isLetterOrDigit(c)){
                str.append(Character.toLowerCase(c));
            }
        }
        String s1 = str.toString();
        String s2 = str.reverse().toString();
        if(s1.equals(s2)){
            return true;
        }
        return false;
    }
}