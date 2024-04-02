package heap;

public class Heapify {
	private int[] heap = new int[1000];
	private int size = 0;
	private int swapCounter = 0;
	
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
	
	public void heapify(int current) {
		System.out.println("in heapify");
		// If current node is not a leaf
		if(!isLeaf(current)) {
			System.out.println("1");
			// If value of current < value of left child 
			// OR if value of current < value of right child
			if(heap[current] < heap[leftChild(current)] || heap[current] < heap[rightChild(current)]) {
				System.out.println("2");

				if(heap[leftChild(current)] > heap[rightChild(current)]) {
					System.out.println("3a");

					swap(current, leftChild(current));
					heapify(leftChild(current));
				} else {
					System.out.println("3b");

					swap(current, rightChild(current));
					heapify(rightChild(current));
				}
			}
		}
	}
	
	public void insert(int data) {
		if(isFull()) {
			System.out.println("Heap is full! Cannot add " + data);
			return;
		}
		
		heap[size] = data;
		size++;
//		System.out.println("size: " + size);
	}
	
    public void downHeapify(int pos) {
        if (pos >= (size / 2) && pos <= size)
            return;

        if (heap[pos] < heap[leftChild(pos)] ||
                heap[pos] < heap[rightChild(pos)]) {

            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                downHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                downHeapify(rightChild(pos));
            }
        }
    }
    
    public void heapifyUp(int pos) {
        int temp = heap[pos];
        while(pos>0 && temp > heap[parent(pos)]){
            heap[pos] = heap[parent(pos)];
            pos = parent(pos);
        }
        heap[pos] = temp;
    }
    
	public void sink(int location) {
		int swapWith;
		int left = 2*location + 1;
		int right = 2*location + 2;
		int current = heap[location];
		
		if(left > (size - 1)) { 
			// Reached a leaf node
			return;
		} else if(left == (size - 1)) { 
			// Node with one child
			if(current < heap[left]) {
				swap(location, left);
			}
		} else { // Node with two children
			 if(heap[left] < heap[right]) {
				 // Right value is bigger
				 swapWith = right;
			 } else {
				 // Left value is bigger
				 swapWith = left;
			 }
			 
			 // If current is less than the value to swap with,
			 // swap the values then recursively call sink on 
			 // the swapWith element
			 if(current < heap[swapWith]) {
				 swap(location, swapWith);
				 sink(swapWith);
			 }
		}
	}
	
    public void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;
 
        if (heap[pos] < heap[leftChild(pos)]
            || heap[pos] < heap[rightChild(pos)]) {
 
            if (heap[leftChild(pos)]
                > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }
    // Function to build a Max-Heap from the Array
    public void buildHeap() {
        // Index of last non-leaf node
//        int startIdx = (N / 2) - 1;
 
        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = 0; i < heap.length; i++) {
        	maxHeapify(0);
        }
    }
	
	public void printHeap() {
		System.out.println();
		for(int i = 0; i < size; i++) {
			System.out.println("heap[" + i + "]: " + heap[i]);
		}
	}
	
}
