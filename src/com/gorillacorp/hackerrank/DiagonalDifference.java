package com.gorillacorp.hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// Given a square matrix, calculate the absolute difference between the sums of its diagonals
public class DiagonalDifference {

    private static int diagonalDifference(final List<List<Integer>> compoundIntList) {

        var leftRightValue = 0;
        var rightLeftValue = 0;
        var rows = compoundIntList.size();
        var columns = compoundIntList.get(0).size();

        //counters
        var i = 0;
        var j = 0;
        var k = 0;
        var l = compoundIntList.size() - 1;
        while (i < rows && j < columns && k < rows && l >= 0) {
            leftRightValue += compoundIntList.get(i).get(j);
            rightLeftValue += compoundIntList.get(k).get(l);
            i += 1;
            j += 1;
            k += 1;
            l -= 1;
        }
        return Math.abs(leftRightValue - rightLeftValue);
    }

    /**
     * Sample input:
     * 3
     * 11 2 4
     * 4 5 6
     * 10 8 -12
     * <p>
     * Sample output:
     * 15
     */
    public static void main(String[] args) throws IOException {
        try (var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             var bufferedWriter = new BufferedWriter(new PrintWriter(System.out))) {

            var n = Integer.parseInt(bufferedReader.readLine().trim());
            var compoundIntList = new ArrayList<List<Integer>>();
            IntStream.range(0, n).forEach(i -> {
                try {
                    compoundIntList.add(
                            Stream.of(bufferedReader.readLine()
                                    .replaceAll("\\s+$", "")
                                    .split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            var result = diagonalDifference(compoundIntList);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
