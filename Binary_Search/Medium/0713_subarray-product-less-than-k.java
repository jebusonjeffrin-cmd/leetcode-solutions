class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1){
            return 0;
        }
        int count=0;
        int left=0;
        int pro=1;
        for(int right=0;right<nums.length;right++){
            pro=pro*nums[right];
            while(pro >= k){
                pro/=nums[left];
                left++;
            }
            count+=right-left+1;
        }
        return count;
    }
}