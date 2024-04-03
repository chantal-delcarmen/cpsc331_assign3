package graph;

import java.util.Arrays;
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
	private int[][] matrix; 			// Adjacency matrix
	
public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	//	private static int numOfVertices = 0;	// Number of vertices
	private int numOfWarehouses = 6;
	private int numOfDeliveryLocs = 0;
	private int numOfRoads = 0;
	
//	public void addEdge(int source, int dest, int weight) {
//		Edge edge = new Edge(source, dest, weight);
//		
//	}
	
	public int[] dijkstra(int[][] graph, int startWarehouse) {
		int[] distance = new int[numOfWarehouses];
		int[] path = new int[numOfWarehouses];
		boolean[] visited = new boolean[numOfWarehouses];
		
		// Initialize and fill the arrays to default values
		// Max int value for both distance and path values and
		// false for visited array
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(path, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		
		// Distance to self is always 0
		distance[startWarehouse] = 0;
		
		int closestWarehouse = 0;
		
		// Finding closest warehouse to current warehouse for each of the warehouses
		for(int i = 0; i < numOfWarehouses; i++) {
			// Find closest warehouse
			closestWarehouse = findClosestWarehouse(distance, visited);
			
			// Mark closest warehouse as visited
			visited[closestWarehouse] = true;
			
			// Analyzing neighbouring warehouses and updating distance array when new 
			// distance is shorter than original distance
			for(int j = 0; j < numOfWarehouses; j++) {
				if(matrix[closestWarehouse][j] != 0 && visited[j] == false && distance[closestWarehouse] != Integer.MAX_VALUE) {
					int newDistance = distance[closestWarehouse] + matrix[closestWarehouse][j];
					if(newDistance < distance[j]) {
						distance[j] = newDistance;
					}
				}
			}
			
			for(int k = 0; k < numOfWarehouses; k++) {
				System.out.println("Warehouse: " + k + " and Distance from start warehouse: " + distance[k] );
			}
		}
		
		return null;
	}
	
	private int findClosestWarehouse(int[] distance, boolean[] visited) {
	// TODO Auto-generated method stub
	return 0;
}

	public static void main(String[] args) {
		
		int adjMat [] [] = {
		        {0, 2, 0, 4, 0, 0},
		        {0, 0, 3, 2, 0, 0},
		        {2, 0, 0, 0, 0, 4},
		        {0, 0, 0, 0, 2, 0},
		        {0, 0, 0, 0, 0, 1},
		        {0, 0, 0, 0, 0, 0}};
		
		Graph g = new Graph();
		g.setMatrix(adjMat);
		g.dijkstra(adjMat, 0);

		//		// --------------------------------------------------------
//		// GETTING USER INPUT
//		// Using Scanner to get input from user
//		
//		System.out.println("Please enter input.");
//		
//        Scanner in = new Scanner(System.in);
//
//        int a = in.nextInt();
//
//        int b = in.nextInt();
//
//        int c = in.nextInt();
//        
//        int d1 = in.nextInt();
//        int d2 = in.nextInt();
//        int d3 = in.nextInt();
//        
//        int e1 = in.nextInt();
//        int e2 = in.nextInt();
//        int e3 = in.nextInt();
//
//        int f1 = in.nextInt();
//        int f2 = in.nextInt();
//        int f3 = in.nextInt();
//
//        int g1 = in.nextInt();
//        int g2 = in.nextInt();
//        int g3 = in.nextInt();
//
//        int h1 = in.nextInt();
//        int h2 = in.nextInt();
//        int h3 = in.nextInt();
//
//        System.out.println("You entered:"
//        		+ "\n" + a
//        		+ "\n" + b
//        		+ "\n" + c
//        		+ "\n" + d1 + ", " + d2 + ", " + d3
//        		+ "\n" + e1 + ", " + e2 + ", " + e3
//        		+ "\n" + f1 + ", " + f2 + ", " + f3
//        		+ "\n" + g1 + ", " + g2 + ", " + g3
//        		+ "\n" + h1 + ", " + h2 + ", " + h3);

	}
}






















