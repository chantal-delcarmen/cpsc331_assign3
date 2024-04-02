package heap;

public class Main {
	// Implement heap sort to sort an array
	// Count the swaps required for:
	// 	- heap creation
	//	- sorting
	// * excluding removals in heap sort
	
	// heapify method:
	// build a max heap from array
	
	// method to add elements one by one:
	// construct a max heap by inserting elements 
	// individually and maintain heap property
	
	// next, heap sort will be performed
	public static void main(String[] args) {
	    System.out.println("-------------------------------------------------");
	    System.out.println("Heapify Heap");
	    
		Heapify heap1 = new Heapify();
			    
	    heap1.insert(5);
	    heap1.insert(3);
	    heap1.insert(17);
	    heap1.insert(10);
	    heap1.insert(84);
	    heap1.insert(19);
	    heap1.insert(6);
	    heap1.insert(22);
	    heap1.insert(9);
	    
	    heap1.printHeap();
	    
	    System.out.println("\nHeapifying...\n");
//	    heap1.heapify(0);
//	    heap1.sink(0);
//	    heap1.maxHeapify(0);
//	    heap1.heapifyUp(8);
	    heap1.buildHeap();
	    heap1.printHeap();
		
	    System.out.println("-------------------------------------------------");
	    System.out.println("OneByOne Heap\n");
	    
	    OneByOne heap2 = new OneByOne();
	    
	    heap2.insert(5);
	    heap2.insert(3);
	    heap2.insert(17);
	    heap2.insert(10);
	    heap2.insert(84);
	    heap2.insert(19);
	    heap2.insert(6);
	    heap2.insert(22);
	    heap2.insert(9);
	    
	    heap2.printHeap();
	}

}
