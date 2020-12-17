package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// Mark and Jane are very happy after having their first child. Their son loves toys, so Mark wants to buy
// some. There are a number of different toys lying in front of him, tagged with their prices. Mark has only
// a certain amount to spend, and he wants to maximize the number of toys he buys with this money.
// Given a list of prices and an amount to spend, what is the maximum number of toys Mark can buy? For
// example, if "prices = [1,2,3,4]" and Mark has "k = 7" to spend, he can buy items [1,2,3]  for 6, or [3,4]
// for 7 units of currency. He would choose the first group of 3 items.
//
// Input Format:
// The first line contains two integers, N and K, the number of priced toys and the amount Mark has to spend.
// The next line contains N space-separated integers "prices[i]"
// NOTICE THAT A toy can't be bought multiple times.
//
// Sample Input
// 7 50
// 1 12 5 111 200 1000 10
//
// Sample output:
// 4
public class MarkAndToys {

    // Complete the maximumToys function below. The function returns an integer that denotes the maximum
    // number of toys Mark can buy for his son, given the parameters:
    // prices: an array of integers representing toy prices
    // k: an integer, Mark's budget
    static int maximumToys(int[] prices, int k) {
        int maxNumberOfToys = 0;
        if (prices.length == 0 || k == 0) return maxNumberOfToys;
        // sort prices to maximise efficiency of the for loop
        Arrays.sort(prices);
        for (int toyPrice : prices) {
            k -= toyPrice;
            if (k < 0) return maxNumberOfToys;
            else maxNumberOfToys++;
        }
        return maxNumberOfToys;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nk = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            int[] prices = new int[n];
            String[] pricesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                int pricesItem = Integer.parseInt(pricesItems[i]);
                prices[i] = pricesItem;
            });
            int result = maximumToys(prices, k);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
