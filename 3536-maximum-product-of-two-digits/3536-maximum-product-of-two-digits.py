class Solution:
    def maxProduct(self, n: int) -> int:
        
        s = list(str(n))
        s.sort()
        n = len(s)
        return int(s[n-2])*int(s[n-1])
