class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean[] isprime = new boolean[right+1];
        Arrays.fill(isprime,true);
        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] =-1;
        isprime[0] = false;
        isprime[1] = false;
        for(int i=2;i*i<=right;i++){
            if(isprime[i]){
                for(int j=i*i;j<=right;j+=i){
                    isprime[j] = false;
                }
            }
        }
        int currdiff=0;int mindiff=Integer.MAX_VALUE;
        int prev=-1;
        for(int i=left;i<=right;i++){
            if(isprime[i]){
                if(prev!=-1){
                    currdiff = i-prev;
                    if(currdiff<mindiff){
                        mindiff=currdiff;
                        arr[0] = prev;
                        arr[1] = i;
                    }
                }
                prev = i;
            }
        }
        return arr;
        
    }
}