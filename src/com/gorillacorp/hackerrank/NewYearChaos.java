package com.gorillacorp.hackerrank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// It's New Year's Day and everyone's in line for the Wonderland roller-coaster ride! There are a number of
// people queued up, and each person wears a sticker indicating their initial position in the queue. Initial
// positions increment by 1 from 1 at the front of the line to N at the back.
// Any person in the queue can bribe the person directly in front of them to swap positions. If two people
// swap positions, they still wear the same sticker denoting their original places in line. One person can
// bribe at most two others. For example, if N = 8 and Person5 bribes Person4, the queue will look like
//this: 1,2,3,5,4,6,7,8.
// Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place
// to get the queue into its current state!
//
// Input Format:
// The first line contains an integer t, the number of test cases.
// Each of the next t pairs of lines are as follows:
// - The first line contains an integer t, the number of people in the queue
// - The second line has t space-separated integers describing the final state of the queue.
//
// Output Format:
// Print an integer denoting the minimum number of bribes needed to get the queue into its final state.
// Print Too chaotic if the state is invalid, i.e. it requires a person to have bribed more than 2 people.
//
// Sample Input
// 2
// 5
// 2 1 5 3 4
// 5
// 2 5 1 3 4
//
// Sample Output:
// 3
// Too chaotic
public class NewYearChaos {

    // Complete the minimumBribes function below. It must print an integer representing the minimum number
    // of bribes necessary, or "Too chaotic" if the line configuration is not possible. The function
    // parameter is q (an array of integers)
    static int minimumBribes(int[] q) {
        if (!isValidArray(q)) return -1;
        int swappedPersonNumber = 0;
        List<Integer> target = Arrays.stream(q)
                .sorted()
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        for (int number : q) {
            int index = target.indexOf(number);
            if (index >= 3) return -1;
            swappedPersonNumber += index;
            target.remove(index);
        }
        return swappedPersonNumber;
    }

    static boolean isValidArray(int[] q) {
        return (Arrays.stream(q)
                .min()
                .getAsInt() == 1) &&
                (Arrays.stream(q)
                        .max()
                        .getAsInt() == q.length) &&
                (Arrays.stream(q)
                        .distinct()
                        .count() == q.length);
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            int t = scanner.nextInt();
            for (int tc = 0; tc < t; tc++) {
                int n = scanner.nextInt();
                int[] q = IntStream.range(0, n).map(i -> scanner.nextInt()).toArray();
                int solution = minimumBribes(q);
                System.out.println(solution >= 0 ? solution : "Too chaotic");
            }
        }
    }
}
