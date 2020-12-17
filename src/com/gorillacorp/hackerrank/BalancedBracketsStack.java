package com.gorillacorp.hackerrank;

// A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
//
// Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs to
// the left of a closing bracket (i.e., ), ], or }) of the exact same type. There are three types of matched
// pairs of brackets: [], {}, and ().
//
// A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For
// example, {[(])} is not balanced because the contents in between { and } are not balanced. The pair of
// square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a
// single, unbalanced closing square bracket, ].
//
// By this logic, we say a sequence of brackets is balanced if the following conditions are met:
// - It contains no unmatched brackets.
// The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair
// of brackets.
// Given N strings of brackets, determine whether each sequence of brackets is balanced. If a string is
// balanced, return YES. Otherwise, return NO.

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

// Input Format:
// The first line contains a single integer , the number of strings.
//
// Output Format
// For each string, return YES or NO
public class BalancedBracketsStack {

    // Complete the isBalanced function below.
    // s: a string of brackets
    static String isBalanced(String s) {
        var stack = new Stack<>();
        for (var i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') stack.push(s.charAt(i));
            else if (stack.isEmpty()) return "NO";
            else {
                var popChar = (char) stack.pop();
                if (s.charAt(i) == ')' && popChar != '(') return "NO";
                if (s.charAt(i) == '}' && popChar != '{') return "NO";
                if (s.charAt(i) == ']' && popChar != '[') return "NO";
            }
        }
        if (stack.isEmpty()) return "YES";
        else return "NO";
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var token = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int tokenIter = 0; tokenIter < token; tokenIter++) {
                var s = scanner.nextLine();
                var result = isBalanced(s);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }
}
