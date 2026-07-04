package heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Problem 2: Top K Frequent Elements
 *
 * Given an int array nums and int k, return the k most frequent elements
 * (any order).
 *
 * Example: nums = [1,1,1,2,2,3], k = 2 -> [1,2]
 *
 * Hint: frequency map, then same min-heap-capped-at-k pattern as
 * Problem 1, ordered by frequency instead of value.
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
     * Frequency map, then min-heap of entries (by count) capped at k ->
     * heap ends up holding the k most frequent entries.
     * Time: O(n log k). Space: O(n) map + O(k) heap.
     */
}
