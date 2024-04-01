package heap;


/**
 * Code adapted from class slides 
 */

public class OneByOne {
	private int[] array = new int[1000];
	private int size = 0;
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public boolean isFull() {
		return (size == array.length);
	}
	
	public void enqueue(int data) {
		// Add element at position array[size] aka at end of array
		array[size] = data;
		
		// Set initial current and parent node
		int current = size;
		int parent = (current - 1)/2;
		
		// While current is > 0 (not the root) AND the difference 
		// between the value of current and value of parent is > 0
		while((current > 0) && ((array[current] - array[parent]) > 0)) {
			// Swap current location node and parent nodes
			swap(current, parent);
			
			// The parent node now becomes the current location node
			current = parent;
			
			// Recalculate the new parent using this formula
			parent = (current - 1)/2;
		}
		size++;
	}

	private void swap(int location, int parent) {
		int temp;
		temp = array[location];
		array[location] = array[parent];
		array[parent] = temp;		
	}
	
	public int dequeue() {
		int max = array[0];
		
		array[0] = array[size - 1];
		sink(0);
		array[size - 1] = null;
		size--;
		return max;
	}
	
	public void sink(int location) {
		int swapWith;
		int left = 2*location + 1;
		int right = 2*location + 2;
		int current = array[location];
		
		if(left > (size - 1)) { 
			// Reached a leaf node
			return;
		} else if(left == (size - 1)) { 
			// Node with one child
			if(current < array[left]) {
				swap(location, left);
			}
		} else { // Node with two children
			 if(array[left] < array[right]) {
				 // Right value is bigger
				 swapWith = right;
			 } else {
				 // Left value is bigger
				 swapWith = left;
			 }
			 
			 // If current is less than the value to swap with,
			 // swap the values then recursively call sink on 
			 // the swapWith element
			 if(current < array[swapWith]) {
				 swap(location, swapWith);
				 sink(swapWith);
			 }
		}
	}
}






















