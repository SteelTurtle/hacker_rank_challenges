package com.gorillacorp.hackerrank;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him
// through his handwriting. He found a magazine and wants to know if he can cut out whole words from it and
// use them to create an untraceable replica of his ransom note. The words in his note are case-sensitive
// and he must use only whole words available in the magazine. He cannot use substrings or concatenation to
// create the words he needs.
// Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his
// ransom note exactly using whole words from the magazine; otherwise, print No.
// For example, the note is "Attack at dawn". The magazine contains only "attack at dawn". The magazine has
// all the right words, but there's a case mismatch. The answer is 'No'.
//
// Input Format:
// The first line contains two space-separated integers, m and n, the numbers of words in the magazine and
// the note.
// The second line contains m space-separated strings, each magazine[i].
// The third line contains n space-separated strings, each note[i].
//
// Output Format:
// Print Yes if he can use the magazine to create an untraceable replica of his ransom note. Otherwise,
// print No.
//
// Sample Input:
// 6 4
// give me one grand today night
// give one grand today
//
// Sample Output:
// Yes
public class RansomNoteDictAndHashmaps {
    // Complete the checkMagazine function below. It must print 'Yes' if the note can be formed
    // using the magazine, or 'No', give the parameters:
    // magazine: an array of strings, each a word in the magazine
    // note: an array of strings, each a word in the ransom note
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> availableWords = Arrays.stream(magazine)
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
        for (String s : note)
            if (availableWords.containsKey(s) && (availableWords.get(s) > 0))
                availableWords.merge(s, -1, Integer::sum);
            else {
                System.out.println("No");
                return;
            }
        System.out.println("Yes");
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            String[] mn = scanner.nextLine().split(" ");
            int m = Integer.parseInt(mn[0]);
            int n = Integer.parseInt(mn[1]);
            String[] magazine = new String[m];
            String[] magazineItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.arraycopy(magazineItems, 0, magazine, 0, m);
            String[] note = new String[n];
            String[] noteItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.arraycopy(noteItems, 0, note, 0, n);
            checkMagazine(magazine, note);
        }
    }
}
