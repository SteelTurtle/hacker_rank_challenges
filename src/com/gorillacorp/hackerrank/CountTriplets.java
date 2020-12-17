package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// You are given an array and you need to find number of triplets of indices (i,j,k) such that the elements
// at those indices are in geometric progression for a given common ratio r and i < j < k.
// For example, srr = [1,4,16,64]. If r = 4, we have [1,4,16] and [4,16,64] at indices (0,1,2) and (1,2,3).
//
// Input Format:
// The first line contains two space-separated integers N and R, the size of "arr" and the common ratio.
// The next line contains N space-separated integers arr[i].
//
// Sample Input:
// 4 2
// 1 2 2 4
//
// Sample Output:
// 2
// (because there are 2 triplets satisfying our criteria, whose indices are (0,1,3) and (0,2,3).
public class CountTriplets {
    // Complete the countTriplets function below. The function returns the number of triplets forming a
    // geometric progression for a given  as an integer, given the following parameter(s):
    // arr: an array of integers
    // r: an integer, the common ratio
    static long countTriplets(final List<Long> array, final long r) {
        Map<Long, Long> leftElementsMap = new HashMap<>();
        Map<Long, Long> rightElementsMap = new HashMap<>();
        array.forEach(item -> rightElementsMap
                .put(item, rightElementsMap.getOrDefault(item, 0L) + 1));

        var count = 0;
        for (var element : array) {
            long c1 = 0, c3 = 0;
            rightElementsMap.put(element, rightElementsMap.getOrDefault(element, 0L) - 1);
            if (leftElementsMap.containsKey(element / r) && element % r == 0)
                c1 = leftElementsMap.get(element / r);
            if (rightElementsMap.containsKey(element * r))
                c3 = rightElementsMap.get(element * r);
            count += c1 * c3;
            leftElementsMap.put(element, leftElementsMap.getOrDefault(element, 0L) + 1);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var nr = scanner.nextLine()
                    .replaceAll("\\s+$", "")
                    .split(" ");
            var r = Long.parseLong(nr[1]);
            var array = Stream.of(scanner.nextLine()
                    .replaceAll("\\s+$", "")
                    .split(" "))
                    .map(Long::parseLong)
                    .collect(toList());
            var response = countTriplets(array, r);
            bufferedWriter.write(String.valueOf(response));
            bufferedWriter.newLine();
        }
    }
}
