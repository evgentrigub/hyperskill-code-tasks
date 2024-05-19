package org.example;

import java.util.*;

public class KruskalAlgo {
    public void demo(){
        // Create a graph with 5 vertices
        int numVertices = 5;
        Graph graph = new Graph(numVertices);

        graph.addEdge(0, 1, 2);     // Add edge from vertex 0 to vertex 1 with weight 2
        graph.addEdge(0, 3, 6);     // Add edge from vertex 0 to vertex 3 with weight 6
        graph.addEdge(1, 2, 3);     // Add edge from vertex 1 to vertex 2 with weight 3
        graph.addEdge(1, 3, 8);     // Add edge from vertex 1 to vertex 3 with weight 8
        graph.addEdge(1, 4, 5);     // Add edge from vertex 1 to vertex 4 with weight 5
        graph.addEdge(2, 4, 7);     // Add edge from vertex 2 to vertex 4 with weight 7
        graph.addEdge(3, 4, 9);     // Add edge from vertex 3 to vertex 4 with weight 9

        Kruskal kruskal = new Kruskal();
        List<Edge> mst = kruskal.getMstEdges(graph);

        // Print edges in minimum spanning tree
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + ": " + edge.weight);
        }
    }
    static class Kruskal {

        // Find parent of a vertex using path compression
        private int find(int[] parent, int vertex) {
            if (parent[vertex] != vertex) {
                parent[vertex] = find(parent, parent[vertex]);
            }
            return parent[vertex];
        }

        // Merge two sets using union by rank
        private void mergeSets(int[] parent, int[] rank, int x, int y) {
            int rootX = find(parent, x);
            int rootY = find(parent, y);

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        // Kruskal's algorithm to find minimum spanning tree
        public List<Edge> getMstEdges(Graph graph) {
            List<Edge> result = new ArrayList<>();
            int[] parent = new int[graph.numVertices];
            int[] rank = new int[graph.numVertices];

            // Create a disjoint set for each vertex
            for (int i = 0; i < graph.numVertices; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            // Sort edges in non-decreasing order of weight
            Collections.sort(graph.edges);

            // Iterate over each edge and add to minimum spanning tree if it does not create a cycle
            for (Edge edge : graph.edges) {
                int u = edge.source;
                int v = edge.destination;

                if (find(parent, u) != find(parent, v)) {
                    result.add(edge);
                    mergeSets(parent, rank, u, v);
                }
            }

            return result;
        }
    }


    static class Graph {
        int numVertices;
        List<Edge> edges;

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            edges.add(edge);
        }
    }

    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}
