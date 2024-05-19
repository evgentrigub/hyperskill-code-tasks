package org.example;

import java.util.Scanner;

/**
 * [TITLE] Roads in a city [/TITLE]

 * You are working in a city hall.
 * You were requested to get a minimum distance of a road, that connects all towns in your city area.
 * You are given these roads as matrix input. In our case:
 * 1) node - town, and edge - road between towns.
 * 2) the value at graph[i][j] represents the weight (distance) of the road between towns i and j.
 * For example, "Town 0" is connected to "Town 1" with a road of distance 2,
 * and "Town 1" is connected to "Town 2" with a road of distance 3.

 * Your colleague almost has finished implementation using Prim's approach.
 * Unfortunately, method getMinWeightNode() works incorrectly.
 * Investigate the code and fix an issue in the getMinWeightNode().
 * The rest of the code works fine.
 */

public class PrimEasyAlgo {
    public static void demo() {
//        Scanner scanner = new Scanner(System.in);
//
//        int towns = scanner.nextInt();
//        int[][] graph = new int[towns][towns];
//
//        for(int i=0; i<towns; i++){
//            for(int j=0; j<towns; j++){
//                graph[i][j] = scanner.nextInt();
//            }
//        }

//        int towns = 2;
//        int[][] graph = {
//                {0, 2},
//                {2, 0}
//        };

//        int towns = 3;
//        int[][] graph = {
//                {0, 2, 3},
//                {2, 0, 4},
//                {3, 4, 0}
//        };

//        int towns = 5;
//        int[][] graph = {
//                {0, 2, 0, 6, 0},
//                {2, 0, 3, 8, 5},
//                {0, 3, 0, 0, 7},
//                {6, 8, 0, 0, 9},
//                {0, 5, 7, 9, 0}
//        };

        int towns = 6;
        int[][] graph = {
                {0, 2, 0, 6, 0, 0},
                {2, 0, 3, 8, 5, 0},
                {0, 3, 0, 0, 7, 4},
                {6, 8, 0, 0, 9, 1},
                {0, 5, 7, 9, 0, 3},
                {0, 0, 4, 1, 3, 0}
        };
        primMST(graph, towns);
    }

    public static void primMST(int[][] graph, int numNodes) {

        // Create arrays with
        // 1) a parent of each node
        int[] nodeParents = new int[numNodes];
        // 2) a minimum weight of each edge
        int[] weights = new int[numNodes];
        // 3) nodes that was visited
        boolean[] visitedNodes = new boolean[numNodes];

        // Create weights with max value and non visited
        for (int i = 0; i < numNodes; i++) {
            weights[i] = Integer.MAX_VALUE;
            visitedNodes[i] = false;
        }

        // The first node has no parent
        nodeParents[0] = -1;
        weights[0] = 0;

        // Update the weight and parent arrays for neighbour nodes
        // that have not been visited yet
        for (int count = 0; count < numNodes - 1; count++) {
            // Choose the minimum weight node from the set of nodes not yet visited
            int minWeightNode = getMinWeightNode(weights, visitedNodes);

            // Mark the node as visited
            visitedNodes[minWeightNode] = true;

            // Update the weight and parent arrays for neighbour nodes that have not been visited yet
            for (int node = 0; node < numNodes; node++) {

                // Update the weight of node if:
                // 1) it is not visited yet
                boolean isVisited = visitedNodes[node];
                // 2) it is neighbour to minWeightNode
                boolean isNeighbourNode = graph[minWeightNode][node] != 0;
                // 3) its weight is smaller than current weight
                boolean isWeightSmaller = graph[minWeightNode][node] < weights[node];

                if (!isVisited &&  isNeighbourNode && isWeightSmaller) {
                    nodeParents[node] = minWeightNode;
                    weights[node] = graph[minWeightNode][node];
                }
            }
        }

        // Print the minimum spanning tree
        System.out.println("Minimum spanning tree by Prim's algorithm");
        System.out.println("Source - Destination (Weight)");
        for (int numNode = 1; numNode < numNodes; numNode++) {
            System.out.printf("%s - %s (%s)\n",
                    nodeParents[numNode],
                    numNode,
                    graph[numNode][nodeParents[numNode]]
            );
        }
    }


    // Find an error and fix "getMinWeightNode()" method
    // Don't change the rest of code
    public static int getMinWeightNode(int[] weights, boolean[] visited) {
        // initialize the index of the minimum weight node to -1
        int minNode = -1;
        // initialize the minimum weight to infinity
        int minWeight = Integer.MAX_VALUE;

        // loop through all nodes
        for (int node = 0; node < weights.length; node++) {
            // if the node is not visited and its weight is less than the minimum weight
            if (!visited[node] && weights[node] < minWeight) {
                minWeight = node;
                minNode = weights[node];

                // HIDE CODE BELOW
//                minWeight = weights[node]; // update the minimum weight value
//                minNode = node; // update the index of the minimum weight node
            }
        }

        return minNode; // return the index of the node with the minimum weight value
    }
}

