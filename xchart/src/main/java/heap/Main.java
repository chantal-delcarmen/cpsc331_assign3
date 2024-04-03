package heap;

import java.util.Random;

/**
 * Some code copied and modified from lecture / tutorial notes
 */

public class Main {
	private static int maxSize = 1000;	 
	private static int swapCounter = 0;	
	private static int sortedHeapifySwapCounter = 0;
	private static int randomHeapifySwapCounter = 0;
	private static int sortedOneByOneSwapCounter = 0;
	private static int randomOneByOneSwapCounter = 0;	
	private static int[] sortedArray = new int[maxSize];
	private static int[] randomArray = new int [maxSize];
	private static int[] sortedHeapifyHeap = new int[maxSize];
	private static int[] randomHeapifyHeap = new int[maxSize];
	private static int[] sortedOneByOneHeap = new int[maxSize];
	private static int[] randomOneByOneHeap = new int[maxSize];
	private static int size = 0;
	private static Random rand = new Random();
	
    // Returns the index of the parent for the node at index pos
    private static int parent(int pos) {return pos / 2; }

    // Returns the index of the left child for the node at index pos
    private static int leftChild(int pos) { return (2 * pos); }

    // Returns the index of the right child for the node at index pos
    private static int rightChild(int pos) { return (2 * pos) + 1; }

    // Checks if the node at index pos is a leaf node
    private static boolean isLeaf(int pos) { return pos > (size / 2) && pos <= size; }


	private static int[] getRandomArray(int length) {
		int[] array = new int[length];
		
		for(int i = 0; i < length; i++) {	
			array[i] = rand.nextInt(1000);
		}
		return array;
	}

	private static int[] getSortedArray(int length) {
		int[] array = new int[length];
		
		for(int i = 0; i < length; i++) {
			array[i] = i;
		}
		
		return array;
	}
	
	private static int[] buildHeap(int[] array, int length) {
		swapCounter = 0;

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
	}
	
	private static void insert(int[] heap, int data) {
		
        if (size >= maxSize) {
        	System.out.println("Heap is full!");
            return; // Heap is full
        }
        
        heap[size] = data;
        int current = size;
        
        // Move the new element up to maintain the heap property
        while (heap[current] > heap[parent(current)]) {
            swap(heap, current, parent(current));
            current = parent(current);
        }	
        size++;
	}
	

	private static int[] buildRandomOneByOne() {
		// Reset swap counter again
		swapCounter = 0;
		size = 0;
		
		int[] array = new int[maxSize];

		for(int i = 0; i < maxSize; i++) {
			insert(array, rand.nextInt(1000));
		}
		
		return array;
	}

	private static int[] buildSortedOneByOne() {
		// Reset swap counter again
		swapCounter = 0; 
		size = 0;

		int[] array = new int[maxSize];
		
		for(int i = 0; i < maxSize; i++) {
			insert(array, i);
		}
				
		return array;
	}
	

	private static void heapSort(int[] heap) {
		int[] sortedArray = new int[heap.length];
		
		for(int i = (heap.length - 1); i >= 0; i--) {
			sortedArray[i] = remove(heap, 0, i);
		}
	}

	private static int remove(int[] heap, int root, int current) {
		int value = heap[root];
		heap[root] = heap[current];		
		heapify(heap, current, root);
		return value;
	}
	
	public static void main(String[] args) {
		
		// -----------------------------------------------------------------
		// BUILDING HEAPIFY HEAPS AND CALCULATING SWAPS 
		
		// Build heap for sorted heapify heap
		sortedArray = getSortedArray(sortedArray.length);
		sortedHeapifyHeap = buildHeap(sortedArray, sortedArray.length);
		sortedHeapifySwapCounter = swapCounter;

		
		// Build heap for random heapify heap
		randomArray = getRandomArray(randomArray.length);
		randomHeapifyHeap = buildHeap(randomArray, randomArray.length);
		randomHeapifySwapCounter = swapCounter;
		
		// -----------------------------------------------------------------
		// BUILDING ONE-BY-ONE HEAPS AND CALCULATING SWAPS 

		// Build heap for sorted one by one heap
		sortedOneByOneHeap = buildSortedOneByOne();
		sortedOneByOneSwapCounter = swapCounter;
		
		// Build heap for random one by one heap
		randomOneByOneHeap = buildRandomOneByOne();
		randomOneByOneSwapCounter = swapCounter;
		
		// -----------------------------------------------------------------
		// HEAP SORTING RANDOM HEAPS AND CALCULATING TOTAL SWAPS 
		
		// Random Heapify Heap
		swapCounter = 0;
		heapSort(randomHeapifyHeap);
		int heapifySwapSum = randomHeapifySwapCounter + swapCounter;

		// Random One by One Heap
		swapCounter = 0;
		heapSort(randomOneByOneHeap);
		int onebyoneSwapSum = randomOneByOneSwapCounter + swapCounter;
		
		// -----------------------------------------------------------------
		// OUTPUT
		
		// Part 1, Section B
		System.out.println("\nPart 1, Section B -  Sorted");
		System.out.println("1. Heapify Swap Counter: " + sortedHeapifySwapCounter);
		System.out.println("2. OneByOne Swap Counter: " + sortedOneByOneSwapCounter);

		// Part 2, Section B
		System.out.println("\nPart 2, Section B - Random -> HeapSort");
		System.out.println("1. Heapify Swap Counter: " + heapifySwapSum);
		System.out.println("2. OneByOne Swap Counter: " + onebyoneSwapSum);

		
	} // End main method

}
