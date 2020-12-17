package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to
// be anagrams of each other if the first string's letters can be rearranged to form the second string. In
// other words, both strings must contain the same exact letters in the same exact frequency For example,
// "bacdc" and "dcbac" are anagrams, but "bacdc" and "dcbad" are not.
//
// Alice decides on an encryption scheme involving two large strings where encryption is dependent on the
// minimum number of character deletions required to make the two strings anagrams. Can you help her find
// this number?
//
// Given two strings, A and B, that may or may not be of the same length, determine the minimum number of
// character deletions required to make A and B anagrams. Any characters can be deleted from either of the
// strings.
//
// Input Format:
// The first line contains a single string, A.
// The second line contains a single string, B.
//
// Output Format:
// Print a single integer denoting the number of characters you must delete to make the two strings anagrams
// of each other.
//
// Sample Input:
// cde
// abc
//
// Sample Input:
// 4
public class MakingAnagramsWithStrings {

    // Complete the makeAnagram function below. The function returns an integer representing the minimum
    // total characters that must be deleted to make the strings anagrams, given two input strings.
    static int makeAnagram(String a, String b) {
        int minDeletions;
        // notice the limitation of the whole problem here: we assume we are working with the ascii
        // character set and considering only the english-based 26 letters' alphabet...
        int[] frequenciesA = new int[26];
        int[] frequenciesB = new int[26];
        // convert ascii value to integer representation
        IntStream.range(0, a.length())
                .map(a::charAt)
                .map(charToInt -> charToInt - 'a')
                .forEach(position -> frequenciesA[position]++);
        IntStream.range(0, b.length())
                .map(b::charAt)
                .map(charToInt -> charToInt - 'a')
                .forEach(position -> frequenciesB[position]++);
        minDeletions = IntStream.range(0, 26)
                .map(i -> Math.abs(frequenciesA[i] - frequenciesB[i]))
                .sum();
        return minDeletions;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            int res = makeAnagram(a, b);
            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }
    }
}
