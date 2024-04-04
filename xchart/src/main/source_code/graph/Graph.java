package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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

class Result {
	int[] distArr;
	int[] pathArr;	
	List<Integer> shortestPathList;
	
	public Result(int[] distArr, int[] pathArr) {
		this.distArr = distArr;
		this.pathArr = pathArr;
//		this.shortestPathList = shortestPathList;
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
	
//	public List<Integer> getshortestPathList() {
//		return shortestPathList;
//	}

}

public class Graph {
	private int numOfVertices = 0; // Number of vertices
	private int numOfWarehouses = 1; // Always 1
	private int numOfDeliveryLocs = 0;
	private int numOfRoads = 0;
	private int[][] matrix; // Adjacency matrix
	private int[] distance = new int[numOfVertices];
	private int[] path = new int[numOfVertices];
	private List<Integer> shortestPathList;
	
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
	

	private int[][] createMatrix() {
		setNumOfVertices(getNumOfWarehouses() + getNumOfDeliveryLocs());
		matrix = new int[getNumOfVertices()][getNumOfVertices()];
		return matrix;
	}
	
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
		
		res = new Result(distance, path);
		return res;		
	}


	private int findClosestLoc(int[] distance, boolean[] visited) {
		int closestVertex = -1;
		
		for(int i = 0; i < distance.length; i++) {
			if(visited[i] == false && (closestVertex == -1 || distance[i] < distance[closestVertex])) {
				closestVertex = i;
			}
		}
		return closestVertex;
	}
	
	private void printOutput(int[] distArray, int[] pathArray) {
		
		// Create shortest path list
		List<Integer> shortestPathList = new ArrayList<Integer>();
		int start = 0;		
		int destination = 0;	
		int current;
		

		
//		System.out.println("shortest path:");
//		for(Integer i : shortestPathList) {
//			System.out.println(i);
//		}

		
		for(int i = 0; i < path.length; i++) {
			System.out.print("\nDelivery Location " + i); 
			System.out.print(" - Shortest Route: ");
			
			current = i;
			
			while (current != start && path[current] != -1) {
				shortestPathList.add(0, current);
			    current = path[current];
			}

			if (current == start) {
				shortestPathList.add(0, start);	
			}
			
			for(Integer num : shortestPathList) {
				System.out.print(shortestPathList.get(num));
				System.out.print(" -> ");
			}
			
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


		























