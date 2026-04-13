class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone:stones){
            maxheap.add(stone);
        }


        while(maxheap.size() >1){
            int x = maxheap.poll();
            int y = maxheap.poll();
            if(x!=y){
                maxheap.offer(x-y);
            }
        }
        if(!maxheap.isEmpty())return maxheap.peek();

        return 0;
    }
}