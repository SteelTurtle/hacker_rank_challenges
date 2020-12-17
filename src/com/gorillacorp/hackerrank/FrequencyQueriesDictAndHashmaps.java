package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

// You are given q queries. Each query is of the form two integers described below:
// -1 x: Insert x in your data structure.
// -2 y: Delete one occurrence of y from your data structure, if present.
// -3 z: Check if any integer is present whose frequency is exactly z. If yes, print 1 else 0.
// The queries are given in the form of a 2-D array "queries" of size q where queries[i][0] contains
// the operation, and queries[i][1] contains the data element.
//
// Input Format:
// The first line contains of an integer q, the number of queries.
// Each of the next q lines contains two integers denoting the 2-d array "queries".
//
// Output Format:
// Return an integer array consisting of all the outputs of queries of type 3 (see above).
//
// Sample Input:
// 8
// 1 5
// 1 6
// 3 2
// 1 10
// 1 10
// 1 6
// 2 5
// 3 2
//
// Sample Output:
// 0
// 1
// (That is, for the first query of type 3, there is no integer whose frequency is 2 (array = [5,6]). So
// answer is 0. For the second query of type 3, there are two integers in array = [6,10,10,6]
// whose frequency is 2 (integers = 6 and 10). So, the answer is 1).
public class FrequencyQueriesDictAndHashmaps {

    // Complete the freqQuery function below. The function returns an array of integers where
    // each element is a 1 if there is at least one element value with the queried number of occurrences in
    // the current array, or 0 if there is not. freqQuery() accept as parameter a List of int[]
    // (originally stated in the problem as 2-d array of integers AND declared as
    // List<List<Integer>>, which made necessary to change all the boilerplate code of the main function...
    // [face-palm])...
    static List<Integer> freqQuery(final List<int[]> queries) {
        var response = new ArrayList<Integer>();
        if (queries.isEmpty()) return null;
        Map<Integer, Integer> intFrequencyMap = new HashMap<>();
        Map<Integer, Integer> frequencyCountMap = new HashMap<>();

        for (var query : queries) {
            var command = query[0];
            var param = query[1];
            int currentFrequency;
            int frequencyCounterForElement;
            switch (command) {
                case 1 -> {
                    currentFrequency = intFrequencyMap.getOrDefault(param, 0);
                    frequencyCounterForElement = frequencyCountMap
                            .getOrDefault(currentFrequency, 0);
                    if (frequencyCounterForElement > 0)
                        frequencyCountMap.put(currentFrequency, frequencyCounterForElement - 1);
                    currentFrequency++;
                    intFrequencyMap.put(param, currentFrequency);
                    frequencyCounterForElement = frequencyCountMap
                            .getOrDefault(currentFrequency, 0);
                    frequencyCountMap.put(currentFrequency, frequencyCounterForElement + 1);
                }
                case 2 -> {
                    currentFrequency = intFrequencyMap.getOrDefault(param, 0);
                    if (currentFrequency > 0) {
                        frequencyCounterForElement = frequencyCountMap
                                .getOrDefault(currentFrequency, 0);
                        if (frequencyCounterForElement > 0)
                            frequencyCountMap.put(currentFrequency, frequencyCounterForElement - 1);
                        currentFrequency--;
                        intFrequencyMap.put(param, currentFrequency);
                        frequencyCounterForElement = frequencyCountMap
                                .getOrDefault(currentFrequency, 0);
                        frequencyCountMap.put(currentFrequency, frequencyCounterForElement + 1);
                    }
                }
                case 3 -> {
                    frequencyCounterForElement = frequencyCountMap
                            .getOrDefault(param, 0);
                    response.add(frequencyCounterForElement == 0 ? 0 : 1);
                }
            }
        }
        return response;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var q = Integer.parseInt(scanner.nextLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            var pattern = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (var i = 0; i < q; i++) {
                var query = new int[2];
                var matcher = pattern.matcher(scanner.nextLine());
                if (matcher.matches()) {
                    query[0] = Integer.parseInt(matcher.group(1));
                    query[1] = Integer.parseInt(matcher.group(2));
                    queries.add(query);
                }
            }
            List<Integer> answer = freqQuery(queries);
            bufferedWriter.write(
                    Objects.requireNonNull(answer).stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n");
        }
    }
}