class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            if(map.containsKey(ch)){
                int freq = map.get(ch);
                map.put(ch,freq+1);
            }
            else{
                map.put(ch,1);
            }
        }
        for(char c : t.toCharArray()){
            if(!map.containsKey(c)){
                return false;
            }
            else{
                int freq = map.get(c);
                if(freq == 1){
                    map.remove(c);
                }
                else{
                    map.put(c,freq-1);
                }
            }
        }
        return true;
    }
}