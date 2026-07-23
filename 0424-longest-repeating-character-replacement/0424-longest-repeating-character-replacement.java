class Solution {
    public int characterReplacement(String s, int k) {
        int mx=-1,left=0,n=s.length(),mxfreq=0;
        Map<Character,Integer> mp = new HashMap<>();

        for(int right=0;right<n;right++){
            mp.put(s.charAt(right),mp.getOrDefault(s.charAt(right),0)+1);
            mxfreq = Math.max(mxfreq,mp.get(s.charAt(right)));
            while(right-left+1 -mxfreq > k){
                char l = s.charAt(left);
                mp.put(l,mp.get(l)-1);
                left++; 
            }
            mx = Math.max(mx,right-left+1);
        }
        return mx;
    }
}