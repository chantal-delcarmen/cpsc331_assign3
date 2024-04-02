package heap;

import java.util.Random;

public class Main {
	private static int maxSize = 1000;	 
	private static int swapCounter = 0;	
	private static int sortedHeapifySwapCounter = 0;
	private static int randomHeapifySwapCounter = 0;
	private static int sortedOneByOneSwapCounter = 0;
	private static int randomOneByOneSwapCounter = 0;	
	private static int[] sortedArr = new int[maxSize];
	private static int[] randomArr = new int [maxSize];
	private static int[] sortedHeapifyHeap = new int[maxSize];
	private static int[] randomHeapifyHeap = new int[maxSize];
	private static int[] sortedOneByoneHeap = new int[maxSize];
	private static int[] randomOneByoneHeap = new int[maxSize];
	private static int size = 0;
	
    // Returns the index of the parent for the node at index pos
    private static int parent(int pos) {return pos / 2; }

    // Returns the index of the left child for the node at index pos
    private static int leftChild(int pos) { return (2 * pos); }

    // Returns the index of the right child for the node at index pos
    private static int rightChild(int pos) { return (2 * pos) + 1; }

    // Checks if the node at index pos is a leaf node
    private static boolean isLeaf(int pos) { return pos > (size / 2) && pos <= size; }
	
	public static void main(String[] args) {
		
		// -----------------------------------------------------------------
		// HEAPIFY 
		
		// Populate sorted array
		for(int i = 0; i < sortedArr.length; i++) {
			sortedArr[i] = i;
		}

		// Populate random array
		Random rand = new Random();
		for(int i = 0; i < randomArr.length; i++) {	
			randomArr[i] = rand.nextInt(1000);
		}
		
		// Set swapCounter to 0 and build heap for sorted array
		swapCounter = 0;
		sortedHeapifyHeap = buildHeap(sortedArr, sortedArr.length);
		sortedHeapifySwapCounter = swapCounter;
		
		// Reset swapCounter to 0 and build heap for random array
		swapCounter = 0; 
		randomHeapifyHeap = buildHeap(randomArr, randomArr.length);
		randomHeapifySwapCounter = swapCounter;
		
		// Output
		System.out.println("HEAPIFY");
		
		System.out.println("INPUT: A random array with a size of 1000");
		System.out.println(randomHeapifySwapCounter + "\n");
		
		System.out.println("INPUT: A sorted array with a size of 1000");
		System.out.println(sortedHeapifySwapCounter + "\n");
		
		// -----------------------------------------------------------------
		// ONE-BY-ONE 
		
		// Reset swap counter again
		swapCounter = 0; 
		
		for(int i=0; i < sortedOneByoneHeap.length; i++) {
			insert(sortedOneByoneHeap, i);
		}
		
		sortedOneByOneSwapCounter = swapCounter;
		
		// Reset swap counter again
		swapCounter = 0;
		
		for(int i=0; i < randomOneByoneHeap.length; i++) {
			insert(randomOneByoneHeap, rand.nextInt(1000));
		}
		
		randomOneByOneSwapCounter = swapCounter;
		
		// Output
		System.out.println("ONE BY ONE");

		System.out.println("INPUT: A random array with a size of 1000");
		System.out.println(randomOneByOneSwapCounter + "\n");
		
		System.out.println("INPUT: A sorted array with a size of 1000");
		System.out.println(sortedOneByOneSwapCounter + "\n");
		
	} // End main method

	private static void insert(int[] heap, int data) {
//		if(isFull()) {
//			return;
//		}
//		
//		// Add element at position heap[size] aka at end of array
//		heap[size] = data;
//		
//		// Set initial current and parent node
//		int current = size;
//		
//		// While current is > 0 (not the root) AND the difference 
//		// between the value of current and value of parent is > 0
//        // Move the new element up to maintain the heap property
//        while (heap[current] > heap[parent(current)]) {
//            swap(current, parent(current));
//            current = parent(current);
//        }
        if (size >= maxSize) {
            return; // Heap is full
        }
        heap[++size] = data;
        int current = size;

        // Move the new element up to maintain the heap property
        while (heap[current] > heap[parent(current)]) {
            swap(heap, current, parent(current));
            current = parent(current);
        }
        
		size++;		
	}

	private static int[] buildHeap(int[] array, int length) {
		int start = (length/2) - 1;
		
		for(int i = start; i >=0; i--) {
			heapify(array, length, i);
		}
        return array;
	}

	private static int[] heapify(int[] array, int length, int current) {
		
		int node = current;
		int left = ((2 * current) + 1);
		int right = ((2 * current) + 2);
		
		if(left < length && array[left] > array[node]) {
			node = left;
		}
		
		if(right < length && array[right] > array[node]) {
			node = right;
		}
		
        // If largest is not root
        if (node != current) {
        	swap(array, current, node);
            heapify(array, length, node);
        }
        return array;
	}
	
	private static void swap(int[] heap, int location, int parent) {
		int temp;
		temp = heap[location];
		heap[location] = heap[parent];
		heap[parent] = temp;		
		swapCounter++;
		
//		System.out.println("Swapping " + heap[parent] + " and " + heap[location]);
	}
}
