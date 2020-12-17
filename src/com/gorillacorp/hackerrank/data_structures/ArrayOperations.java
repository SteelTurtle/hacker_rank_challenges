package com.gorillacorp.hackerrank.data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// various array operations
public class ArrayOperations {

    // reverse an array
    static int[] reverseArray(int[] a) {
        int[] b = new int[a.length];
        IntStream.iterate(a.length - 1, i -> i >= 0, i -> i - 1)
                .forEach(i -> b[a.length - 1 - i] = a[i]);
        return b;
    }

    // Dynamic Array
    // https://www.hackerrank.com/challenges/dynamic-array/problem
    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> computation =
                IntStream.range(0, n)
                        .<List<Integer>>mapToObj(i -> new ArrayList<>())
                        .collect(Collectors.toList());

        int lastAnswer = 0;
        for (List<Integer> q : queries) {
            if (q.get(0) == 1) computation.get((q.get(1) ^ lastAnswer) % n).add(q.get(2));
            else {
                List<Integer> seq = computation.get((q.get(1) ^ lastAnswer) % n);
                lastAnswer = seq.get(q.get(2) % seq.size());
                result.add(lastAnswer);
            }
        }
        return result;
    }

    // Sparse Arrays
    // https://www.hackerrank.com/challenges/sparse-arrays/problem
    // There is a collection of input strings and a collection of query strings.
    // For each query string, determine how many times it occurs in the list of input strings.
    static int[] matchingStrings(String[] strings, String[] queries) {
        Map<String, Integer> map = new HashMap<>();
        for (String string : strings)
            if (!map.containsKey(string)) map.put(string, 1);
            else {
                int count = map.get(string);
                map.put(string, ++count);
            }
        int[] matches = new int[queries.length];
        int count = 0;
        for (String query : queries)
            if (map.containsKey(query)) matches[count++] = map.get(query);
            else count++;
        return matches;
    }


}
