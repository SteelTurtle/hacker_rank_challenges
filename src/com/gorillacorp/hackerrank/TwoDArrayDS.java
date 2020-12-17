package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// Given a 6 X 6 2D Array, arr:
//
// 1 1 1 0 0 0
// 0 1 0 0 0 0
// 1 1 1 0 0 0
// 0 0 0 0 0 0
// 0 0 0 0 0 0
// 0 0 0 0 0 0
//
// We define an hourglass in A to be a subset of values with indices falling in this pattern in arr's
// graphical representation:
// a b c
//  d
// e f g
//
// There are 16 hourglasses in arr, and an hourglass sum is the sum of an hourglass' values. Calculate the
// hourglass sum for every hourglass in arr, then print the maximum hourglass sum.
// Input Format:
// Each of the 6 lines of inputs arr[i] contains 6 space-separated integers arr[i][j].
//
// Output Format:
// Print the largest (maximum) hourglass sum found in arr.
//
// Sample Input:
// 1 1 1 0 0 0
// 0 1 0 0 0 0
// 1 1 1 0 0 0
// 0 0 2 4 4 0
// 0 0 0 2 0 0
// 0 0 1 2 4 0
//
// Sample Output
// 19
public class TwoDArrayDS {

    // Complete the hourglassSum function below. The function returns an integer,
    // (the maximum hourglass sum in the array), given the parameter:
    // arr: an array of integers
    static int hourglassSum(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        int maxHourglassSum = Integer.MIN_VALUE;
        // loop through rows and columns - 2, because we don't want to go out of the array bounds!
        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < columns - 2; j++) {
                int hourglassSum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2] +
                        arr[i + 1][j + 1] +
                        arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                maxHourglassSum = Math.max(hourglassSum, maxHourglassSum);
            }
        }
        return maxHourglassSum;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int[][] arr = new int[6][6];
            for (int i = 0; i < 6; i++) {
                String[] arrRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (int j = 0; j < 6; j++) {
                    int arrItem = Integer.parseInt(arrRowItems[j]);
                    arr[i][j] = arrItem;
                }
            }
            int result = hourglassSum(arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}