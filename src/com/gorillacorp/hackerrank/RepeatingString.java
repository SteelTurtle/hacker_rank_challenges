package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// Lilah has a string, s, of lowercase English letters that she repeated infinitely many times.
// Given an integer, n, find and print the number of letter a's in the first n letters of Lilah's infinite
// string. For example, if the string s = abc and n = 10, the substring we consider is 'abcacabcac', the
// first characters of her infinite string. There are 4 occurrences of a in the substring.
//
// Input Format:
// The first line contains a single string, s.
// The second line contains an integer, n.
// Output Format
//
// Print a single integer denoting the number of letter a's in the first n letters of the infinite string
// created by repeating s infinitely many times.
//
// Sample Input:
// aba
// 10
//
// Sample Output:
// 7
public class RepeatingString {

    // Complete the repeatedString function below. The function returns an integer representing the number
    // of occurrences of 'a' in the prefix of length n in the infinitely repeating string, given:
    // s: a string to repeat
    // n: the number of characters to consider
    static long repeatedString(String s, long n) {
        long totalCount = 0;
        long occurrencesOfA = IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == 'a')
                .count();
        //Determines how many complete substrings we will analyze
        long divisor = n / s.length();
        totalCount += divisor * occurrencesOfA;
        //Determines how many characters in we will analyze towards the end of our n range
        long remainder = n % s.length();
        return totalCount + IntStream.iterate(0, i -> i < remainder, i -> i + 1)
                .filter(i -> s.charAt(i) == 'a')
                .count();
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String s = scanner.nextLine();
            long n = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            long result = repeatedString(s, n);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
