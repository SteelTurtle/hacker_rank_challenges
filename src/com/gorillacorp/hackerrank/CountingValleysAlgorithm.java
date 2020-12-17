package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// Gary is an avid hiker. He tracks his hikes meticulously, paying close attention to small details like
// topography. During his last hike he took exactly N steps. For every step he took, he noted if it was an
// uphill, U, or a downhill, D step. Gary's hikes start and end at sea level and each step up or down
// represents a 1 unit change in altitude. We define the following terms:
//
// -A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and
// ending with a step down to sea level.
// -A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and
// ending with a step up to sea level.
// Given Gary's sequence of up and down steps during his last hike, find and print the number of valleys he
// walked through.
//
//For example, if Gary's path is [DDUUUUDD], he first enters a valley 2 units deep. Then he climbs out an up
//onto a mountain 2 units high. Finally, he returns to sea level and ends his hike.
public class CountingValleysAlgorithm {

    // Complete the countingValleys function below. The function
    // returns an integer that denotes the number of valleys Gary traversed: given the parameters:
    // n: the number of steps Gary takes
    // s: a string describing his path
    static int countingValleys(final int n, final String s) {
        var altitude = 0;
        var numValleys = 0;
        var i = 0;
        while (i < n) {
            if (s.charAt(i) == 'U') {
                if (altitude == -1) numValleys++;
                altitude++;
            }
            if (s.charAt(i) == 'D') altitude--;
            i++;
        }
        return numValleys;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String s = scanner.nextLine();
            var result = countingValleys(n, s);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
