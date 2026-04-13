class Solution(object):
    def fib(self, n):
        dp = []
        for i in range(n+1):
            dp.append(-1)
        return self.fibonacci(n,dp)
    def fibonacci(self,n,dp):
        if n <= 1:
            return n
        if dp[n] != -1:
            return dp[n]
        dp[n] = self.fibonacci(n-1,dp) + self.fibonacci(n-2,dp)
        return dp[n]
        