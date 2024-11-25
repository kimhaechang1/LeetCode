import java.util.*;

class MedianFinder {

    // 리스트의 크기가 짝수라면 가운데쪽 두 수의 합이 된다.
    PriorityQueue<Integer> minPQ;
    PriorityQueue<Integer> maxPQ;

    public MedianFinder() {
        minPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        int maxSz = maxPQ.size();
        int minSz = minPQ.size();
        if (minSz == maxSz) {
            if (maxPQ.isEmpty()) {
                maxPQ.add(num);
                return;
            }
            if (maxPQ.peek() <= num) {
                maxPQ.add(num);
            } else {
                if (minPQ.peek() > num) {
                    maxPQ.add(minPQ.poll());
                    minPQ.add(num);
                } else {
                    maxPQ.add(num);
                }
            }
        } else if (minSz < maxSz) {
            if (maxPQ.peek() <= num) {
                minPQ.add(maxPQ.poll());
                maxPQ.add(num);
            } else {
                minPQ.add(num);
            }
        }
    }
    
    public double findMedian() {
        int maxSz = maxPQ.size();
        int minSz = minPQ.size();
        
        if (maxSz == minSz) {
            return ((double)minPQ.peek() + maxPQ.peek()) / 2;
        } else {
            return (double)maxPQ.peek();
        }
        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */