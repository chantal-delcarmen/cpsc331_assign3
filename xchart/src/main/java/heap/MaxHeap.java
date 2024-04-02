package heap;

/**
 * Class copied over from tutorial files
 */
public class MaxHeap {
    private int[] Heap; // Array to store heap elements
    private int size;
    private int maxsize;

    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE; // Sentinel value at the 0th index to make indexing easier
    }

    // Returns the index of the parent for the node at index pos
    private int parent(int pos) {return pos / 2; }

    // Returns the index of the left child for the node at index pos
    private int leftChild(int pos) { return (2 * pos); }

    // Returns the index of the right child for the node at index pos
    private int rightChild(int pos) { return (2 * pos) + 1; }

    // Checks if the node at index pos is a leaf node
    private boolean isLeaf(int pos) { return pos > (size / 2) && pos <= size; }

    // Swaps two nodes of the heap at indexes fpos and spos
    private void swap(int fpos, int spos) {
        int tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Heapifies the node at index pos
    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {
                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    // Inserts a new element into the heap
    public void insert(int element) {
        if (size >= maxsize) {
            return; // Heap is full
        }
        Heap[++size] = element;
        int current = size;

        // Move the new element up to maintain the heap property
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Removes and returns the maximum element from the heap
    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }

    public boolean isFull() { return size == maxsize; }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public int getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return Heap[1];
    }

//    public static void main(String[] args) {
//        MaxHeap maxHeap = new MaxHeap(10);
//        maxHeap.insert(5);
//        maxHeap.insert(3);
//        maxHeap.insert(17);
//        maxHeap.insert(10);
//        maxHeap.insert(84);
//        maxHeap.insert(19);
//        maxHeap.insert(6);
//        maxHeap.insert(22);
//        maxHeap.insert(9);
//
//        System.out.println("The Max val is: " + maxHeap.getMax());
//
//        System.out.println("Removing max...");
//        while (!maxHeap.isEmpty()) {
//            int maxVal = maxHeap.remove();
//            System.out.println(maxVal + " removed, now max is: " + (maxHeap.isEmpty() ? "none" : maxHeap.getMax()));
//        }
//    }
}