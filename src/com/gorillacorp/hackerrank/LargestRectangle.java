package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

// Skyline Real Estate Developers is planning to demolish a number of old, unoccupied buildings and
// construct a shopping mall in their place. Your task is to find the largest solid area in which the mall
// can be constructed.
// There are a number of buildings in a certain two-dimensional landscape. Each building has a height,
// given by h[i] where i (belongs to) [1, n]. If you join k adjacent buildings, they will form a solid
// rectangle of area k X min(h[i], h[i+1], h[i + k - 1).
// For example, the heights array h = [3,2,3]. A rectangle of height h = 2 and length = 3 can be constructed
// within the boundaries. The area formed is h * k = 2 * 3 = 6
//
// Input Format:
// The first line contains n, the number of buildings.
// The second line contains n space-separated integers, each representing the height of a building.
//
// Output Format:
// Print a long integer representing the maximum area of rectangle formed.
//
// Sample input:
// 5
// 1 2 3 4 5
//
// Sample output:
// 9
public class LargestRectangle {

    // Complete the largestRectangle function below. The function returns an integer
    // representing the largest rectangle that can be formed within the bounds of consecutive buildings,
    // given the parameter h (an array of integers representing building heights).
    static long largestRectangle(final int[] h) {
        Stack<int[]> stack = new Stack<>(); // Create stack of span = [v0, v1]
        var heightValues = h.length;
        var buildings = Arrays.copyOf(h, heightValues + 1); // Append a sentinel to array h
        var maxRectangleArea = 0;

        int v1;
        for (var v0 = 0; v0 <= heightValues; v0++) {
            v1 = v0;
            while (!stack.isEmpty() && (buildings[stack.peek()[0]] >= buildings[v0])) {
                var x = stack.pop();
                v1 = x[1];
                maxRectangleArea = Math.max(maxRectangleArea, buildings[x[0]] * (v0 - v1));
            }
            stack.push(new int[]{v0, v1});
        }
        return maxRectangleArea;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            var h = new int[n];
            var hItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int hItem = Integer.parseInt(hItems[i]);
                h[i] = hItem;
            });
            var result = largestRectangle(h);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
