package bst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

/**
 * Code reused and modified from tutorial notes 
 */

public class BinarySearchTree<T extends Comparable<T>> {
	// Declare and initialize variables
	private Node root = null;
	private int size = 0;
	private List<Double> timeValues = new ArrayList<>();
	private List<Double> heightValues = new ArrayList<>();
	private int arraySize = 50;
	private double[] xData = new double[arraySize];
	private double[] yData = new double[arraySize];
	
	// Define Node class
	class Node {
		T data = null;
		Node left = null;
		Node right = null;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	// Set root node
	public void setRoot(Node node) {
		this.root = node;
	}
	
	// Get root node
	public Node getRoot() {
		return root;
	}
	
	// Get size
	public int getSize() {
		return size;
	}
	
	// Contains method which calls recursive containsRecursive
	// Returns true or false for whether the given element is in tree
    public boolean contains(T element) {
        return containsRecursive(root, element);
    }

    // containsRecursive method which takes current node and value
    private boolean containsRecursive(Node current, T element) {
    	// If the current node is null, return false
        if (current == null) {
            return false;
        }

        // If given value is equivalent to the current value, 
        // return true
        if (element.compareTo(current.data) == 0) {
            return true;
        }
        
        // If given element < current value, call containsResursive on left subtree
        // Else, given element is > 0, call containsRecursive on right subtree
        return element.compareTo(current.data) < 0
            ? containsRecursive(current.left, element)
            : containsRecursive(current.right, element);
    }

    // Method to add element to the tree
    public void add(T element) {
        root = addRecursive(root, element);
    }

    // Recursive add method, takes current node and value
    private Node addRecursive(Node current, T element) {
    	
    	// If current is null, create a new node with the given element
    	// and return this new node
        if (current == null) {
        	Node nn = new Node(element);        	
            return nn;
        }
        
        // If element is less than current value, call recursive add on left subtree
        // which becomes current node's left child
        if (element.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, element);
        } else if (element.compareTo(current.data) > 0) {
        	// Else if element is larger than current value, then call recursive add 
        	// on right subtree. This becomes current node's right child
            current.right = addRecursive(current.right, element);
        } else {
            // Else value already exists and return current
            return current;
        }

        // Return current
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
	
	// Remove odd value nodes from tree
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

    
    // Remove method which takes the element to be removed
    public void remove(T element) {
        root = removeRecursive(root, element);
    }

    // Recursive remove method which takes current node and element to remove
    private Node removeRecursive(Node current, T element) {
    	// If current is null, return null
        if (current == null) {
            return null;
        }

        // If element is equal to current data
        if (element.compareTo(current.data) == 0) {
        	// If left and right children are null. return null
            if (current.left == null && current.right == null) {
                return null;
            }
            // If right child is null, return left child of current
            if (current.right == null) {
                return current.left;
            }
            
            // If left child is null, return right child
            if (current.left == null) {
                return current.right;
            }
            
            // Find the smallest value of the right subtree, and this becomes current value
            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }

        // Element smaller than current value, go into left subtree 
        if (element.compareTo(current.data) < 0) {
            current.left = removeRecursive(current.left, element);
        } else { // Else element is bigger than current value, go into right subtree
            current.right = removeRecursive(current.right, element);
        }
        return current;
    }
    
    // Finds the smallest value of the tree, takes in root node
    private T findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

	
	// Method that inserts k = 50 new random integers for the 2nd insertion 
	public void insert2(T number) {
		// Check that the tree is not already contained in the tree
		if(!contains(number)) {
			// Find height of the tree
			double height = findHeight(root);
			
			// Add to the ArrayList of heights
			heightValues.add(height); 
			
			// Insert ri into tree T1	
			// Measure time needed for insertion process	
			double startTime = System.nanoTime();
			add(number);	
			double endTime = System.nanoTime();
			double runTime = (endTime - startTime)/1000;
			
			// Add runtime value to the ArrayList of times
			timeValues.add(runTime);
			
			// Increment size
			size++;
			System.out.print("\n" + number + " added | Size: " + getSize());
			
		} else { // Else already exists in the tree
			System.out.print("\n" + number + " already exists in tree, not added | Size: " + getSize());
		}
	}

	// Method that finds the height of tree
	private int findHeight(Node node) {
		int leftHeight = 0;
		int rightHeight = 0;
		int height = 0;
		
		if(node == null) {
			// Doesn't change height
			height = 0;
		} else {
			leftHeight = findHeight(node.left);
			rightHeight = findHeight(node.right);
			
			if(leftHeight > rightHeight) {
				height = leftHeight + 1;
			} else {
				height = rightHeight + 1;
			}
		}
		return height;
	}

	// Plot values ti (x-axis) against hi (y-axis)
	// Using xchart library
	public void plotValues() {
		
		// Transfer time values to double array for Xchart x-axis
		int index = 0;		
		System.out.print("\nTime values: ");
		for(Double element : timeValues) {
			xData[index++] = element.doubleValue();
			System.out.print(element + " ");
		}
		System.out.println();
		
		// Transfer height values to double array for Xchart y-axis
		index = 0;
		System.out.print("\nHeight values: ");		
		for(Double element : heightValues) {
			yData[index++] = element.doubleValue();
			System.out.print(element + " ");

		}

		// Create graph using Xchart
		XYChart chart = new XYChartBuilder().width(600).height(500).title("Time vs Height").xAxisTitle("X-axis: Time (ps)").yAxisTitle("Y-axis: Height (# of nodes)").build();
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
		chart.addSeries("Coordinates", xData, yData);
		
		// Show graph
		new SwingWrapper<XYChart>(chart).displayChart();
		
		// Save graph
		try {
			BitmapEncoder.saveBitmap(chart, "./Chart", BitmapFormat.PNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
}








































