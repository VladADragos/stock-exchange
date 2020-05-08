// package src;

import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
	public ArrayList<E> heap = new ArrayList<E>();
	private Comparator<E> comparator;

	private Map<E, Integer> map = new HashMap<>();

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
		map.put(x,size()-1);
		siftUp(size()-1,x);

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

		if (heap.size() > 0){

			siftDown(0,minimum());
		}
	}

	public void update(E oldElement, E newElement) {
		// int index = heap.indexOf(oldElement);
		boolean exists = map.get(oldElement) != null;
		if (exists) {

			// int index = map.get(oldElement);
			int index = map.get(oldElement);

			 map.remove(oldElement);
			 map.put(newElement, index);
			heap.set(index, newElement);

			int comparison = comparator.compare(newElement, oldElement);

			if (comparison == -1) {
				siftUp(index,newElement);
			} else if (comparison == 1) {
				siftDown(index,newElement);
			}

			// siftUp(index);
			// index = heap.indexOf(newElement);
			// siftDown(index);
		}

	}

	// Sifts a node up.
	// siftUp(index) fixes the invariant if the element at 'index' may
	// be less than its parent, but all other elements are correct.
	private void siftUp(int index,E value) {
		// E thing = heap.get(index);
		

		while (index > 0 ) {
			int parentIndex = parent(index);
			E parentValue = heap.get(parentIndex);

			if(comparator.compare(parentValue,value) <=0){
				break;
			}

			heap.set(index, parentValue);
			map.put(parentValue,index);
			heap.set(parentIndex, value);
			map.put(value,parentIndex);

			index = parentIndex;
		}
		//heap.set(index,thing);
	}

	// Sifts a node down.
	// siftDown(index) fixes the invariant if the element at 'index' may
	// be greater than its children, but all other elements are correct.
	private void siftDown(int index,E value) {
		// E value = heap.get(index);

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
				map.put(childValue,index);
				index = child;
			} else
				break;
		}

		heap.set(index, value);
		map.put(value, index);
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
