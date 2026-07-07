package heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem 4: Find Median from Data Stream
 *
 * Design a structure that supports:
 * - addNum(int num): add a number from the stream.
 * - findMedian(): return the median of all numbers added so far.
 *
 * Example:
 * addNum(1); addNum(2); findMedian() -> 1.5
 * addNum(3); findMedian() -> 2
 *
 * Hint: one heap isn't enough here — you need two, one holding the
 * smaller half of the numbers and one holding the larger half. What
 * heap type should each half be, and how do you keep their sizes
 * balanced (within 1 of each other) as numbers come in?
 */
public class Problem4_FindMedianFromDataStream {

	PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
	PriorityQueue<Integer> right = new PriorityQueue<>();
	
	

    public void addNum(int num) {
        // TODO: write your logic here
    	if(left.isEmpty()) {
    		left.add(num);
    	}else {
    		if(left.peek() > num) {
    			left.add(num);
    			if(left.size() - right.size() > 1) {
    				int n = left.poll();
    				right.add(n);
    			}
    		} else {
    			right.add(num);
    			if(right.size() - left.size() > 1) {
    				int n = right.poll();
    				left.add(n);
    			}
    		}
    	}
    }

    public double findMedian() {
       if(left.size() < right.size()) {
    	   return (double) right.peek();
       } 
       if(left.size() == right.size()) {
    	   return (double) (right.peek() + left.peek())/2;
       }
       return (double) left.peek();
       
    }
    

    public static void main(String[] args) {
        Problem4_FindMedianFromDataStream solution = new Problem4_FindMedianFromDataStream();

        solution.addNum(1);
        solution.addNum(2);
        System.out.println("Expected: 1.5, Got: " + solution.findMedian());

        solution.addNum(3);
        System.out.println("Expected: 2.0, Got: " + solution.findMedian());
    }

    /*
     * Two heaps: left (max-heap) holds the smaller half, right
     * (min-heap) holds the larger half, sizes kept within 1 of each
     * other. addNum places the number by comparing to left's top, then
     * rebalances if needed. findMedian reads the top(s) directly.
     * Time: O(log n) per addNum, O(1) per findMedian. Space: O(n).
     */
}
