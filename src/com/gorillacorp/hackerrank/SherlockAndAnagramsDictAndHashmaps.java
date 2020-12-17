package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Two strings are anagrams of each other if the letters of one string can be rearranged to form the other
// string. Given a string, find the number of pairs of substrings of the string that are anagrams of each
// other.
// For example s = mom, the list of all anagrammatic pairs is [m,m], [mo, om] at positions
//[[0],[2]], [[0,1],[1,2]] respectively.
//
// Input Format:
// The first line contains an integer q, the number of queries.
// Each of the next q lines contains a string s to analyze.
//
// Output Format
// For each query, return the number of unordered anagrammatic pairs.
//
// Sample input:
// 2
// abba
// abcd
//
// Sample output:
// 4 0 (4 anagrammatic pairs in "abba", 0 in "abcd")
public class SherlockAndAnagramsDictAndHashmaps {

    // Complete the sherlockAndAnagrams function below. The function returns an integer that represents
    // the number of anagrammatic pairs of substrings in parameter string s.
    static int sherlockAndAnagrams(String s) {
        int currentLength = 1;
        Map<String, Integer> matchingPairs = new HashMap<>();
        while (currentLength < s.length()) {
            for (int i = 0; i < s.length() - (currentLength - 1); i++) {
                String str = s.substring(i, i + currentLength);
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                str = String.valueOf(arr);
                matchingPairs.put(str, matchingPairs.containsKey(str) ? matchingPairs.get(str) + 1 : 1);
            }
            currentLength++;
        }
        return matchingPairs
                .values()
                .stream()
                .filter(i -> i >= 2)
                .mapToInt(i -> (i * i - i) / 2)
                .sum();
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();
                int result = sherlockAndAnagrams(s);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
