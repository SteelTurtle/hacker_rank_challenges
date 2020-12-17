package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// You will be given an array of integers and a target value. Determine the number of pairs of array
// elements that have a difference equal to a target value.
// For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values
// meeting the condition: 2 - 1 = 1, 3 - 2 = 1, and 4 - 3 = 1.
//
// Input Format:
// The first line contains two space-separated integers n and k, the size of arr and the target value.
// The second line contains n space-separated integers of the array arr.
//
// Output format:
// An integer representing the number of pairs of integers whose difference is k.
//
// Sample Input:
// 5 2
// 1 5 3 4 2
//
// Sample output:
// 3
public class Pairs {

    // Complete the pairs function below. The function returns an integer (I changed it to a long...)
    // representing the number of element pairs having the required difference, given the parameters:
    // k: an integer, the target difference
    // arr: an array of integers
    static long pairs(int k, int[] arr) {
        Set<Integer> numbers = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toSet());
        return numbers.stream()
                .filter(n -> numbers.contains(n - k))
                .count();
    }


    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nk = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            });
            long result = pairs(k, arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
