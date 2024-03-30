package bst;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

/**
 * 
 * Code reused and modified from tutorial notes 
 */

public class BinarySearchTree<T extends Comparable<T>> {
	
	private Node root = null;
	private int size = 0;
	private List<Double> timeValues = new ArrayList<>();
	private List<Double> heightValues = new ArrayList<>();
	private int arraySize = 50;
	private double[] xData = new double[arraySize];
	private double[] yData = new double[arraySize];
	
	class Node {
		T data = null;
		Node left = null;
		Node right = null;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	
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

	
	// Insert k = 50 new random integers 
	// 		- Check if exists in tree 
	public void insert2(T number) {
		if(!contains(number)) {
			// Find height of the tree
			double height = findHeight(root);
			
			// Add to the ArrayList of heights
			heightValues.add(height); 
//			System.out.println("\nHeight added: " + height);
			
			// Insert ri into tree T1	
			// Measure time needed for insertion process	
			double startTime = System.nanoTime();
			add(number);	
			double endTime = System.nanoTime();
			double runTime = (endTime - startTime);
			
			// Add runtime value to the ArrayList of times
			timeValues.add(runTime);
//			System.out.println("Runtime added: " + runTime);
			
			size++;
			System.out.print("\n" + number + " added | Size: " + getSize());
//			System.out.printf("\nInsertion time: %08d ns", runTime);

			// Store values (ti, hi) in data structure
//			map.put(runTime, height);
			
			
		} else {
			System.out.print("\n" + number + " already exists in tree, not added | Size: " + getSize());
		}
	}

	private int findHeight(Node node) {
		int leftHeight = 0;
		int rightHeight = 0;
		int height = 0;
		
		if(node == null) {
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
			xData[index] = element.doubleValue();
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
	
		// Create Chart
//		XYChart chart = QuickChart.getChart("Time vs Height", "X-axis: Time (ns)", "Y-axis: Height (# of nodes)", "y(x)", xData, yData);
	
		XYChart chart = new XYChartBuilder().width(600).height(500).title("Time vs Height").xAxisTitle("X-axis: Time (ns)").yAxisTitle("Y-axis: Height (# of nodes)").build();
		
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
		
		chart.addSeries("Coordinates", xData, yData);
		
		// Show it
		new SwingWrapper<XYChart>(chart).displayChart();
		
	//	BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
				
	}
}








































