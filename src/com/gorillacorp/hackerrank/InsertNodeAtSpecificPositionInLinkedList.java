package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// You’re given the pointer to the head node of a linked list, an integer to add to the list and the
// position at which the integer must be inserted. Create a new node with the given integer, insert this
// node at the desired position and return the head node.
//
// A position of 0 indicates head, a position of 1 indicates one node away from the head and so on. The head
// pointer given may be null meaning that the initial list is empty.

//Input Format:
//The first line contains an integer N, the number of elements in the linked list.
//Each of the next N lines contains an integer "SinglyLinkedListNode[i].data".
//The next line contains an integer "data" denoting the data of the node that is to be inserted.
//The last line contains an integer "position".

// Sample input:
// 3
// 16
// 13
// 7
// 1
// 2
// Sample output:
// 16 13 1 7
public class InsertNodeAtSpecificPositionInLinkedList {

    private static void printSinglyLinkedList(SinglyLinkedListNode node,
                                              final BufferedWriter out) throws IOException {
        while (node != null) {
            out.write(String.valueOf(node.data));
            node = node.next;
            if (node != null) out.write(" ");
        }
    }

    // Complete the insertNodeAtPosition function below.
    // The function returns a reference to the head node of your finished list, given the
    // following parameters:
    // head: a SinglyLinkedListNode pointer to the head of the list
    // data: an integer value to insert as data in your new node
    // position: an integer position to insert the new node, zero based indexing
    private static SinglyLinkedListNode insertNodeAtPosition(final SinglyLinkedListNode head,
                                                             final int data,
                                                             final int position) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
        SinglyLinkedListNode currentNode = head;

        int index = 0;
        while (index < position - 1) {
            currentNode = currentNode.next;
            index++;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        return head;
    }

    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {
            var list = new SinglyLinkedList();
            var listCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            IntStream.range(0, listCount).map(i -> scanner.nextInt())
                    .forEach(listItem -> {
                        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                        list.insertNode(listItem);
                    });
            var data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            var position = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            SinglyLinkedListNode list_head = insertNodeAtPosition(list.head, data, position);
            printSinglyLinkedList(list_head, bufferedWriter);
            bufferedWriter.newLine();
        }
    }

    static class SinglyLinkedListNode {
        private final int data;
        private SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        private SinglyLinkedListNode head;
        private SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            var node = new SinglyLinkedListNode(nodeData);
            if (this.head == null) this.head = node;
            else this.tail.next = node;
            this.tail = node;
        }
    }
}
