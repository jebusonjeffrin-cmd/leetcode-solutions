class Solution:
    def rearrangeString(self, s: str, x: str, y: str) -> str:
        t=""
        n=0
        for i in s:
            if i == y:
                n+=1
        t = y*n
        for i in s:
            if i != y:
                t += i
        return t