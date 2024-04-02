package heap;


/**
 * Code adapted from class slides 
 */

public class OneByOne {
	private int[] heap = new int[1000];
	private int size = 0;
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public boolean isFull() {
		return (size == heap.length);
	}
	
	public int parent(int loc) {
		return (loc - 1)/2;
	}
	
	public int leftChild(int loc) {
		return (2 * loc) + 1;
	}
	
	public int rightChild(int loc) {
		return (2 * loc) + 2;
	}
	
	public boolean isLeaf(int loc) {
		return (loc > (size/2)) && (loc <= size);
	}
	
	private void swap(int location, int parent) {
		int temp;
		temp = heap[location];
		heap[location] = heap[parent];
		heap[parent] = temp;	
		System.out.println("Swapping " + heap[parent] + " and " + heap[location]);
	}
	
	public void insert(int data) {
		if(isFull()) {
			return;
		}
		
		// Add element at position heap[size] aka at end of array
		heap[size] = data;
		
		// Set initial current and parent node
		int current = size;
		
		// While current is > 0 (not the root) AND the difference 
		// between the value of current and value of parent is > 0
        // Move the new element up to maintain the heap property
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
		size++;
	}
	
	public void printHeap() {
		for(int i = 0; i < size; i++) {
			System.out.println("heap[" + i + "]: " + heap[i]);
		}
	}
	
//	public void enqueue(int data) {
//		
//		// Add element at position heap[size] aka at end of array
//		heap[size] = data;
//		
//		// Set initial current and parent node
//		int current = size;
//		int parent = (current - 1)/2;
//		
//		// While current is > 0 (not the root) AND the difference 
//		// between the value of current and value of parent is > 0
//		while((current > 0) && ((heap[current] - heap[parent]) > 0)) {
//			// Swap current location node and parent nodes
//			swap(current, parent);
//			
//			// The parent node now becomes the current location node
//			current = parent;
//			
//			// Recalculate the new parent using this formula
//			parent = (current - 1)/2;
//		}
//		size++;
//	}
	
//	public int dequeue() {
//		int max = heap[0];
//		
//		heap[0] = heap[size - 1];
//		sink(0);
//		heap[size - 1] = null;
//		size--;
//		return max;
//	}
	
}






















