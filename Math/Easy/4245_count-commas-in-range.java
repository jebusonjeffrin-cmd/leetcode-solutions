class Solution {
    public int countCommas(int n) {
        int count=0;
        while(n>999){
            count+=check(n);
            n=n-1;
        }
        return count;
    }
    private int check(int n){
        String s = n+"";
        return (s.length()-1)/3;
    }
}