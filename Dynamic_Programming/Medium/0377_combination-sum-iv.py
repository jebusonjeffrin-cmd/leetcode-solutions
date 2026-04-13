class Solution(object):
    def combinationSum4(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        memo={}
        def backtrack(target):
            if target==0:
                return 1
            if target<0:
                return 0
            if target in memo:
                return memo[target]
            r=0
            for i in range(len(nums)):
                r+=backtrack(target-nums[i])
            memo[target]=r
            return r
        return backtrack(target)