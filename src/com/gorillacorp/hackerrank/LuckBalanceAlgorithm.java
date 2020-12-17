package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

// Lena is preparing for an important coding competition that is preceded by a number of sequential
// preliminary contests. Initially, her luck balance is 0. She believes in "saving luck", and wants to check
// her theory. Each contest is described by two integers, L[i] and T[i]:
//
// - L[i] is the amount of luck associated with a contest. If Lena wins the contest, her luck balance will
// decrease by L[i]; if she loses it, her luck balance will increase by L[i].
// - T[i] denotes the contest's importance rating. It's equal to 1 if the contest is important, and it's
//  equal to 0 if it's unimportant.
// If Lena loses no more than K important contests, what is the maximum amount of luck she can have after
// competing in all the preliminary contests? This value may be negative.
//
// Input Format:
// The first line contains two space-separated integers N and K, the number of preliminary contests and the
// maximum number of important contests Lena can lose.
// Each of the next N lines contains two space-separated integers, L[i] and T[i], the contest's luck balance
// and its importance rating.
//
// Sample input:
// 6 3
// 5 1
// 2 1
// 1 1
// 8 1
// 10 0
// 5 0
//
// Sample output:
//  29
public class LuckBalanceAlgorithm {
    // Complete the luckBalance function below. The function returns an integer that represents the
    // maximum luck balance achievable, given the parameters:
    // k: the number of important contests Lena can lose
    // contests: a 2D array of integers where each "contests[i]" contains two integers that represent
    // the luck balance and importance of the  contest.
    static int luckBalance(int k,
                           final int[][] contests) {
        var luckAmount = 0;
        // to maximize the efficiency of the algorithm, let's first sort the "contests" array entries
        // so that th e"luck" values appears in decreasing order (notice the -1 there...)
        Arrays.sort(contests, (a, b) -> -1 * Integer.compare(a[0], b[0]));
        for (var contest : contests) {
            var luck = contest[0];
            var importance = contest[1];
            if ((importance == 1) && (k > 0)) {
                k--;
                luckAmount += luck;
            } else if (importance == 1 && k == 0) luckAmount -= luck;
            if (importance == 0) luckAmount += luck;
        }
        return luckAmount;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String[] nk = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            int[][] contests = new int[n][2];
            for (var i = 0; i < n; i++) {
                var contestsRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (var j = 0; j < 2; j++) {
                    var contestsItem = Integer.parseInt(contestsRowItems[j]);
                    contests[i][j] = contestsItem;
                }
            }
            var result = luckBalance(k, contests);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
