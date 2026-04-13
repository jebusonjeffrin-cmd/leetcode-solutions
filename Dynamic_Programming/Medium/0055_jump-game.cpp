class Solution {
public:
    bool canJump(vector<int>& nums) {
        int N = nums.size(),till =0;
        for(int i=0;i<N-1 && till>=i;i++){
                till = max(till,i+nums[i]);
        }
        return till>=N-1;
    }
};