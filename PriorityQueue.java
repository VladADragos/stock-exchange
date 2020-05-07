// package src;

import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
	private ArrayList<E> heap = new ArrayList<E>();
	private Comparator<E> comparator;

	// private Map<E, Integer> map = new HashMap<>();

	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Returns the size of the priority queue.
	public int size() {
		return heap.size();
	}

	// Adds an item to the priority queue.
	public void add(E x) {
		heap.add(x);
		siftUp(size() - 1);

		// throw new UnsupportedOperationException();
	}

	// Returns the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	public E minimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		return heap.get(0);
	}

	// Removes the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	public void deleteMinimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		// map.remove(heap.get(0));
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);

		if (heap.size() > 0)
			siftDown(0);
	}

	public void update(E oldElement, E newElement) {

		// boolean exists = heap.indexOf(oldElement) != -1;
		// System.out.println("INDEX OF OLD ELEMENT " + heap.indexOf(oldElement));
		// System.out.println("OLD ELEMENT" + ((Bid)oldElement)).name ==
		// ((Bid)heap.get(0)).name && ((Bid)oldElement)).bid == ((Bid)heap.get(0)).bid);

		// int index = heap.indexOf(oldElement);
		boolean exists = heap.indexOf(oldElement) != -1;
		if (exists) {

			// int index = map.get(oldElement);
			int index = heap.indexOf(oldElement);

			// map.remove(oldElement);
			// map.put(newElement, index);
			heap.set(index, newElement);

			int comparison = comparator.compare(newElement, oldElement);

			// if new < old then it will have higher priority hence sift up
			if (comparison == -1) {
				siftUp(index);
				// if new > old then it will have lower priority hence sift down
			} else if (comparison == 1) {
				siftDown(index);
			}

			// siftUp(index);
			// index = heap.indexOf(newElement);
			// siftDown(index);
		}

	}

	// Sifts a node up.
	// siftUp(index) fixes the invariant if the element at 'index' may
	// be less than its parent, but all other elements are correct.
	private void siftUp(int index) {
		E value = heap.get(index);

		int parentIndex = parent(index);
		E parentValue = heap.get(parentIndex);

		while (parentIndex > 0 && comparator.compare(parentValue, value) > 0) {
			// System.out.println("Is run");
			//parentIndex = parent(index);
			//parentValue = heap.get(parentIndex);

			heap.set(parentIndex, value);
			heap.set(index, parentValue);

			index = parent(index);
			parentIndex = parent(index);
			if (parentIndex != -1){
				parentValue = heap.get(parentIndex);
			}
		}
		// map.put(value, index);


		// int newValue = heap[index];

        // while (index > 0 && newValue > heap[getParent(index)]) {
        //     heap[index] = heap[getParent(index)];
        //     index = getParent(index);
        // }

        // heap[index] = newValue;
	}

	// Sifts a node down.
	// siftDown(index) fixes the invariant if the element at 'index' may
	// be greater than its children, but all other elements are correct.
	private void siftDown(int index) {
		E value = heap.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < heap.size()) {
			int left = leftChild(index);
			int right = rightChild(index);

			// Work out whether the left or right child is smaller.
			// Start out by assuming the left child is smaller...
			int child = left;
			E childValue = heap.get(left);

			// ...but then check in case the right child is smaller.
			// (We do it like this because maybe there's no right child.)
			if (right < heap.size()) {
				E rightValue = heap.get(right);
				if (comparator.compare(childValue, rightValue) > 0) {
					child = right;
					childValue = rightValue;
				}
			}

			// If the child is smaller than the parent,
			// carry on downwards.
			if (comparator.compare(value, childValue) > 0) {
				heap.set(index, childValue);
				index = child;
			} else
				break;
		}

		heap.set(index, value);
		// map.put(value, index);
		// here?
	}

	// Helper functions for calculating the children and parent of an index.
	private final int leftChild(int index) {
		return 2 * index + 1;
	}

	private final int rightChild(int index) {
		return 2 * index + 2;
	}

	private final int parent(int index) {
		return (index - 1) / 2;
	}

}
