package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// A string is said to be a child of a another string if it can be formed by deleting 0 or more characters
// from the other string. Given two strings of equal length, what's the longest string that can be
// constructed such that it is a child of both?
// For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be formed by
// eliminating either the D or C from both strings. Note that we will not consider ABCD as a common child
// because we can't rearrange characters and ABCD != ABDC.
//
// Sample Input:
// HARRY
// SALLY
//
// Sample Output:
// 2
// (That's because the longest string that can be formed by deleting zero or more characters from HARRY and
// SALLY is AY, whose length is 2).
public class CommonChildAlgorithm {

    // Complete the commonChild function below. The function returns the longest string which is a common
    //child of the input strings, given the parameters s1 and s2 (two equal length strings).
    static int commonChild(final String s1, final String s2) {
        var s1Characters = s1.toCharArray();
        var s2Characters = s2.toCharArray();
        var lengthS1 = s1.length();
        var lengthS2 = s2.length();
        var tempS2 = new int[lengthS2 + 1];

        for (var i = 1; i <= lengthS1; i++) {
            var previousElement = 0;
            for (int j = 1; j <= lengthS2; j++) {
                var tempElement = tempS2[j];
                if (s1Characters[i - 1] == s2Characters[j - 1]) tempS2[j] = previousElement + 1;
                else tempS2[j] = Math.max(tempS2[j], tempS2[j - 1]);
                previousElement = tempElement;
            }
        }
        return tempS2[lengthS2];
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var s1 = scanner.nextLine();
            var s2 = scanner.nextLine();
            var result = commonChild(s1, s2);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
