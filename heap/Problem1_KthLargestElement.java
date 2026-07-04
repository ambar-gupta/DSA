package heap;

import java.util.PriorityQueue;

/**
 * Problem 1: Kth Largest Element in an Array
 *
 * Given an int array nums and int k, return the kth largest element
 * (sorted order, not distinct).
 *
 * Example: nums = [3,2,1,5,6,4], k = 2 -> 5
 *
 * Hint: which heap type lets you track the kth largest while only ever
 * holding k elements?
 */
public class Problem1_KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i : nums){
            minHeap.offer(i);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        Problem1_KthLargestElement solution = new Problem1_KthLargestElement();

        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Expected: 5, Got: " + solution.findKthLargest(nums1, k1));

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("Expected: 4, Got: " + solution.findKthLargest(nums2, k2));
    }

    /*
     * Min-heap capped at size k -> root is always the kth largest.
     * Time: O(n log k). Space: O(k).
     */
}
