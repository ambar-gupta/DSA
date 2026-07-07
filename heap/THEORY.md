# Heap — Theory Notes

## What is a heap?
A **complete binary tree** (every level filled left-to-right, except possibly the last)
that satisfies the **heap property**:

- **Min-Heap** — every parent <= its children -> smallest element is always at the root.
- **Max-Heap** — every parent >= its children -> largest element is always at the root.

Not fully sorted — only parent-child order is guaranteed, not sibling order.

## Array representation
Complete tree -> stored as an array, no pointers needed.
For a node at index `i` (0-indexed):
- Left child  -> `2i + 1`
- Right child -> `2i + 2`
- Parent      -> `(i - 1) / 2`

## Core operations & complexity

| Operation             | Complexity | What it does |
|------------------------|-----------|---------------|
| `peek()`               | O(1)      | Look at root (min or max) |
| `insert()`             | O(log n)  | Add at end, "bubble up" (swap with parent while violated) |
| `extractMin/Max()`     | O(log n)  | Remove root, move last element to root, "bubble down" |
| `heapify(array)`       | O(n)      | Build a heap from an unsorted array (not O(n log n)) |
| Search arbitrary value | O(n)      | Heaps aren't built for search |

## Why heaps matter for interviews
Go-to structure whenever a problem needs repeated access to the current min/max while
data keeps changing, without fully sorting everything upfront.
Signals in a problem statement: "kth largest/smallest", "top k", "merge k sorted...",
"median of a stream", "closest points".

## Java: `PriorityQueue<T>`
- `new PriorityQueue<>()` -> min-heap by default.
- `new PriorityQueue<>(Collections.reverseOrder())` -> max-heap.
- `new PriorityQueue<>((a, b) -> ...)` -> custom comparator (pairs/objects).
- Key methods: `offer(x)` (insert), `poll()` (extract root), `peek()` (read root).

## Common sticking points
- Heap's generic type must match what you push in — `Entry<K,V>`, not `Map<K,V>`.
- No indexed access (`get(i)` doesn't exist) — only `offer()`, `poll()`, `peek()`.
- For-each iteration isn't sorted order — only `poll()` gives heap order.
- Bounded-heap eviction is inverted: keep k *largest* → min-heap, evict smallest; keep k *smallest* → max-heap, evict largest.

## Practice log
| # | Problem | Status |
|---|---------|--------|
| 1 | Kth Largest Element in an Array | Solved |
| 2 | Top K Frequent Elements | Solved |
| 3 | K Closest Points to Origin | Solved |
| 4 | Find Median from Data Stream | Solved |
