import java.util.*;

class MedianFinder {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
    public MedianFinder() {
    
    }

    public void addNum(int num) {
        minheap.add(num);
        if(minheap.size()-maxheap.size() > 1){
            maxheap.add(minheap.poll());  
        }
        if(!maxheap.isEmpty() && maxheap.peek() > minheap.peek()){
            int a = maxheap.poll();
            int b = minheap.poll();
            maxheap.add(b);
            minheap.add(a);
        }
    }

    public double findMedian() {
        if(minheap.size() == maxheap.size()){
            return (minheap.peek() + maxheap.peek())/2.0;
        }

        return minheap.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */