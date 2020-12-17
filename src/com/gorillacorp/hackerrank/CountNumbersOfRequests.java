package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class CountNumbersOfRequests {

    /*
     * Complete the 'droppedRequests' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY requestTime as parameter.
     */

    private static int droppedRequests(List<Integer> requestTime) {
        var frequencySize = requestTime.get(requestTime.size() - 1);
        var frequencyOfRequests = new int[frequencySize];
        requestTime.forEach(integer -> frequencyOfRequests[integer - 1]++);

        var droppedRequests = 0;
        var transactionExceededIn10Seconds = 0;
        var transactionsExceededIn60Seconds = 0;

        var i = 0;
        while (i < frequencyOfRequests.length) {
            if (i - 10 >= 0) transactionExceededIn10Seconds -= frequencyOfRequests[i - 10];
            if (i - 60 >= 0) transactionsExceededIn60Seconds -= frequencyOfRequests[i - 60];
            transactionExceededIn10Seconds += frequencyOfRequests[i];
            transactionsExceededIn60Seconds += frequencyOfRequests[i];
            int droppedElements = 0;
            int removedElements = frequencyOfRequests[i];
            if (frequencyOfRequests[i] > 3) {
                droppedElements += frequencyOfRequests[i] - 3;
                removedElements -= droppedElements;
            }
            if (transactionExceededIn10Seconds > 20) {
                int requestsToDrop = transactionExceededIn10Seconds - 20;
                if (removedElements > requestsToDrop) {
                    droppedElements += requestsToDrop;
                    removedElements -= requestsToDrop;
                } else {
                    droppedElements += removedElements;
                    removedElements = 0;
                }
            }
            if (transactionsExceededIn60Seconds > 60) {
                int toDrop = transactionsExceededIn60Seconds - 60;
                if (removedElements > toDrop) {
                    droppedElements += toDrop;
                    removedElements -= toDrop;
                } else {
                    droppedElements += removedElements;
                    removedElements = 0;
                }
            }
            droppedRequests += droppedElements;
            frequencyOfRequests[i] = removedElements;
            i++;
        }
        return droppedRequests;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var bufferedReader = new Scanner(System.in)) {

            var requestTimeCount = Integer.parseInt(bufferedReader.nextLine().trim());

            var requestTime =
                    IntStream.range(0, requestTimeCount)
                            .mapToObj(i -> bufferedReader.nextLine().replaceAll("\\s+$", ""))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(toList());

            var result = CountNumbersOfRequests.droppedRequests(requestTime);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}

