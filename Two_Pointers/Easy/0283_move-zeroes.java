class Solution {
    public void moveZeroes(int[] nums) {
        int count=0;
        int zerocount = 0;
        int len = nums.length;
        for(int n:nums){
            if(n !=0){
                nums[count++] = n;
                zerocount++;
            }
        }
        for(int i =zerocount;i<len;i++){
            nums[i] = 0;
        }
    }
}