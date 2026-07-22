class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        int n = nums.length;
        for(int i=0;i<n;i++){
            int j = i+1,k=nums.length-1;
            while(j<k){
                int ans = nums[i]+nums[j]+nums[k];
                if(ans == 0){
                    res.add(new ArrayList<>(List.of(nums[i],nums[j],nums[k])));
                    j++;k--;
                }else if(ans < 0){
                    j++;
                }
                else k--;
            }
        }
        return new ArrayList<>(res);
    }
}