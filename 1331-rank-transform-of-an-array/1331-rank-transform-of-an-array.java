class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = Arrays.copyOf(arr,arr.length);
        Arrays.sort(temp);
        int rank=1;
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(!mp.containsKey(temp[i])){
                mp.put(temp[i],rank++);
            }
        }
        for(int i=0;i<arr.length;i++){
            temp[i] = mp.get(arr[i]);
        }
        return temp;
    }
}