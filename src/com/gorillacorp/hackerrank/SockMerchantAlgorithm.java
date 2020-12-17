package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

// John works at a clothing store. He has a large pile of socks that he must pair by color for sale. Given
// an array of integers representing the color of each sock, determine how many pairs of socks with matching
// colors there are.
//
// For example, there are n = 7 socks with colors ar = [1,2,1,2,1,3,2].
// There is one pair of color 1 and one of color 2. There are
// three odd socks left, one of each color. The number of pairs is 2.
//
// Input format:
// The first line contains an integer N, the number of socks represented in an "ar" array.
// The second line contains N space-separated integers describing the colors "ar[i]" of the socks in the pile.
//
// Sample input:
// 9
// 10 20 20 10 10 30 50 10 20
//
// Sample output:
// 3
public class SockMerchantAlgorithm {
    // Complete the sockMerchant function below. The function returns an integer representing the number of
    // matching pairs of socks that are available, given the parameters:
    // n: the number of socks in the pile
    // ar: the colors of each sock
    static int sockMerchant(int n, int[] ar) {
        int socksPairs = 0;
        if (ar.length == 0) return socksPairs;
        Set<Integer> socksSet = new HashSet<>();
        // notice that with the enhanced loop we never use the parameter n of the function... but, well...
        for (int sock : ar) {
            if (!socksSet.contains(sock)) socksSet.add(sock);
            else {
                socksPairs++;
                socksSet.remove(sock);
            }
        }
        return socksPairs;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] ar = new int[n];
            String[] arItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int arItem = Integer.parseInt(arItems[i]);
                ar[i] = arItem;
            });
            int result = sockMerchant(n, ar);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
