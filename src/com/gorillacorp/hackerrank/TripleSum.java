package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// Given 3 arrays a, b, c of different sizes, find the number of distinct triplets (p, q, r) where p is an
//element of a, written as p (belongsTo) a, q (belongsTo) b, and r (belongsTo) r, satisfying the criteria:
// p <= q && q >= r.
//
// For example, given a = [3,5,7], b = [3,6] and c = [4,6,9], we find four distinct triplets:
// (3,6,4), (3,6,6), (5,6,4) and (5,6,6).
//
// Input Format:
// The first line contains 3 integers lengthA, lengthB, lengthC, the sizes of the three arrays.
// The next 3 lines contain space-separated integers numbering lengthA, lengthB, lengthC respectively.
//
// Output Format:
// Print an integer representing the number of distinct triplets.
public class TripleSum {

    // Complete the triplets function below. The function returns the number of distinct triplets that can
    // be formed from the given arrays, given the parameters a, b, c (three arrays of integers).
    static long triplets(int[] a, int[] b, int[] c) {
        int[] uniqueElementsA = Arrays.stream(a).sorted().distinct().toArray();
        int[] uniqueElementsB = Arrays.stream(b).sorted().distinct().toArray();
        int[] uniqueElementsC = Arrays.stream(c).sorted().distinct().toArray();

        long leftValue = 0;
        long rightValue = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        long tripletsSum = 0;

        for (int value : uniqueElementsB) {
            while (leftIndex < uniqueElementsA.length && uniqueElementsA[leftIndex] <= value) {
                leftValue++;
                leftIndex++;
            }
            while (rightIndex < uniqueElementsC.length && uniqueElementsC[rightIndex] <= value) {
                rightValue++;
                rightIndex++;
            }
            tripletsSum += leftValue * rightValue;
        }
        return tripletsSum;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] lenaLenbLenc = scanner.nextLine().split(" ");
            int lena = Integer.parseInt(lenaLenbLenc[0]);
            int lenb = Integer.parseInt(lenaLenbLenc[1]);
            int lenc = Integer.parseInt(lenaLenbLenc[2]);
            int[] arra = new int[lena];
            String[] arraItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, lena).forEach(i -> {
                int arraItem = Integer.parseInt(arraItems[i]);
                arra[i] = arraItem;
            });
            int[] arrb = new int[lenb];
            String[] arrbItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, lenb).forEach(i -> {
                int arrbItem = Integer.parseInt(arrbItems[i]);
                arrb[i] = arrbItem;
            });
            int[] arrc = new int[lenc];
            String[] arrcItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, lenc).forEach(i -> {
                int arrcItem = Integer.parseInt(arrcItems[i]);
                arrc[i] = arrcItem;
            });
            long ans = triplets(arra, arrb, arrc);
            bufferedWriter.write(String.valueOf(ans));
            bufferedWriter.newLine();
        }
    }
}
