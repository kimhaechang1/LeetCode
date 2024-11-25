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
        //System.out.println("add: "+num);
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
        //System.out.println("up: "+maxPQ);
        //System.out.println("down: "+minPQ);
        //System.out.println("################");
    }
    
    public double findMedian() {
        //System.out.println("find median");
        int maxSz = maxPQ.size();
        int minSz = minPQ.size();
        //System.out.println("maxSz: "+maxSz+" minSz: "+minSz);
        
        if (maxSz == minSz) {
            //System.out.println("med: "+ (((double)minPQ.peek() + maxPQ.peek()) / 2));
            //System.out.println("###################");
            return ((double)minPQ.peek() + maxPQ.peek()) / 2;
        } else {
           // System.out.println("med: "+ (double)maxPQ.peek());
            //System.out.println("###################");
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