package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArrayManipulationAlgorithm {
    // Complete the arrayManipulation function below.
    static long arrayManipulation(final int n,
                                  final int[][] queries) {
        var outputArray = new long[n + 2];
        IntStream.range(0, queries.length).forEach(i -> {
            var a = queries[i][0];
            var b = queries[i][1];
            var k = queries[i][2];
            outputArray[a] += k;
            outputArray[b + 1] -= k;
        });
        return getMax(outputArray);
    }

    private static long getMax(long[] inputArray) {
        var max = Long.MIN_VALUE;
        var sum = 0;
        for (var number : inputArray) {
            sum += number;
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nm = scanner.nextLine().split(" ");
            var n = Integer.parseInt(nm[0]);
            var m = Integer.parseInt(nm[1]);
            var queries = new int[m][3];
            for (int i = 0; i < m; i++) {
                String[] queriesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (var j = 0; j < 3; j++) {
                    var queriesItem = Integer.parseInt(queriesRowItems[j]);
                    queries[i][j] = queriesItem;
                }
            }
            var result = arrayManipulation(n, queries);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
