package org.example.kruskalAndPrimAlgo;

import java.util.*;

/**
 * [TITLE] Prepare edges [/TITLE]
 * Kruskal's algorithm use separate edges to build MST.
 * Kruskal's approach has significant difference from Prim's approach.

 * Implement a method getPreparedEdges() that represents the initial step of Kruskal's algorithm.
 * As input, you get a list of edges.
 */

public class KruskalDataPrepare {

    public void demo() {
        int numNodes = 4;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 3));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(2, 3, 3));

        List<Edge> mstEdges = Graph.getKruskalMST(edges, numNodes);
        for (Edge edge : mstEdges) {
            System.out.printf("%s - %s (%s)\n",
                    edge.source, edge.destination, edge.weight);
        }
    }

    static class Graph {

        // A method that finds the parent of a node in the disjoint set
        private static int findParentNode(int x, int[] parent) {
            // If the node is not its own parent, recursively find the parent of its parent
            if (parent[x] != x) {
                parent[x] = findParentNode(parent[x], parent);
            }
            // Return the parent of the node
            return parent[x];
        }

        public static List<Edge> getKruskalMST(List<Edge> edges, int numNodes) {
            // Create a disjoint "parentNodes" set to keep track of connected components
            int[] parentNodes = new int[numNodes];
            for (int i = 0; i < numNodes; i++) {
                // Initialize each node is a parent of its own
                parentNodes[i] = i;
            }

            var sortedEdges = Helper.getPreparedEdges(edges);
            List<Edge> mstEdges = new ArrayList<>();

            for (Edge edge : sortedEdges) {
                // Search the parent of the source and destination node of the edge
                int sourceParentNode = findParentNode(edge.source, parentNodes);
                int destinationParentNode = findParentNode(edge.destination, parentNodes);

                // If the nodes do not have the same parent, then we can add this edge
                // Because it will not create a cycle
                if (sourceParentNode != destinationParentNode) {
                    // Add the edge to the list of MST edges
                    // Students should write next line
                    mstEdges.add(edge);

                    // Merge two nodes to one tree
                    // Set the parent of the first node to the parent of the second node
                    // On each step we move our tree main parent node
                    parentNodes[sourceParentNode] = destinationParentNode;
                }
            }

            return mstEdges;
        }
    }
}


class Helper {
    // SHOW ONLY THIS METHOD FOR PROBLEM
    // Implement method that prepares edges according to Kruskal's algorithm
    static List<Edge> getPreparedEdges(List<Edge> edges) {
        // Write your code here

        // HIDE THIS
//            edges.sort(Comparator.comparingInt(e -> e.weight));
        return edges;
    }
}

// !!! Do not modify the code below !!!
class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}