package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// A left rotation operation on an array shifts each of the array's elements 1 unit to the left. For
// example, if 2 left rotations are performed on array [1,2,3,4,5], then the array would become [3,4,5,1,2].
// Given an array A of N integers and a number, D, perform D left rotations on the array. Return the updated
// array to be printed as a single line of space-separated integers.
//
// Input Format:
// The first line contains two space-separated integers N and D, the size of A and the number of left
// rotations you must perform.
// The second line contains N space-separated integers "a[i]".
//
// Sample Input
// 5 4
// 1 2 3 4 5
//
// Sample Output:
// 5 1 2 3 4
public class ArraysLeftRotation {

    // Complete the rotateLeft function below. The function returns the resulting array of integers,
    // given the parameters:
    // An array of integers a.
    // An integer d, the number of rotations.
    static int[] rotateLeft(final int[] a, final int d) {
        var arraySize = a.length;
        var rotatedArray = new int[arraySize];
        var rotationIndex = d;

        var i = 0;
        while (rotationIndex < arraySize) {
            rotatedArray[i] = a[rotationIndex];
            i++;
            rotationIndex++;
        }
        rotationIndex = 0;
        while (rotationIndex < d) {
            rotatedArray[i] = a[rotationIndex];
            i++;
            rotationIndex++;
        }
        return rotatedArray;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nd = scanner.nextLine().split(" ");
            var n = Integer.parseInt(nd[0]);
            var d = Integer.parseInt(nd[1]);
            var array = new int[n];
            var arrayItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                var item = Integer.parseInt(arrayItems[i]);
                array[i] = item;
            });
            var result = rotateLeft(array, d);
            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));
                if (i != result.length - 1) bufferedWriter.write(" ");
            }
            bufferedWriter.newLine();
        }
    }
}
