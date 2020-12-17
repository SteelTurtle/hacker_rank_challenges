package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// You will be given a list of integers, "arr", and a single integer k. You must create an
// array of length k from elements of arr such that its unfairness is minimized. Call that
// array subarr. Unfairness of an array is calculated as max(subarr) - min (subarr),
// where:
// - max denotes the largest integer in subarr
// - min denotes the smallest integer in subarr
//
// Input Format:
// The first line contains an integer n, the number of elements in array arr.
// The second line contains an integer k.
// Each of the next n lines contains an integer arr[i] where 0 <= i < n.
//
// Output Format:
// An integer that denotes the minimum possible value of unfairness.
//
// Sample input:
// 7
// 3
// 10
// 100
// 300
// 200
// 1000
// 20
// 30
//
// Sample output:
// 20
public class MaxMin {
    // Complete the maxMin function below.
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int minUnfairness = arr[arr.length - 1];
        int currentNumber;
        int i = 0;
        while (i <= arr.length - k) {
            currentNumber = arr[i + k - 1] - arr[i];
            if (currentNumber <= minUnfairness) minUnfairness = currentNumber;
            i++;
        }
        return minUnfairness;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int k = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            IntStream.range(0, n).forEach(i -> {
                int arrItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                arr[i] = arrItem;
            });
            int result = maxMin(k, arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
