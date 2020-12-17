package com.gorillacorp.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

// Given an array of N Player objects, write a comparator that sorts them in order of decreasing score.
// If  or more players have the same score, sort those players alphabetically ascending by name. To do this,
// you must create a Checker class that implements the Comparator interface, then write an int compare
// (Player a, Player b) method implementing the Comparator.compare(T o1, T o2) method.
//
// Input Format:
// Locked stub code in the Solution class handles the following input from stdin:
// The first line contains an integer, N, the number of players.
// Each of the next N lines contains a player's respective "name" and "score" (as a string and an integer).
//
// Sample input:
// 5
// amy 100
// david 100
// heraldo 50
// aakansha 75
// aleksa 150
//
// Sample output:
// aleksa 150
// amy 100
// david 100
// aakansha 75
// heraldo 50
public class SortingWithComparator {

    public static void main(String[] args) {
        try (var scan = new Scanner(System.in)) {
            var n = scan.nextInt();
            Player[] players;
            Checker checker = new Checker();
            players = IntStream
                    .range(0, n)
                    .mapToObj(i -> new Player(scan.next(), scan.nextInt()))
                    .toArray(Player[]::new);
            Arrays.sort(players, checker);
            for (Player value : players) System.out.printf("%s %s\n", value.name, value.score);
        }
    }

    static class Checker implements Comparator<Player> {
        // complete this method; it sort first descending by score, then ascending by name. The code stub
        // reads
        // the input, creates a list of Player objects, uses your method to sort the data, and prints it out
        // properly.
        @Override
        public int compare(final Player a, final Player b) {
            return a.score == b.score ? a.name.compareTo(b.name) : b.score - a.score;
        }
    }

    static class Player {
        final String name;
        final int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}