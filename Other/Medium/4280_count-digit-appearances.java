class Solution {
    public int countDigitOccurrences(int[] nums, int digit) {
        int count=0;
        for(int i=0;i<nums.length;i++){
            count+=count(nums[i],digit);
        }
        return count;
    }
    private int count(int n,int digit){
        int count=0;
        while(n>0){
            if(n%10 == digit){
                count++;
            }
            n=n/10;
        }
        return count;
    }
}