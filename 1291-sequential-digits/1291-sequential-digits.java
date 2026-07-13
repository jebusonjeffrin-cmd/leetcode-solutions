class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        mp.put(12,11);mp.put(123,111);mp.put(1234,1111);mp.put(12345,11111);
        mp.put(123456,111111);mp.put(1234567,1111111);mp.put(12345678,11111111);
        mp.put(123456789,111111111);
        
        List<Integer> ans = new ArrayList<>();
        Integer key = mp.floorKey(low);
        if (key == null) key = mp.ceilingKey(low);
        
        int val = key;
        int dig = mp.get(key);
        
        while (val <= high) {
            if (val >= low) ans.add(val);       
            if (val % 10 == 9) {
                Integer nextKey = mp.higherKey(val);
                if (nextKey == null) break;
                val = nextKey;
                dig = mp.get(val);
            } else {
                val += dig;
            }
        }
        return ans;
    }
}