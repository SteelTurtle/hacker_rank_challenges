package com.gorillacorp.hackerrank.statistics;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MeanMedianAndMode {
    public static void main(String[] args) throws IOException {
        try (var scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            });

            // average
            double sum = Arrays.stream(arr).asDoubleStream().sum();
            System.out.println(sum / arr.length);
            // median
            Arrays.sort(arr);
            double median;
            if (arr.length % 2 == 0)
                median = ((double) arr[arr.length / 2] + (double) arr[arr.length / 2 - 1]) / 2;
            else median = arr[arr.length / 2];
            System.out.println(median);
            // mode
            int maxCount = 0;
            int maxValue = 0;
            for (int number : arr) {
                int count = (int) Arrays.stream(arr).filter(innerNumber -> number == innerNumber).count();
                if (count > maxCount) {
                    maxCount = count;
                    maxValue = number;
                }
            }
            System.out.println(maxValue);
        }
    }
}
