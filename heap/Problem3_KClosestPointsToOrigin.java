package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Problem 3: K Closest Points to Origin
 *
 * Given int[][] points (each [x,y]) and int k, return the k points
 * closest to (0,0), any order. Use squared distance (no sqrt needed).
 *
 * Example: points = [[1,3],[-2,2]], k = 1 -> [[-2,2]]
 *
 * Hint: Problems 1 and 2 kept the k LARGEST via a min-heap (evict
 * smallest). Here you want the k SMALLEST distances — which heap type
 * and eviction rule gives you that instead?
 */
public class Problem3_KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        // TODO: write your logic here
    	HashMap<List<Integer>,Double> map = new HashMap<>();
    	int n = points.length;
    	for(int i=0; i<n; i++) {
    		int x = points[i][0];
    		int y = points[i][1];
    		int temp = (x*x) + (y*y);
    		double v = Math.sqrt(temp);
    		List<Integer> l = new ArrayList<>();
    		l.add(x);
    		l.add(y);
    		map.put(l, v);
    	}
    	
    	PriorityQueue<Entry<List<Integer>,Double>> pq = 
    			new PriorityQueue<>((a,b) -> Double.compare(b.getValue(), a.getValue()));
    	
    	for(Entry<List<Integer>,Double> entry : map.entrySet()) {
    		pq.add(entry);
    		if(pq.size() > k) {
    			pq.poll();
    		}
    	}
    	
    	int[][] ans = new int[k][2];
    	for(int i=0; i<k; i++) {
    		Entry<List<Integer>, Double> l = pq.poll();
    		ans[i][0] = l.getKey().get(0);
    		ans[i][1] = l.getKey().get(1);
    	}
        return ans;
    }

    // Optimized: compares raw int[] points directly (no HashMap, no sqrt).
    // Squared distance preserves ordering, so sqrt is wasted work; and
    // HashMap<List<Integer>, Double> would silently drop duplicate points
    // since they'd collide as the same map key.
    public int[][] kClosestOptimized(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem3_KClosestPointsToOrigin solution = new Problem3_KClosestPointsToOrigin();

        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        System.out.println("Expected: [[-2, 2]], Got: "
                + Arrays.deepToString(solution.kClosest(points1, k1)));
        System.out.println("Expected: [[-2, 2]], Got (optimized): "
                + Arrays.deepToString(solution.kClosestOptimized(points1, k1)));

        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        System.out.println("Expected: [[3, 3], [-2, 4]] (any order), Got: "
                + Arrays.deepToString(solution.kClosest(points2, k2)));
        System.out.println("Expected: [[3, 3], [-2, 4]] (any order), Got (optimized): "
                + Arrays.deepToString(solution.kClosestOptimized(points2, k2)));
    }
}
