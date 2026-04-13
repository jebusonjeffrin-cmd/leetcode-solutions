class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        
        for(int n:nums1){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        for(int n:nums2){
            if(map.containsKey(n)){
                set.add(n);
            }
        }
        int[] resultArr = new int[set.size()];
        int i = 0;

        for(int num : set){
            resultArr[i++] = num;
        }

        return resultArr;
    }
}