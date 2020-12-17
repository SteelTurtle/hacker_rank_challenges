package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// Emma is playing a new mobile game that starts with consecutively numbered clouds. Some of the clouds are
// thunderheads and others are cumulus. She can jump on any cumulus cloud having a number that is equal to
// the number of the current cloud plus 1 or 2. She must avoid the thunderheads. Determine the minimum
// number of jumps it will take Emma to jump from her starting position to the last cloud. It is always
// possible to win the game.
//
// Sample input:
// 7
// 0 0 1 0 0 1 0
// Sa,ple output:
// 4
public class JumpingOnTheCloudsAlgorithm {

    // This function will return the minimum number of jumps required, as an integer.
    // parameter "c" is an array of binary integers
    static int jumpingOnClouds(final int[] c) {
        var numberOfJumps = 0;
        var i = 0;
        while (i < c.length - 1) {
            if (i + 2 == c.length || c[i + 2] == 1) i++;
            else i += 2;
            numberOfJumps++;
        }
        return numberOfJumps;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {

            var n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            var c = new int[n];
            var itemsArray = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, n).forEach(i -> {
                var item = Integer.parseInt(itemsArray[i]);
                c[i] = item;
            });
            var result = jumpingOnClouds(c);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}