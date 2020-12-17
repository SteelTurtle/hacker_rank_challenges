package com.gorillacorp.hackerrank;

import java.util.stream.IntStream;

// This code challenges is not from HackerRank... but I cannot remember the name of the site :D
public class CountCountries {
    private static final int[] row = {-1, -1, 0};
    private static final int[] col = {-1, 0, -1};

    private static int[] findNeighborCountries(int[][] A, int x, int y) {
        var neighborCountries = IntStream.range(0, 3).map(k -> -1).toArray();
        IntStream.range(0, 3)
                .filter(k -> x + row[k] >= 0 && y + col[k] >= 0)
                .forEach(k -> neighborCountries[k] = A[x + row[k]][y + col[k]]);
        return neighborCountries;
    }

    public static int solution(int[][] A) {
        int rows = A.length;
        int columns = A[0].length;
        int numberOfCountries = 0;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                int[] neighbors = findNeighborCountries(A, i, j);
                int currentValue = A[i][j];
                if (neighbors[1] == currentValue
                        && neighbors[2] == currentValue
                        && neighbors[0] != currentValue) numberOfCountries--;
                if (neighbors[1] != currentValue && neighbors[2] != currentValue) numberOfCountries++;
            }
        return numberOfCountries;
    }
}