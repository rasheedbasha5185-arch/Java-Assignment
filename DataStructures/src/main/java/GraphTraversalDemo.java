import java.util.*;

class Graph {
    private final Map<Integer, List<Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    // Add edge to the graph (undirected)
    public void addEdge(int src, int dest) {
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
        adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(src);
    }

    // Depth First Search (DFS)
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int node, Set<Integer> visited) {
        if (visited.contains(node)) return;
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            dfsHelper(neighbor, visited);
        }
    }

    // Breadth First Search (BFS)
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
}

public class GraphTraversalDemo {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Creating a simple graph
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        graph.dfs(1);
        graph.bfs(1);
    }
}
