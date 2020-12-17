package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// Sherlock considers a string to be valid if all characters of the string appear the same number of times.
// It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters
// will occur the same number of times. Given a string s, determine if it is valid. If so, return YES,
// otherwise return NO.
//
// Input Format:
// A single string s.
//
// Output Format:
// Print YES if string s is valid, otherwise, print NO.
//
// Sample Input:
// aabbcd
//
// Sample output:
// NO
// (That's because given s = "aabbcd", we would need to remove two characters, both c and d --> aabb
// or a and b --> abcd, to make it valid. We are limited to removing only one character, so s is invalid.)
public class SherlockAndValidString {

    // Complete the isValid function below. The function returns either the string YES or
    // the string NO, given the string s as input parameter:
    static String isValid(String s) {
        // edge cases
        if (s.isEmpty()) return "NO";
        if (s.length() <= 3) return "YES";

        int[] letters = new int[26];
        IntStream.range(0, s.length()).forEach(i -> letters[s.charAt(i) - 'a']++);
        Arrays.sort(letters);
        int index = 0;
        while (letters[index] == 0) index++;
        //the smallest frequency of some letter
        int min = letters[index];
        // the largest frequency of some letter
        int max = letters[25];
        // remove one letter at higher frequency or the lower frequency
        if (min == max || ((max - min == 1) && (max > letters[24])) ||
                (min == 1) && (letters[index + 1] == max)) return "YES";
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var s = scanner.nextLine();
            var result = isValid(s);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }
}
