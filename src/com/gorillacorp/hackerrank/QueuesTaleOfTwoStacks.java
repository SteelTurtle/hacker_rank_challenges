package com.gorillacorp.hackerrank;

import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

// In this challenge, you must first implement a queue using two stacks. Then process q queries, where each
// query is one of the following 3 types:
// 1 x: Enqueue element x into the end of the queue.
// 2: Dequeue the element at the front of the queue.
// 3: Print the element at the front of the queue.
//
// Input Format:
// The first line contains a single integer, q, the number of queries.
// Each of the next q lines contains a single query (a query can be a single number or more than one).
// All queries start with an integer denoting the query type (1,2 or 3),
// but only query 1 is followed by an additional space-separated value, x, denoting the value to be enqueued.
//
// Output format:
// For each query of type 3, return the value of the element at the front of the fifo queue on a new line.
//
// Sample input:
// 10
// 1 42
// 2
// 1 14
// 3
// 1 28
// 3
// 1 60
// 1 78
// 2
// 2
//
// Sample output:
// 14
// 14
public class QueuesTaleOfTwoStacks {

    public static void main(String[] args) {
        CustomQueue<Integer> queue = new CustomQueue<>();
        try (var scan = new Scanner(System.in)) {
            int n = scan.nextInt();
            IntStream.range(0, n).map(i -> scan.nextInt())
                    .forEach(operation -> {
                        switch (operation) {
                            case 1:  // enqueue
                                queue.enqueue(scan.nextInt());
                                break;
                            case 2:  // dequeue
                                queue.dequeue();
                                break;
                            case 3:  // print/peek
                                System.out.println(queue.peek());
                                break;
                        }
                    });
        }
    }

    private static class CustomQueue<T> {
        private Stack<T> in = new Stack<>();
        private Stack<T> out = new Stack<>();

        public void enqueue(T value) {
            in.push(value);
        }

        public T dequeue() {
            if (out.isEmpty())
                while (!in.empty()) out.push(in.pop());
            return out.pop();
        }

        public T peek() {
            if (out.isEmpty())
                while (!in.empty()) out.push(in.pop());
            return out.peek();
        }
    }
}
