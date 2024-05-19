package org.example;

import java.util.*;

public class PrimAlgo {
    public void demo(){
        int numVertices = 5;
        GraphPrim graph = new GraphPrim(numVertices);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        Prim prim = new Prim();

        List<Edge> mst = prim.primMST(0, graph);
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }

    static class Prim {
        public List<Edge> primMST(int root, GraphPrim graph) {
            boolean[] visited = new boolean[graph.numVertices];
            int[] parent = new int[graph.numVertices];
            int[] key = new int[graph.numVertices];
            Arrays.fill(key, Integer.MAX_VALUE);
            PriorityQueue<Node> pq = new PriorityQueue<>(graph.numVertices, new Node());

            List<Edge> result = new ArrayList<>();

            pq.add(new Node(root, 0));
            key[root] = 0;

            while (!pq.isEmpty()) {
                int u = pq.poll().vertex;
                visited[u] = true;
                for (Edge edge : graph.adjList[u]) {
                    int v = edge.destination;
                    int weight = edge.weight;
                    if (!visited[v] && weight < key[v]) {
                        parent[v] = u;
                        key[v] = weight;
                        pq.add(new Node(v, key[v]));
                    }
                }
            }

            for (int i = 1; i < graph.numVertices; i++) {
                result.add(new Edge(parent[i], i, key[i]));
            }

            return result;
        }
    }

    static class GraphPrim {
        int numVertices;
        ArrayList<Edge>[] adjList;

        public GraphPrim(int numVertices) {
            this.numVertices = numVertices;
            this.adjList = new ArrayList[numVertices];
            for (int i = 0; i < numVertices; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjList[source].add(edge);
            edge = new Edge(destination, source, weight);
            adjList[destination].add(edge);
        }
    }

    static class Node implements Comparator<Node> {
        int vertex;
        int cost;

        public Node() {}

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }


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


