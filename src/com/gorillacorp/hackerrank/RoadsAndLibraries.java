package com.gorillacorp.hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// The Ruler of HackerLand believes that every citizen of the country should have access to a library.
// Unfortunately, HackerLand was hit by a tornado that destroyed all of its libraries and obstructed its
// roads! As you are the greatest programmer of HackerLand, the ruler wants your help to repair the roads
// and build some new libraries efficiently.
// HackerLand has n cities numbered from 1 to n. The cities are connected by m bidirectional roads. A citizen
// has access to a library if:
// - Their city contains a library.
// - They can travel by road from their city to a city containing a library.
//
// Input Format:
// The first line contains a single integer q, that denotes the number of queries.
// The subsequent lines describe each query in the following format:
// - The first line contains four space-separated integers that describe the respective values of n, m,
// clib and croad, the number of cities, number of roads, cost of a library and cost of a road.
// - Each of the next m lines contains two space-separated integers, u[i] and v[i], that describe a
//bidirectional road that connects cities u[i] and v[i].
//
// Output Format:
// For each query, print an integer that denotes the minimum cost to make libraries accessible to all the
// citizens on a new line.
//
// Sample Input
// 2
// 3 3 2 1
// 1 2
// 3 1
// 2 3
// 6 6 2 5
// 1 3
// 3 4
// 2 4
// 1 2
// 2 3
// 5 6
//
// Sample Output
// 4
// 12
public class RoadsAndLibraries {

    private static long getGraphCost(final Graph graph,
                                     final long cLib,
                                     final long cRoad) {
        if (cLib < cRoad) return cLib * graph.vector;
        var visitedLocations = new boolean[graph.vector];
        var cost = 0;
        for (var i = 0; i < graph.vector; i++)
            if (!visitedLocations[i]) {
                var x = DFSUtil(graph, visitedLocations, i);
                cost += (x - 1) * cRoad;
                cost += cLib;
            }
        return cost;
    }

    private static int DFSUtil(final Graph graph,
                               final boolean[] visited,
                               final int current) {
        var count = 1;
        visited[current] = true;
        count += graph
                .adjacencyList[current]
                .stream()
                .mapToInt(a -> a)
                .filter(a -> !visited[a])
                .map(a -> DFSUtil(graph, visited, a))
                .sum();
        return count;
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var q = scanner.nextInt();
            var a0 = 0;
            while (a0 < q) {
                var n = scanner.nextInt();
                Graph g = new Graph(n, false);
                var m = scanner.nextInt();
                var clib = scanner.nextInt();
                var croad = scanner.nextInt();
                IntStream.range(0, m).map(a1 -> scanner.nextInt()).forEach(city_1 -> {
                    var city_2 = scanner.nextInt();
                    g.insertEdge(city_1 - 1, city_2 - 1);
                });
                System.out.println(getGraphCost(g, clib, croad));
                a0++;
            }
        }
    }

    static class Graph {
        private final boolean isDirectedGraph;
        int vector;
        List<Integer>[] adjacencyList;

        Graph(final int vector, final boolean isDirectedGraph) {
            this.vector = vector;
            this.isDirectedGraph = isDirectedGraph;
            adjacencyList = new LinkedList[vector];
            IntStream.range(0, vector).forEach(i -> adjacencyList[i] = new LinkedList<>());
        }

        public void insertEdge(int a, int b) {
            adjacencyList[a].add(b);
            if (!isDirectedGraph) adjacencyList[b].add(a);
        }
    }
}