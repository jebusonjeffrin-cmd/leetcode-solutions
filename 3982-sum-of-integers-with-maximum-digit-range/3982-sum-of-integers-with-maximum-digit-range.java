class Solution {
    public int maxDigitRange(int[] nums) {
      int lr=Integer.MIN_VALUE,ans=0;
      for(int n:nums){
        lr = Math.max(lr,digitRange(n));
      }  
      for(int n:nums){
        if(digitRange(n) == lr)ans+=n;
      }
      return ans;
    }
    private int digitRange(int num){
        int mx=Integer.MIN_VALUE;
        int mn=Integer.MAX_VALUE;
        while(num>0){
            int rem=num%10;
            if(rem > mx)mx=rem;
            if(rem < mn)mn=rem;
            num=num/10;
        }
        return mx-mn;
    }
}