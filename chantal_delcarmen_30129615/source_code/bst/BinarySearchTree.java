package bst;

/**
 * 
 * Code reused and modified from tutorial notes 
 */

public class BinarySearchTree<T extends Comparable<T>> {
	class Node {
		T data = null;
		Node left = null;
		Node right = null;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node root = null;
	private int size = 0;
	
//	public Node search(Node root, int key) {
//		if(root == null || root.data == key) {
//			return root;
//		}
//		
//		if (root.data < key) {
//			return search(root.right, key);
//		}
//		
//		return search(root.left, key);
//	}
	public void setRoot(Node node) {
		this.root = node;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public int getSize() {
		return size;
	}
	
    public boolean contains(T element) {
        return containsRecursive(root, element);
    }

    private boolean containsRecursive(Node current, T element) {
        if (current == null) {
            return false;
        }

        if (element.compareTo(current.data) == 0) {
            return true;
        }

        return element.compareTo(current.data) < 0
            ? containsRecursive(current.left, element)
            : containsRecursive(current.right, element);
    }

    public void add(T element) {
        root = addRecursive(root, element);
    }

    private Node addRecursive(Node current, T element) {
        if (current == null) {
        	Node nn = new Node(element);
        	
            return nn;
        }

        if (element.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, element);
        } else if (element.compareTo(current.data) > 0) {
            current.right = addRecursive(current.right, element);
        } else {
            // value already exists
            return current;
        }

        return current;
    }
    
	// Check if it exists in tree
	// If does not exist already in tree, 
		// Insert node
	public void insert(T number) {
		if(!contains(number)) {
			add(number);	
			size++;
			System.out.print("\n" + number + " added | Size: " + getSize());
		} else {
			System.out.print("\n" + number + " already exists in tree, not added | Size: " + getSize());
		}
	}
	
//    public void removeOddNumbers() {
//        postorderRecursive(root);
//        if((int)root.data % 2 != 0) {
//        	remove(root.data);
//            size--;
//            System.out.print("\n" + root.data + " removed | Size: " + getSize());
//        }
//    }
//    
    public void removeOddNode(Node root) 
    { 
        if (root != null) { 
        	removeOddNode(root.left); 
     
            if ((int)root.data % 2 != 0) {            	
            	remove(root.data);
                size--;
                System.out.print("\n" + root.data + " removed | Size: " + getSize());
            }
     
            removeOddNode(root.right); 
        } 
    } 

    
    public void remove(T element) {
        root = removeRecursive(root, element);
    }

    private Node removeRecursive(Node current, T element) {
        if (current == null) {
            return null;
        }

        if (element.compareTo(current.data) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }

        if (element.compareTo(current.data) < 0) {
            current.left = removeRecursive(current.left, element);
        } else {
            current.right = removeRecursive(current.right, element);
        }
        return current;
    }
    
    private T findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }
	
	// Remove odd value nodes from tree
	
	// Insert k = 50 new random integers 
	// Check if exists in tree
	// If not in tree, repeat the following
	
		// Find height of the tree
	
		// Measure time needed for insertion process
	
		// Store values (hi, ti) in data structure
}
