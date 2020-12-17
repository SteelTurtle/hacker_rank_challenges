package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// In an array, arr, the elements at indices i and j (where i < j) form an inversion if arr[i] > arr[j]. In
// other words, inverted elements arr[i] and arr[j] are considered to be "out of order". To correct an
// inversion, we can swap adjacent elements. For example, consider the dataset arr = [2,4,1]. It has two
// inversions: (4,1) and (2,1). Given d datasets, print the number of inversions that must be swapped to
// sort each dataset on a new line.
//
// Input Format:
// The first line contains an integer, d, the number of datasets.
// Each of the next d pairs of lines is as follows:
// The first line contains an integer, n, the number of elements in arr.
// The second line contains n space-separated integers, arr[i].
//
// Output Format:
// For each of the d datasets, return the number of inversions that must be swapped to sort the dataset.
//
// Sample input:
// 2
// 5
// 1 1 1 2 2
// 5
// 2 1 3 1 2
//
// Sample output:
// 0
// 4
public class MergeSortCountingInversions {

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                int[] arr = new int[n];
                String[] arrItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                IntStream.range(0, n).forEach(i -> {
                    int arrItem = Integer.parseInt(arrItems[i]);
                    arr[i] = arrItem;
                });
                var ms = new MergeSort();
                long result = ms.countInversions(arr);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

    // Complete the countInversions function below. The function returns an integer representing the
    // number of inversions required to sort the array, given an array of integers arr as parameter.
    // We basically implement MergeSort and
    // 1) Add "swaps" counter and 1 line of code to count swaps when merging
    // 2) Use "long" instead of "int" to avoid integer overflow
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    private static class MergeSort {
        private long swaps = 0;

        private long countInversions(int[] arr) {
            int[] swapSpace = new int[arr.length];
            mergeSort(arr, swapSpace, 0, arr.length - 1);
            return swaps;
        }

        private void mergeSort(int[] array, int[] swapSpace, int start, int end) {
            if (start < end) {
                int mid = (start + end) / 2;
                mergeSort(array, swapSpace, start, mid);
                mergeSort(array, swapSpace, mid + 1, end);
                merge(array, swapSpace, start, mid, end);
            }
        }

        private void merge(int[] array, int[] swapSpace, int start, int mid, int end) {
            // Fill helper array with same elements as original array
            // notice "i" goes from "start" to "end", not "0" to "array.length"
            if (end + 1 - start >= 0) System.arraycopy(array, start, swapSpace, start, end + 1 - start);
            int curr = start;
            int left = start;
            int right = mid + 1;
            // Loop through swapSpace[] left and right halves and continuously copy smaller element to array[]
            while (left <= mid && right <= end) {
                if (swapSpace[left] <= swapSpace[right]) array[curr++] = swapSpace[left++];
                else {
                    /* Each time we choose element from right side, we count up how many elements
                       it is less than from left side. This is equivalent to counting swaps. */
                    swaps += mid + 1 - left;
                    array[curr++] = swapSpace[right++];
                }
            }
            /* Copy remaining elements of left half. Right half elements are already in proper place */
            while (left <= mid) array[curr++] = swapSpace[left++];
        }
    }
}
