class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        int left =0;
        int maxfreq=0;
        int maxlen=0;
        for(int right=0;right<s.length();right++){
            char r = s.charAt(right);
            map.put(r,map.getOrDefault(r,0)+1);

            maxfreq = Math.max(maxfreq,map.get(r));
            
            while((right-left+1) - maxfreq > k){
                char l = s.charAt(left);
                map.put(l,map.get(l)-1);
                left++;
            }

            maxlen = Math.max(maxlen,right-left+1);
        }
        return maxlen;
    }
}