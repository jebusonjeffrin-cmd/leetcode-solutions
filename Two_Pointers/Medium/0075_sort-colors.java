class Solution {
    public void sortColors(int[] nums) {
        //int n = nums.length;
        int zero = 0;
        int one = 0;
        int two = 0;
        for(int n:nums){
            if(n==0) zero++;
            else if(n==1) one++;
            else two++;
        }
        for(int i=0;i<zero;i++){
            nums[i] = 0;
        }
        for(int j=zero;j<one+zero;j++){
            nums[j] = 1;
        }
        for(int k=one+zero;k<nums.length;k++){
            nums[k] = 2;
        }
    }
}