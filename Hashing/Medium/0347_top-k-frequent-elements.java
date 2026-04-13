class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] arr = new int[k];
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        PriorityQueue<Integer> minheap = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for(int key:map.keySet()){
            minheap.add(key);
            if(minheap.size() > k){
                minheap.poll();
            }
        }
        for(int i=0;i<k;i++){
            arr[i] = minheap.poll();
        }
        return arr;

        
    }
}