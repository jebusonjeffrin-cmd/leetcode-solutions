
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return gcd(n*n,n*(n+1)); 
    }
    private int gcd(int a,int b){
        if(b==0)return a;
        return gcd(b,a%b);
    }
}