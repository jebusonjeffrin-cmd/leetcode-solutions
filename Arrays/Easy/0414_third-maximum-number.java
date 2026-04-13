class Solution {
    public int thirdMax(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n:nums){
            set.add(n);
        }
        int[] arr = new int[set.size()];
        int count=0;
        for(int n:set){
            arr[count++] = n;
        }
        Arrays.sort(arr);
        int len = arr.length-1;
        if(len >= 2){
            return arr[len-2];
        }
        return arr[len];
    }
}