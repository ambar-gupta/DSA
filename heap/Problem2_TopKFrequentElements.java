package heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Problem 2: Top K Frequent Elements
 *
 * Given an integer array nums and an integer k, return the k most
 * frequent elements. You may return the answer in any order.
 *
 * Example 1:
 *   Input:  nums = [1,1,1,2,2,3], k = 2
 *   Output: [1,2]
 *
 * Example 2:
 *   Input:  nums = [1], k = 1
 *   Output: [1]
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   k is in range [1, number of distinct elements in nums]
 *   It is guaranteed that the answer is unique.
 *
 * Hint: first build a frequency map (value -> count), then reuse the
 * same "min-heap capped at size k" pattern from Problem 1 — just ordered
 * by frequency instead of by value.
 *
 * Follow-up (optional, once the heap solution works): this can be done
 * in better than O(n log n) using bucket sort on frequency. Think about
 * why frequency values are bounded by nums.length.
 */
public class Problem2_TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

    	HashMap<Integer,Integer> map = new HashMap<>();
    	
    	for(int i=0; i<nums.length; i++) {
    		map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    	}
    	 // [1,3] [2,2] [3,1]
    	
    	PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
    	
    	for(Entry<Integer,Integer> entry : map.entrySet()) {
    		pq.add(entry);
    		if(pq.size() > k) {
    			pq.poll();
    		}
    	}
    	int ans[] = new int[k];
    	int i =0;
    	for(Entry<Integer,Integer> e : pq) {
    		ans[i] = e.getKey();
    		i++;
    	}
        return ans;
    }

    public static void main(String[] args) {
        Problem2_TopKFrequentElements solution = new Problem2_TopKFrequentElements();

        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("Expected: [1, 2] (any order), Got: "
                + Arrays.toString(solution.topKFrequent(nums1, k1)));

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("Expected: [1], Got: "
                + Arrays.toString(solution.topKFrequent(nums2, k2)));
    }

    /*
     * Why this works: build a frequency map first (value -> count), then
     * keep a min-heap of entries capped at size k, ordered by frequency
     * (entry.getValue()). Any time the heap grows past k, the entry with
     * the smallest frequency gets evicted — so only the k most frequent
     * entries survive. Draining the heap at the end (entry.getKey())
     * gives exactly the k most frequent values, in no particular order.
     *
     * Complexity:
     * - Time: O(n log k) — n entries, each heap op (offer/poll) is
     *   O(log k) since the heap never holds more than k elements.
     * - Space: O(n) for the frequency map + O(k) for the heap.
     */
}
