package com.gorillacorp.hackerrank;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

// Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool their money to buy ice cream.
// On any given day, the parlor offers a line of flavors. Each flavor has a cost associated with it.
// Given the value of "money" and the "cost" of each flavor for t trips to the Ice Cream Parlor, help Sunny
// and Johnny choose two distinct flavors such that they spend their entire pool of money during each
// visit. ID numbers are the 1- based index number associated with a cost. For each trip to the
// parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as
// two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.
// Note:
// - Two ice creams having unique IDs i and j may have the same identical cost.
// - There will always be a unique solution.
//
// Input Format:
// The first line contains an integer, t, the number of trips to the ice cream parlor.
// Each of the next t sets of 3 lines is as follows:
// - The first line contains "money".
// - The second line contains an integer, n, the size of the array "cost".
// - The third line contains n space-separated integers denoting the cost[i].
//
// Output Format:
// Print two space-separated integers denoting the respective indices for the two distinct flavors they
// choose to purchase in ascending order. Recall that each ice cream flavor has a unique ID number in the
// inclusive range from 1 to |cost|.
//
// Sample input:
// 2
// 4
// 5
// 1 4 5 3 2
// 4
// 4
// 2 2 4 3
//
// Sample output:
// 1 4
// 1 2
public class IceCreamParlor {
    // Complete the whatFlavors function below. The function  must determine the two flavors they will
    // purchase and print them as two space-separated integers on a line, give the parameters:
    //cost: an array of integers representing price for a flavor
    //money: an integer representing the amount of money they have to spend
    static void whatFlavors(final int[] cost, final int money) {
        if (cost != null && cost.length >= 1) {
            var costTracker = new HashMap<Integer, Integer>();
            for (var i = 0; i < cost.length; i++)
                if (cost[i] < money) {
                    if (costTracker.containsKey(money - cost[i])) {
                        var index = costTracker.get(money - cost[i]);
                        System.out.println((index + 1) + " " + (i + 1));
                        return;
                    }
                    if (!costTracker.containsKey(cost[i])) costTracker.put(cost[i], i);
                }
        } else System.out.println(-1 + " " + -1);
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var temp = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (var tempIter = 0; tempIter < temp; tempIter++) {
                var money = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                var n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                var cost = new int[n];
                var costItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                IntStream.range(0, n).forEach(i -> {
                    var costItem = Integer.parseInt(costItems[i]);
                    cost[i] = costItem;
                });
                whatFlavors(cost, money);
            }
        }
    }
}
