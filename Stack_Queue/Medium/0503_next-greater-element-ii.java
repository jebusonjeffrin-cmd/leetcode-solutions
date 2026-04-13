class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for(int i = 0; i < n; i++){
            boolean found = false;

            for(int j = i + 1; j < n; j++){
                if(nums[j] > nums[i]){
                    ans[i] = nums[j];
                    found = true;
                    break;
                }
            }

            if(!found){
                for(int j = 0; j < i; j++){
                    if(nums[j] > nums[i]){
                        ans[i] = nums[j];
                        found = true;
                        break;
                    }
                }
            }

            if(!found){
                ans[i] = -1;
            }
        }

        return ans;
    }
}