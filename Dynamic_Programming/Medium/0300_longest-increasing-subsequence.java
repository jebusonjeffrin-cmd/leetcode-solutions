class Solution {
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        Arrays.fill(dp,1);
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],1+dp[j]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            if(dp[i] > max)max= dp[i];
        }
        return max;
    }
}