class Solution {
    public int[] findErrorNums(int[] nums) {
        ArrayList<Integer> lst = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
    
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(map.containsKey(nums[i])){
                int freq = map.get(nums[i]);
                map.put(nums[i] , freq+1);
            }
            else{
                map.put(nums[i] , 1);
            }
        }
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            if(e.getValue() >1){
                lst.add(e.getKey());
            }
        } 
        for(int i=1;i<=n;i++){
            if(!map.containsKey(i)){
                lst.add(i);
            }
        }
        int[] arr = new int[lst.size()];
        for(int i = 0; i < lst.size(); i++){
             arr[i] = lst.get(i);
}
        return arr;
    }
}