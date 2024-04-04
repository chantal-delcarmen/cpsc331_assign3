package graph;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Code copied and modified from class slides
 */
class Result {
	int[] distArr;
	int[] pathArr;
	
	public Result(int[] distArr, int[] pathArr) {
		this.distArr = distArr;
		this.pathArr = pathArr;
	}
	
	public int[] getDistArray() {
		return distArr;
	}

	public void setDistArray(int[] distArr) {
		this.distArr = distArr;
	}
	
	public int[] getPathArray() {
		return pathArr;
	}

	public void setPathArray(int[] pathArr) {
		this.pathArr = pathArr;
	}

}

public class Graph {
	private int numOfVertices = 0; // Number of vertices
	private int numOfWarehouses = 1; // Always 1
	private int numOfDeliveryLocs = 0;
	private int numOfRoads = 0;
	private int[][] matrix; // Adjacency matrix
	private int[] distance = new int[numOfVertices];
	private int[] path = new int[numOfVertices];
	
	// Setters and getters of variables
	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	public int[] getDistance() {
		return distance;
	}

	public void setDistance(int[] distance) {
		this.distance = distance;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int getNumOfVertices() {
		return numOfVertices;
	}

	public void setNumOfVertices(int numOfVertices) {
		this.numOfVertices = numOfVertices;
	}
	
	public int getNumOfWarehouses() {
		return numOfWarehouses;
	}

	public void setNumOfWarehouses(int numOfWarehouses) {
		this.numOfWarehouses = numOfWarehouses;
	}
	
	public int getNumOfDeliveryLocs() {
		return numOfDeliveryLocs;
	}

	public void setNumOfDeliveryLocs(int numOfDeliveryLocs) {
		this.numOfDeliveryLocs = numOfDeliveryLocs;
	}

	public int getNumOfRoads() {
		return numOfRoads;
	}

	public void setNumOfRoads(int numOfRoads) {
		this.numOfRoads = numOfRoads;
	}

	private void addRoad(int start, int dest, int distance) {
		matrix[start][dest] = distance;
	}
	

	// Method to create the adjacency matrix
	private int[][] createMatrix() {
		setNumOfVertices(getNumOfWarehouses() + getNumOfDeliveryLocs());
		matrix = new int[getNumOfVertices()][getNumOfVertices()];
		return matrix;
	}
	
	// Dijkstras algorithm function
	public Result dijkstra(int[][] graph, int startWarehouse) {
		distance = new int[numOfVertices];
		path = new int[numOfVertices];
		Result res;		
		boolean[] visited = new boolean[numOfVertices];
		
		// Initialize and fill the arrays to default values
		// Max int value for both distance and path values and
		// false for visited array
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(path, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		
		// Distance to self is always 0
		distance[startWarehouse] = 0;
		
		int closestLoc = 0;
		
		// Finding closest location to current location for each of the locations
		for(int i = 0; i < numOfVertices; i++) {
			// Find closest warehouse
			closestLoc = findClosestLoc(distance, visited);
			path[i] = closestLoc;
			
			// Mark closest location as visited
			visited[closestLoc] = true;
			
			// Analyzing neighbouring locations and updating distance array when new 
			// distance is shorter than original distance
			for(int j = 0; j < numOfVertices; j++) {
				if(matrix[closestLoc][j] != 0 && visited[j] == false && distance[closestLoc] != Integer.MAX_VALUE) {
					int newDistance = distance[closestLoc] + matrix[closestLoc][j];
					
					if(newDistance < distance[j]) {
						distance[j] = newDistance;
						path[j] = closestLoc;
					}
				}
			}
		}
		
		// Create a Result object to return distance[] and path[]
		res = new Result(distance, path);
		return res;		
	}


	// Method to find closest location
	private int findClosestLoc(int[] distance, boolean[] visited) {
		int closestVertex = -1;
		
		for(int i = 0; i < distance.length; i++) {
			if(visited[i] == false && (closestVertex == -1 || distance[i] < distance[closestVertex])) {
				closestVertex = i;
			}
		}
		return closestVertex;
	}
	
	// Method to print to output
	private void printOutput(int[] distArray, int[] pathArray) {
		for(int i = 0; i < path.length; i++) {
			System.out.print("\nDelivery Location " + i + " - Shortest Route: " + pathArray[i]);
			System.out.print(", Distance: " + distArray[i]);

		}
	}

	public static void main(String[] args) {

		// --------------------------------------------------------
		// GETTING USER INPUT
		// Using Scanner to get input from user
		
		Graph g = new Graph();		
        Scanner in = new Scanner(System.in);

		System.out.println("Please enter input:");

		// Ask for user input for numOfWarehouses, numOfDeliveryLocs, numOfRoads 
        g.setNumOfWarehouses(in.nextInt());
        g.setNumOfDeliveryLocs(in.nextInt());
        g.setNumOfRoads(in.nextInt());
        
        // Create the adjacency matrix from the given user input
        int[][] adjMatrix = g.createMatrix();
        System.out.println();
        
        // Ask for input for # of roads - start, destination, distance
        for(int i = 0; i < g.getNumOfRoads(); i++) {
            g.addRoad(in.nextInt(), in.nextInt(), in.nextInt());
        }
        
        Result result = g.dijkstra(adjMatrix, 0);
        
        g.printOutput(result.getDistArray(), result.getPathArray());
        
	} // End main

}


		























