class Solution {
    public String restoreString(String s, int[] indices) {
        char[] a = new char[s.length()];
        for(int i=0;i<s.length();i++){
            a[indices[i]] = s.charAt(i);
        }
        String t = new String(a);
        return t;
    }
}