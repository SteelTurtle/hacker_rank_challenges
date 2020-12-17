package com.gorillacorp.hackerrank;

import java.util.Scanner;
import java.util.stream.IntStream;

// it is just it... bubble sort. eventually, the innermost for loop could be turned into a swap()
// function, but nothing more special than that.
public class BubbleSort {

    // Complete the countSwaps function below (aka Bubble Sort...).
    static void countSwaps(int[] array) {
        var numSwaps = 0;
        var arrayLength = array.length;
        for (var i = 0; i < arrayLength; i++)
            for (var j = 0; j < arrayLength - 1; j++)
                if (array[j] > array[j + 1]) {
                    var temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    ++numSwaps;
                }
        System.out.println("Array is sorted in " + numSwaps + " swaps.");
        System.out.println("First Element: " + array[0]);
        System.out.println("Last Element: " + array[arrayLength - 1]);
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            var a = new int[n];
            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                var item = Integer.parseInt(aItems[i]);
                a[i] = item;
            });
            countSwaps(a);
        }
    }
}
