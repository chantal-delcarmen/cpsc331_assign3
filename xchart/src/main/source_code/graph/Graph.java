package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Input
The input consists of the following lines:
1 Number of warehouses ( always 1)
2 Number of delivery locations
3 Number of roads connecting locations (R)
4 R lines , each containing three integers : starting location , destination
location , and distance
Denote the central warehouse location as 0 and delivery locations start from 1
 */

//class Edge {
//	private int source = 0;
//	private int dest = 0;
//	private int weight = 0;
//	
//	public Edge(int source, int dest, int weight) {
//		this.source = source;
//		this.dest = dest;
//		this.weight = weight;
//	}
//}


public class Graph {
	private static int[][] matrix; 			// Adjacency matrix
//	private static int numOfVertices = 0;	// Number of vertices
	private static int numOfWarehouses = 1;
	private static int numOfDeliveryLocs = 0;
	private static int numOfRoads = 0;
	
//	public void addEdge(int source, int dest, int weight) {
//		Edge edge = new Edge(source, dest, weight);
//		
//	}
	
	public int[] dijkstra(int[][] graph, int startWarehouse) {
//		List<Integer> warehousesObtained = new ArrayList<>();
		int[] distanceArray = new int[numOfWarehouses];
		
		return null;
	}
	
	public static void main(String[] args) {
		
		// --------------------------------------------------------
		// GETTING USER INPUT
		// Using Scanner to get input from user
		
		System.out.println("Please enter input.");
		
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();

        int b = in.nextInt();

        int c = in.nextInt();
        
        int d1 = in.nextInt();
        int d2 = in.nextInt();
        int d3 = in.nextInt();
        
        int e1 = in.nextInt();
        int e2 = in.nextInt();
        int e3 = in.nextInt();

        int f1 = in.nextInt();
        int f2 = in.nextInt();
        int f3 = in.nextInt();

        int g1 = in.nextInt();
        int g2 = in.nextInt();
        int g3 = in.nextInt();

        int h1 = in.nextInt();
        int h2 = in.nextInt();
        int h3 = in.nextInt();

        System.out.println("You entered:"
        		+ "\n" + a
        		+ "\n" + b
        		+ "\n" + c
        		+ "\n" + d1 + ", " + d2 + ", " + d3
        		+ "\n" + e1 + ", " + e2 + ", " + e3
        		+ "\n" + f1 + ", " + f2 + ", " + f3
        		+ "\n" + g1 + ", " + g2 + ", " + g3
        		+ "\n" + h1 + ", " + h2 + ", " + h3);

	}
}






















