package bst;

import java.util.Random;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Main {
	
	/**
	 * Method that generates a random number between 0 and the number 
	 * passed in as maximum
	 * 
	 * @param maximum
	 * @return randomNum
	 */
	public static int generateRand(int maximum) {
		Random rand = new Random();
		int min = 0;
		int max = maximum;
		int randomNum = rand.nextInt(max - min + 1);		
		return randomNum;
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		// Insert 100 random distinct nodes to BST T0
		while(bst.getSize() < 100) {
			bst.insert(generateRand(300));			
		}
		System.out.println();
		
		// Remove all odd values from T0 -> T1
		// t1 is new tree with only even values
		bst.removeOddNode(bst.getRoot());
		System.out.println();
		
		// Insert k new random integers not existing in tree T1
		// where k = 50
		int k = 50;
		int newSize = bst.getSize() + k;
		System.out.println("\nBST size with " + k + " new integers added = " + newSize);
		
		// Generate ri random number
		
		// If ri is not in T1
			// Find height hi of tree T1
			// Insert ri into tree T1
			// Measure time ti needed for insertion procedure
			// Store values (hi, ti) into data structure (array or dictionary)
		while(bst.getSize() < newSize) {
			bst.insert2(generateRand(300));
		}
		System.out.println();
		
		// Plot values ti (x-axis) against hi (y-axis)
		bst.plotValues();
		
		// Create PDF showing plot graph and big-oh analysis for inserting an element in BST
	}
	
}
