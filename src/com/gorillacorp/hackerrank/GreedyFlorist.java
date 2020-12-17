package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// A group of friends want to buy a bouquet of flowers. The florist wants to maximize his number of new
// customers and the money he makes. To do this, he decides he'll multiply the price of each
// flower by the number of that customer's previously purchased flowers plus 1. The first flower will be
// original price, "(0 + 1) X original price", the next will be "(1 + 1) X original price" and so on.
// Given the size of the group of friends, the number of flowers they want to purchase and the original
// prices of the flowers, determine the minimum cost to purchase all of the flowers.
// For example, if there are k = 3 friends that want to buy n = 4 flowers that cost c = [1,2,3,4]
// each will buy one of the flowers priced [2,3,4] at the original price. Having each purchased
// x = 1 flower, the first flower in the list, c[0] will now cost
// "(current purchase + previous purchase) X c[0] = (1 + 1) X 1 = 2". The total cost will be 2+3+4+2=11.
//
// Input format:
// The first line contains two space-separated integers n and k, the number of flowers and the number of
// friends. The second line contains n space-separated positive integers c[i], the original
// price of each flower.
//
// Output format:
// Print the minimum cost to buy all n flowers.
//
// Sample input:
// 3 3
// 2 5 6
// Sample output
// 15
public class GreedyFlorist {
    // Complete the getMinimumCost function below. The function returns the minimum cost to
    // purchase all of the flowers, given the following parameter(s):
    // c: an array of integers representing the original price of each flower
    // NOTE Hackerrank is SHIT, you know it :) the c parameter, as well as some code in the main
    // function, has changed to manage a List<Integer> instead...
    // k: an integer, the number of friends
    static int getMinimumCost(final int k, final List<Integer> c) {
        var cheapestPrice = 0;
        var flowersPurchased = 0;
        c.sort(Collections.reverseOrder());
        for (var i = 0; i < c.size(); i++) {
            if (i % k == 0) flowersPurchased++;
            cheapestPrice += flowersPurchased * c.get(i);
        }
        return cheapestPrice;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nk = scanner.nextLine().split(" ");
            var n = Integer.parseInt(nk[0]);
            var k = Integer.parseInt(nk[1]);
            var c = new ArrayList<Integer>();
            var itemsArray = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                var item = Integer.parseInt(itemsArray[i]);
                c.add(item);
            });
            var minimumCost = getMinimumCost(k, c);
            bufferedWriter.write(String.valueOf(minimumCost));
            bufferedWriter.newLine();
        }
    }
}
