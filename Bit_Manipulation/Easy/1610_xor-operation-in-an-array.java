class Solution {
    public int xorOperation(int n, int start) {
        int[] arr = new int[n];
        int result =0;
        for(int i=0;i<n;i++){
            arr[i] = start+2 * i;
            result = result^arr[i];
        }
        return result;
       
    }
}