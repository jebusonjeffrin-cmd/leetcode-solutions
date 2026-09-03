class Solution {
    public boolean uniformArray(int[] nums) {
        int mn = Integer.MAX_VALUE;
        for(int num:nums){
            mn = Math.min(mn,num);
        }
        boolean isOdd = ((mn&1) == 1);
        if(!isOdd){
            for(int num:nums){
                if((num&1) == 1)return false;
            }
        }
        return true;
    }
}