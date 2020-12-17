package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Given two strings, determine if they share a common substring. A substring may be as small as one
// character. For example, the words "a", "and", "art" share the common substring a.
// The words "be" and "cat" do not share a substring.
//
// Input Format:
// The first line contains a single integer P, the number of test cases.
// The following P pairs of lines are as follows:
// The first line contains string S1.
// The second line contains string S2.
//
// Output Format:
// For each pair of strings, return YES or NO.
//
// Sample Input:
// 2
// hello
// world
// hi
// world
//
// Sample Output
// YES
// NO
public class TwoStrings {
    // Complete the twoStrings function below. The function returns a string, either YES or NO based on
    // whether the strings share a common substring, given two strings s1 and s2 as parameters.
    static String twoStrings(String s1, String s2) {
        Set<Character> string1Chars = IntStream.range(0, s1.length())
                .mapToObj(s1::charAt)
                .collect(Collectors.toSet());
        Set<Character> string2Chars = IntStream.range(0, s2.length())
                .mapToObj(s2::charAt)
                .collect(Collectors.toSet());
        // set intersection: string1Chars retains ONLY the chars in common with string2Chars
        string1Chars.retainAll(string2Chars);
        return !string1Chars.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String s1 = scanner.nextLine();
                String s2 = scanner.nextLine();
                String result = twoStrings(s1, s2);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }
}
