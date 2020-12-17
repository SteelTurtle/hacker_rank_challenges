package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// You are planning production for an order. You have a number of machines that each have a fixed number of
// days to produce an item. Given that all the machines operate simultaneously, determine the minimum number
// of days to produce the required order.
//
// Input Format:
// The first line consist of two integers n and goal, the size of machines (an array) and the target
// production. The next line contains n space-separated integers, machines[i].
//
// Output Format:
// Return the minimum time required to produce "goal" items considering all machines work simultaneously.
// Sample Input:
// 2 5
// 2 3
//
// Sample output:
// 6
// (That is, in 6 days machines[0] can produce 3 items and machines[1] can produce 2 items.
// This totals up to 5.)
//
// Rationale for the algorithm:
// The first step is identifying that this problem can be solved with search. Given that it's under the
// search category let's jump straight to the assumption that a search approach will be the best.
// When we're performing a search we need to start by defining some bounds (we need to search between two
// bounds otherwise how do we know where to start/stop)
// How do we define our bounds then? Well let's start with the upper bound.
// How many days would it take if ALL machines were equally as slow as the slowest machine?
// If the slowest machine takes 5 days, we have 3 machines in total, and our goal is 30 then the worst case
// scenario is tha ALL machines take 5 days.
// Given we have 3 machines that means that after 5 days we'll have 3 items produced. That means the worst
// case is goal / machines * slowestMachine = 50 days.
// We can do the same thing to find the lower bound. Let's assume that all 3 machines are as fast as the
// fastest machine. Our lower bound is then goal / machines * fastestMachine = 10 days.
// Now that we have our lower and upper bounds (10 - 50) we can finally start to search.
// With binary search we always start at the middle of our bounds. The middle of our bounds is 35 (
// (lowerBound + upperBound) / 2) so let's start there.
// Next we'll try to calculate how many items would be produced after this many days.
// For each machine, how many items does it produce after 35 days? For that we use days / machine. Sum each
// of these values for each machine to get the total number of items produced by all machines after 35 days.
// Now we check if we've met our goal or not. If we produce more than our goal, then the half way point that
// we chose is too high. If we produced less then the half way point was too low.
// All we have to do now is repeat the steps but adjust our upper and lower bounds to be (lowerBound -
// middlePoint) or (middlePoint - upperBound) depending what side of the line we fell.
public class MinimumTimeRequired {

    // Complete the minTime function below. The function returns an integer (you mean a long, dude... :D)
    // representing the minimum number of days required to complete the order given the following parameters:
    // machines: an array of integers representing days to produce one item per machine
    // goal: an integer, the number of items required to complete the order
    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long max = machines[machines.length - 1];
        long minimumDaysRequired = 0;
        long maximumDaysRequired = max * goal;
        long effectiveDaysRequired = -1;
        while (minimumDaysRequired < maximumDaysRequired) {
            long averageTime = (minimumDaysRequired + maximumDaysRequired) / 2;
            long unitProduced = Arrays.stream(machines).map(machine -> averageTime / machine).sum();
            if (unitProduced >= goal) {
                effectiveDaysRequired = averageTime;
                maximumDaysRequired = averageTime;
            } else minimumDaysRequired = averageTime + 1;
        }
        return effectiveDaysRequired;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nGoal = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nGoal[0]);
            long goal = Long.parseLong(nGoal[1]);
            long[] machines = new long[n];
            String[] machinesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                long machinesItem = Long.parseLong(machinesItems[i]);
                machines[i] = machinesItem;
            });
            long ans = minTime(machines, goal);
            bufferedWriter.write(String.valueOf(ans));
            bufferedWriter.newLine();
        }
    }
}
