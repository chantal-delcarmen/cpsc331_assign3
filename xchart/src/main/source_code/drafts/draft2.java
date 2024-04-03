package drafts;

import java.util.*;

import graph.Graph;

class Graph {
    private int vertices;
    private int[][] adjacencyMatrix;
    public int[] num_of_multiple_shortest_paths;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
        }
        num_of_multiple_shortest_paths = new int[vertices];
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }

    public List<Integer> dijkstra(int startVertex, int endVertex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];

        priorityQueue.add(new Node(startVertex, 0));
        distances[startVertex] = 0;

        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll().vertex;
            visited[current] = true;

            for (int next = 0; next < vertices; next++) {
                if (!visited[next] && adjacencyMatrix[current][next] != Integer.MAX_VALUE &&
                        distances[current] != Integer.MAX_VALUE && distances[current] + adjacencyMatrix[current][next] < distances[next]) {
                    distances[next] = distances[current] + adjacencyMatrix[current][next];
                    parent[next] = current;
                    priorityQueue.add(new Node(next, distances[next]));
                    num_of_multiple_shortest_paths[next] = 0;
                } else if (!visited[next] && adjacencyMatrix[current][next] != Integer.MAX_VALUE &&
                        distances[current] + adjacencyMatrix[current][next] == distances[next]) {
                    num_of_multiple_shortest_paths[next]++;
                }
            }
        }

        return reconstructPath(endVertex, parent);
    }

    private List<Integer> reconstructPath(int endVertex, int[] parent) {
        List<Integer> path = new ArrayList<>();
        for (int at = endVertex; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public int findDistance(int[] path) {
        if (path.length == 0) {
            return -1;
        }
        int totalDistance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            int source = path[i];
            int destination = path[i + 1];
            if (adjacencyMatrix[source][destination] == Integer.MAX_VALUE) {
                return -1;
            }
            totalDistance += adjacencyMatrix[source][destination];
        }
        return totalDistance;
    }
//}

//public class Main {
    public static int total_paths;

    public static int[][] create_all_paths(int start, int end, int total_locations, int[] shortestPath, Graph graph) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        generatePaths(start, end, total_locations, currentPath, allPaths, shortestPath, graph);
        return convertTo2DArray(allPaths);
    }

    private static void generatePaths(int current, int end, int totalLocations, List<Integer> currentPath,
                                       List<List<Integer>> allPaths, int[] shortestPath, Graph graph) {
        currentPath.add(current);
        if (current == end) {
            if (!Arrays.equals(currentPath.stream().mapToInt(i -> i).toArray(), shortestPath)) {
                allPaths.add(new ArrayList<>(currentPath));
            }
        } else {
            for (int next = current + 1; next <= totalLocations; next++) {
                total_paths++;
                generatePaths(next, end, totalLocations, currentPath, allPaths, shortestPath, graph);
            }
        }
        currentPath.remove(currentPath.size() - 1);
    }

    private static int[][] convertTo2DArray(List<List<Integer>> allPaths) {
        int[][] result = new int[allPaths.size()][];
        for (int i = 0; i < allPaths.size(); i++) {
            List<Integer> path = allPaths.get(i);
            result[i] = new int[path.size()];
            for (int j = 0; j < path.size(); j++) {
                result[i][j] = path.get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int warehouses = scanner.nextInt();
        int numDeliveryLoc = scanner.nextInt();
        int numEdges = scanner.nextInt();

        Graph graph = new Graph(warehouses + numDeliveryLoc);

        for (int i = 0; i < numEdges; i++) {
            int startVertex = scanner.nextInt();
            int endVertex = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(startVertex, endVertex, weight);
        }

        for (int i = 1; i <= numDeliveryLoc; i++) {
            int[] shortestPath = graph.dijkstra(0, i);
            int distance = graph.findDistance(shortestPath);
            System.out.print("Delivery Location " + i + " - Shortest Route: ");
            if (distance == -1) {
                System.out.print("No route exists");
            } else if (graph.num_of_multiple_shortest_paths[i - 1] == 1) {
                boolean second_item = true;
                int[][] all_paths = create_all_paths(0, i, numDeliveryLoc, shortestPath, graph);
                total_paths = 0;
                for (int l = 0; l < all_paths.length; l++) {
                    int[] possible_path = all_paths[l];
                    if (distance == graph.findDistance(possible_path)) {
                        for (int k = 0; k < possible_path.length; k++) {
                            System.out.print(possible_path[k]);
                            if (k != possible_path.length - 1) {
                                System.out.print(" -> ");
                            }
                        }
                    }
                    total_paths++;
                }
                System.out.print(" (or ");
                for (int j = 0; j < shortestPath.length; j++) {
                    System.out.print(shortestPath[j]);
                    if (j != shortestPath.length - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println("), ");

            } else {
                for (int j = 0; j < shortestPath.length; j++) {
                    System.out.print(shortestPath[j]);
                    if (j != shortestPath.length - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.print(", ");
            }
            System.out.print("Distance: ");
            if (distance == -1) {
                System.out.println("Infinity");
                System.out.println("(Location " + i + " is unreachable from the central warehouse)");
            } else {
                System.out.println(distance);
            }
        }
    }
}
