package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// A string is said to be a special string if either of two conditions is met:
// - All of the characters are the same, e.g. aaa.
// - All characters except the middle one are the same, e.g. aadaa.
// A special substring is any substring of a string which meets one of those criteria. Given a string,
// determine how many special substrings can be formed from it.
// For example, given the string s = mnonopoo, we have the following special substrings:
// {m,n,o,n,o,p,o,o,non,ono,opo,oo}
//
// Input Format:
// The first line contains an integer, n, the length of s.
// The second line contains the string s.
//
// Output Format:
// Print a single line containing the count of total special substrings.
//
// Sample input:
// 5
// asasd
//
// Sample output:
// 7
public class SpecialStringAgain {

    // Complete the substrCount function below. The function returns an integer
    // representing the number of special substrings that can be formed from the given string.
    // The function parameters are:
    // n: an integer, the length of string s
    // s: a string
    // NOTICE this implementation does not need the n parameter...
    static long substrCount(int n, String s) {
        long count = 0;
        for (int i = 0; i < s.length(); i++) {
            int innerCounter = 1;
            int counterDown = 0;
            int counterUp = 1;
            while (((i - innerCounter) >= 0) && ((i + innerCounter) < s.length())
                    && (s.charAt(i - innerCounter) == s.charAt(i - 1))
                    && (s.charAt(i + innerCounter) == s.charAt(i - 1))) {
                count++;
                innerCounter++;
            }
            while (((i - counterDown) >= 0)
                    && ((i + counterUp) < s.length()) && (s.charAt(i - counterDown) == s.charAt(i))
                    && (s.charAt(i + counterUp) == s.charAt(i))) {
                count++;
                counterDown++;
                counterUp++;
            }
        }
        return count + s.length();
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String s = scanner.nextLine();
            long result = substrCount(n, s);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
