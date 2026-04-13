class Solution {
    public int minAbsoluteDifference(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] == 1 && nums[j] == 2){
                    list.add(Math.abs(i-j));
                }
                else if(nums[i] == 2 && nums[j] == 1){
                    list.add(Math.abs(i-j));
                }
            }
        }
        if(list.isEmpty())return -1;
        int curr = 0;
        int max = Integer.MAX_VALUE;
        for(int i=0;i<list.size();i++){
            curr = list.get(i);
            max = Math.min(max,curr);
        }
        return max;
    }
}