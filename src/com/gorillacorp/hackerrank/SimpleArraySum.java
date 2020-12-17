package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Given an array of integers, find the sum of its elements.
 * Sample Input:
 * 6
 * 1 2 3 4 10 11
 * <p>
 * Sample Output:
 * 31
 */
public class SimpleArraySum {

    private static int simpleArraySum(int[] array) {
        return Arrays.stream(array).sum();
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {

            var count = Integer.parseInt(scanner.nextLine().trim());
            var intArray = new int[count];
            var arrayItems = scanner.nextLine().split(" ");

            IntStream.range(0, count).forEach(arItr -> {
                var arItem = Integer.parseInt(arrayItems[arItr].trim());
                intArray[arItr] = arItem;
            });
            var result = simpleArraySum(intArray);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
