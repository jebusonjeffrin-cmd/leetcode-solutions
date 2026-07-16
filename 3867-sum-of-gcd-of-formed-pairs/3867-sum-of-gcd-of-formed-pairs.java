class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx=-1;
        for(int i=0;i<n;i++){
            mx = Math.max(mx,nums[i]);
            prefixGcd[i] = gcd(nums[i],mx);
        }
        //System.out.println(Arrays.toString(prefixGcd));
        Arrays.sort(prefixGcd);
        //System.out.println(Arrays.toString(prefixGcd));
        int i=0,j=n-1;
        long sum=0;
        while(i<j){
            sum += gcd(prefixGcd[i],prefixGcd[j]);
            //System.out.println(sum);
            i++;j--;
        }
        return sum;
    }
    private int  gcd(int a,int b){
        if(b==0)return a;
        return gcd(b,a%b);
    }
}