package heap;

import java.util.PriorityQueue;

/**
 * Problem 1: Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest
 * element in the array.
 *
 * Note: it is the kth largest element in sorted order, not the kth
 * distinct element.
 *
 * Example 1:
 *   Input:  nums = [3,2,1,5,6,4], k = 2
 *   Output: 5
 *
 * Example 2:
 *   Input:  nums = [3,2,3,1,2,4,5,5,6], k = 4
 *   Output: 4
 *
 * Constraints:
 *   1 <= k <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 *
 * Hint: think about which kind of heap (min or max) lets you track the
 * kth largest while only ever holding k elements at a time.
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
     * Why this works: you keep a min-heap capped at size k. Any time it
     * grows past k, you evict the smallest — so only the k largest
     * elements seen so far ever survive in the heap. Once you've
     * processed the whole array, the smallest of those k survivors (the
     * heap's root) is exactly the kth largest overall.
     *
     * Complexity:
     * - Time: O(n log k) — n insertions/removals, each O(log k) since the
     *   heap never holds more than k elements. This beats the naive
     *   full-sort approach (O(n log n)) when k is small.
     * - Space: O(k).
     */
}
