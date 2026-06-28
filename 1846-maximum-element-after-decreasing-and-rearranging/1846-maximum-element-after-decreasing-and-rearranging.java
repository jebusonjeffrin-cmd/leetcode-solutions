class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
       int mx=1;
       Arrays.sort(arr);
       arr[0] = 1;
       for(int i=1;i<arr.length;i++){
        if(Math.abs(arr[i]-arr[i-1]) > 1){
            int diff = arr[i]-arr[i-1]-1;
            arr[i] -= diff;
        }
        mx = Math.max(mx,arr[i]);
       } 
       return mx;
    }
}