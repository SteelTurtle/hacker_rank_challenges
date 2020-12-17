package com.gorillacorp.hackerrank.data_structures;

public class ListOperations {

    // Compare two linked lists
    // https://www.hackerrank.com/challenges/compare-two-linked-lists/problem
    // Compare the two linked lists and return 1 if the lists are equal. Otherwise,
    // return 0. Do NOT print anything to stdout/console.
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return true;
        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    // Merge two sorted linked lists
    // https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem
    // You’re given the pointer to the head nodes of two sorted linked lists. The data in both lists will
    // be sorted in ascending order. Change the next pointers to obtain a single, merged linked list which
    // also has data in ascending order. Either head pointer given may be null meaning that the
    // corresponding list is empty.
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        if (head1.data > head2.data) {
            SinglyLinkedListNode temp = head2;
            head2 = head2.next;
            temp.next = head1;
            head1 = temp;
        }
        head1.next = mergeLists(head1.next, head2);
        return head1;
    }

    /////////////////////////////////////////////////////
    // rubbish classes for the functions to work....
    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }
}
