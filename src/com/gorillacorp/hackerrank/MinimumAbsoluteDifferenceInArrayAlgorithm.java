package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// Consider an array of integers "arr = [arr[0], arr[1],....arr[n]]". We define the absolute difference
// between two elements a[i] and a[j] (where i != j) to be the absolute value of a[i] - a[j].
//
// Input Format
// The first line contains a single integer N, the size of "arr".
// The second line contains N space-separated integers "arr[i]".
//
// Sample input:
// 3
// 3 -7 0
//
// Sample output:
// 3
public class MinimumAbsoluteDifferenceInArrayAlgorithm {

    // Complete the minimumAbsoluteDifference function below. The function returns an integer that
    // represents the minimum absolute difference between any pair of elements, given the parameters:
    // n: an integer that represents the length of arr
    // arr: an array of integers
    static int minimumAbsoluteDifference(int[] arr) {
        int minAbsoluteDifference = Integer.MAX_VALUE;
        // to maximize the efficiency of the algorithm, let's first sort the "arr" array entries
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int currentAbsoluteDifference = Math.abs(arr[i] - arr[i + 1]);
            minAbsoluteDifference = Math.min(minAbsoluteDifference, currentAbsoluteDifference);
        }
        return minAbsoluteDifference;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            });
            int result = minimumAbsoluteDifference(arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
