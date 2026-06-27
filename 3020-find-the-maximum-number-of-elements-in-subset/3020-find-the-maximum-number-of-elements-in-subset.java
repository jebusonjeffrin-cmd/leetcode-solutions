class Solution {
    public int maximumLength(int[] nums) {
        int ans=1,n=nums.length;
        int ones=0;
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            if(nums[i] == 1)ones++;
            else mp.put(nums[i],mp.getOrDefault(nums[i],0)+1);
        }
        for(int i=0;i<n;i++){
            if(nums[i] != 1 && mp.get(nums[i]) >1){
                ans = Math.max(ans,subset(mp,nums[i]));
            }
        }
        if(ones%2 != 0)return Math.max(ans,ones);
        return Math.max(ans,ones-1);
    }
    private int subset(Map<Integer,Integer> mp,int num){
        int pw=2,len=2;
        while(true){
            int nxt = (int)Math.pow(num,pw);
            if(!mp.containsKey(nxt))return len-1;
            if(mp.get(nxt) == 1) return len+1;
            num=nxt;
            len+=2;
        }
    }
}