class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<stones.length();i++){
            char c = stones.charAt(i);
            if(map.containsKey(c)){
                int freq = map.get(c);
                map.put(c,freq+1);
            }
            else{
                map.put(c,1);
            }
        }
        int count = 0;
        for(int i=0;i<jewels.length();i++){
            char c = jewels.charAt(i);
            if(map.containsKey(c)){
                count+=map.get(c);
                map.remove(c);
            }
                
            
        }
        return count;
    }
}