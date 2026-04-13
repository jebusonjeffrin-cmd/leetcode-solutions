class Solution {
    public boolean isPowerOfTwo(int n) {
        long s = n;
        if(s==0){
            return false;
        }
        else if((s&(s-1))==0){
            return true;
        }
        return false;
    }
}