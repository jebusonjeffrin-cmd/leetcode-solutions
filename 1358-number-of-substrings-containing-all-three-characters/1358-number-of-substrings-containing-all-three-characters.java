class Solution {
    public int numberOfSubstrings(String s) {
        HashSet<Character> st = new HashSet<>();
        int left=0,n=s.length(),ans=0;
        int[] freq = new int[3];
        for(int right=0;right<n;right++){
            char ch = s.charAt(right);
            freq[ch-'a']++;
            while(freq[0]>0 && freq[1]>0 && freq[2]>0){
                char c = s.charAt(left);
                ans+= n-right;
                freq[c-'a']--;
                left++;
            }
        }
        return ans;
    }
}