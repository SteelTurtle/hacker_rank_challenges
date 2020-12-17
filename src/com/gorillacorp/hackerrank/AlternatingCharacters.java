package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// You are given a string containing characters A and B only. Your task is to change it into a string such
// that there are no matching adjacent characters. To do this, you are allowed to delete zero or more
// characters in the string. Find the minimum number of required deletions; for example, given the string
// "s = AABAAB, remove an A at positions 0 and 3 to make "s = ABAB" in 2 deletions.
//
// Input format:
// The first line contains an integer Q, the number of queries.
// The next Q lines each contain a string "s".
//
// Sample Input:
// 5
// AAAA
// BBBBB
// ABABABAB
// BABABA
// AAABBB
//
// Sample output:
// 3
// 4
// 0
// 0
// 4
public class AlternatingCharacters {

    // Complete the alternatingCharacters function below. The function returns an integer representing the
    // minimum number of deletions to make the alternating string, given "s" as parameter.
    static long alternatingCharacters(final String s) {
        return IntStream.range(0, s.length() - 1)
                .filter(i -> s.charAt(i) == s.charAt(i + 1))
                .count();
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (var qIter = 0; qIter < q; qIter++) {
                var s = scanner.nextLine();
                var result = alternatingCharacters(s);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
