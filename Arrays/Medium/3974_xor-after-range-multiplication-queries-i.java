class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long[] arr = new long[nums.length];
        for(int i=0;i<nums.length;i++){
            arr[i] = nums[i];
        }
        long result=0;
        long mod = 1000000007;
        for(int[] q:queries){
            int idx = q[0];
            while(idx<=q[1]){
                arr[idx] = ((long)((arr[idx] * q[3])%mod));
                idx+=q[2];
            }
        }
        for(long n:arr){
            result ^=n;
        }
        return (int)result;
    }
}