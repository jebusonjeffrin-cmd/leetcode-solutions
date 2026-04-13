class Solution {
    public int missingNumber(int[] nums) {
        int actualsum=0;
        int missingsum=0;
        int n = nums.length;
        for(int i=0;i<n+1;i++){
            actualsum+=i;
        }
        for(int i=0;i<n;i++){
            missingsum+=nums[i];
        }
        return actualsum-missingsum;
    }
}