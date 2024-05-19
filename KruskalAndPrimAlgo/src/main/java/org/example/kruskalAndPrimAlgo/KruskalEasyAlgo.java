package org.example.kruskalAndPrimAlgo;

import java.util.*;

/**
 * Task text:
 * Kruskal's approach
 * One junior developer decided to implement Kruksal's approach to solve MST problem. The developer wrote almost all code except the one line. Help developer finish the implementation.
 * You should write down only one code line in method getKruskalMST() in Graph class.
 * [ALERT-primary]

 * Go through the code accurate, read comments to understand the implementation and think what is one significant action wasn't written.

 * What should we do if current edge does not create a cycle, according to Kruskal's algorithm?

 * [/ALERT-primary]

 * Here is an image of graph:

 * Task input data:
 * List of edges
 * Source node - destination node (edge weight)
 * 0 - 1: 2
 * 0 - 3: 3
 * 0 - 2: 4
 * 1 - 3: 1
 * 2 - 3: 3

 * Task output
 * 1 - 3 : 1
 * 0 - 1 : 2
 * 2 - 3 : 3
 */

public class KruskalEasyAlgo {
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
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
    static class Graph {
        public static List<Edge> getKruskalMST(List<Edge> edges, int numNodes) {
            // Create a disjoint "parentNodes" set to keep track of connected components
            int[] parentNodes = new int[numNodes];
            for (int i = 0; i < numNodes; i++) {
                // Initialize each node is a parent of its own
                parentNodes[i] = i;
            }

            edges.sort(Comparator.comparingInt(e -> e.weight));
            List<Edge> mstEdges = new ArrayList<>();

            for (Edge edge : edges) {
                // Search the parent of the source and destination node of the edge
                int sourceParentNode = findParentNode(edge.source, parentNodes);
                int destinationParentNode = findParentNode(edge.destination, parentNodes);

                // If the nodes do not have the same parent, then we can add this edge
                // Because it will not create a cycle
                if (sourceParentNode != destinationParentNode) {
                    // !!! Do not modify the code above !!!

                    // Write one line of code here

                    // !!! Do not modify the code below !!!

                    // HIDE THIS TEXT
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

        // A method that finds the parent of a node in the disjoint set
        private static int findParentNode(int x, int[] parent) {
            // If the node is not its own parent, recursively find the parent of its parent
            if (parent[x] != x) {
                parent[x] = findParentNode(parent[x], parent);
            }
            // Return the parent of the node
            return parent[x];
        }
    }


    // Define a class for representing edges
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

}
