package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

// Greg has a tree of nodes containing integer data. He wants to insert a node with some non-zero integer
// value somewhere into the tree. His goal is to be able to cut two edges and have the values of each of the
// three new trees sum to the same amount. This is called a balanced forest. Being frugal, the data value he
// inserts should be minimal. Determine the minimal amount that a new node can have to allow creation of a
// balanced forest. If it's not possible to create a balanced forest, return -1.
//
// Input Format
// The first line contains a single integer, q, the number of queries.
// Each of the following q sets of lines is as follows:
// The first line contains an integer, n, the number of nodes in the tree.
// The second line contains n space-separated integers describing the respective
// values of c[1], c[2],..c[n], where each c[i] denotes the value at node i.
// Each of the following n - 1 lines contains two space-separated integers, x[j] and y[j],
// describing edge j connecting nodes x[j] and y[j].
//
// Output Format:
// For each query, return the minimum value of the integer c[w]. If no such value exists,
// return -1 instead.
//
// Sample Input:
// 2
// 5
// 1 2 2 1 1
// 1 2
// 1 3
// 3 5
// 1 4
// 3
// 1 3 5
// 1 3
// 1 2
//
// Sample Output:
// 2
// -1
public class BalancedForest {

    static long minimumNodeValue;
    static long sum;
    static Set<Long> s = new HashSet<>();
    static Set<Long> q = new HashSet<>();

    // Complete the balancedForest function below. The function returns an integer representing the minimum
    // value of c[w] that can be added to allow creation of a balanced forest, or -1 if it is not possible.
    // The function parameters are:
    // c: an array of integers, the data values for each node
    // edges: an array of 2 element arrays, the node pairs per edge
    static long balancedForest(final long[] c, final int[][] edges) {
        s = new HashSet<>();
        q = new HashSet<>();
        var nodes = Arrays.stream(c).mapToObj(Node::new).collect(toList());
        Arrays.stream(edges).forEach(currentEdge -> {
            var a = currentEdge[0] - 1;
            var b = currentEdge[1] - 1;
            nodes.get(a).adjacentNodes.add(b);
            nodes.get(b).adjacentNodes.add(a);
        });
        minimumNodeValue = sum = depthFirstSearch(0, nodes);
        solve(0, nodes);
        return minimumNodeValue == sum ? -1 : minimumNodeValue;
    }

    private static void solve(final int p, final List<Node> nodes) {
        var node = nodes.get(p);
        if (node.isRevisited) return;
        node.isRevisited = true;
        var y = new long[]{3 * node.cost - sum, (sum - node.cost) / 2 - node.cost};
        if (sum % 2 == 0 && node.cost == (sum / 2))
            minimumNodeValue = Math.min(minimumNodeValue, sum / 2);
        if (s.contains(node.cost) && y[0] >= 0)
            minimumNodeValue = Math.min(minimumNodeValue, y[0]);
        if ((s.contains(sum - 2 * node.cost) || q.contains(sum - node.cost)) && y[0] >= 0)
            minimumNodeValue = Math.min(minimumNodeValue, y[0]);
        if ((sum - node.cost) % 2 == 0
                && (s.contains((sum - node.cost) / 2)
                || q.contains((sum + node.cost) / 2)))
            if (y[1] >= 0) minimumNodeValue = Math.min(minimumNodeValue, y[1]);
        q.add(node.cost);
        IntStream.range(0, node.adjacentNodes.size()).forEach(i -> solve(node.adjacentNodes.get(i), nodes));
        q.remove(node.cost);
        s.add(node.cost);
    }

    private static long depthFirstSearch(final int p, final List<Node> nodes) {
        var node = nodes.get(p);
        if (node.isVisited) return 0;
        node.isVisited = true;
        for (var i = 0; i < node.adjacentNodes.size(); i++)
            node.cost += depthFirstSearch(node.adjacentNodes.get(i), nodes);
        return node.cost;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qIter = 0; qIter < q; qIter++) {
                var n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                var c = new long[n];
                var cItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                IntStream.range(0, n).forEach(i -> {
                    var cItem = Integer.parseInt(cItems[i]);
                    c[i] = cItem;
                });
                var edges = new int[n - 1][2];
                for (var i = 0; i < n - 1; i++) {
                    var edgesRowItems = scanner.nextLine().split(" ");
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    for (var j = 0; j < 2; j++) {
                        var edgesItem = Integer.parseInt(edgesRowItems[j]);
                        edges[i][j] = edgesItem;
                    }
                }
                var result = balancedForest(c, edges);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }

        }
    }

    private static class Node {
        long cost;
        boolean isVisited = false;
        boolean isRevisited = false;
        List<Integer> adjacentNodes = new ArrayList<>();

        public Node(long cost) {
            this.cost = cost;
        }
    }
}
