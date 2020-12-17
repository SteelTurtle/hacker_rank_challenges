package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
// Input Format:
// A single line containing the string s.
//
// Output Format:
// Find and return the string which is the lexicographically smallest valid A.
//
// Sample Input:
// eggegg
//
// Sample output:
// egg
// Explanation:
// -Split "eggegg" into strings of like character counts: "egg", "egg"
// -reverse("egg") = "gge"
// -shuffle("egg") can be "egg"
// -"eggegg" belongs to the merge of ("gge", "egg")
//
//The merge is: eGGEgge <---
//'egg' < 'gge'
public class ReverseShuffleMerge {
    // Complete the reverseShuffleMerge function below. The  function return the
    // lexicographically smallest string fitting the criteria, given an input string s.
    static String reverseShuffleMerge(String s) {
        int a = 'a';
        int m = 'z' - a + 1;
        int[] frequency = new int[m];
        s.chars().forEach(c -> frequency[c - a]++);
        int[] count = Arrays.stream(frequency).map(f -> f / 2).toArray();
        int top = -1;
        int[] stack = new int[s.length()];
        for (int n = s.length(); --n >= 0; ) {
            int c = s.charAt(n) - a;
            frequency[c]--;
            if (count[c] >= 1) {
                count[c]--;
                while (top >= 0 &&
                        stack[top] > c &&
                        frequency[stack[top]] > count[stack[top]])
                    count[stack[top--]]++; // Increment and then pop the stack
                stack[++top] = c; // Push c on to the stack
            }
        }
        return IntStream.rangeClosed(0, top)
                .mapToObj(c -> Character.toString((char) (stack[c] + a)))
                .collect(Collectors.joining(""));
    }


    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String s = scanner.nextLine();
            String result = reverseShuffleMerge(s);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }
}
