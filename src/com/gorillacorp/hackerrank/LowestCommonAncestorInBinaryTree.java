package com.gorillacorp.hackerrank;

import java.util.Scanner;

// Given a pointer to the root of the binary search tree and two values, return the lowest
// common ancestor (LCA) of  and  in the binary search tree.
public class LowestCommonAncestorInBinaryTree {

    // Complete the function lca in the editor below. It should return a pointer to the lowest common
    // ancestor node of the two values given.
    //
    // lca has the following parameters:
    //- root: a pointer to the root node of a binary search tree
    //- v1: a node.data value
    //- v2: a node.data value
    // Input format:
    // The first line contains an integer, , the number of nodes in the tree.
    // The second line contains  space-separated integers representing  values.
    // The third line contains two space-separated integers, v1 and v2:
    // 6
    // 4 2 3 1 7 6
    // 1 7
    // Sample output:
    // (reference to node 4)

    private static Node lca(final Node root,
                            final int v1,
                            final int v2) {
        // Write your code here.
        if (v1 > root.data && v2 > root.data) return lca(root.right, v1, v2);
        if (v1 < root.data && v2 < root.data) return lca(root.left, v1, v2);
        return root;
    }

    // prepare the binary tree for traversal...
    public static Node insert(final Node root,
                              final int data) {
        if (root == null) return new Node(data);
        else {
            Node currentNode;
            if (data <= root.data) {
                currentNode = insert(root.left, data);
                root.left = currentNode;
            } else {
                currentNode = insert(root.right, data);
                root.right = currentNode;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Node root;
        int v1;
        int v2;
        try (var scanner = new Scanner(System.in)) {
            var t = scanner.nextInt();
            root = null;
            while (t-- > 0) {
                var data = scanner.nextInt();
                root = insert(root, data);
            }
            v1 = scanner.nextInt();
            v2 = scanner.nextInt();
        }
        var retrievedNode = lca(root, v1, v2);
        System.out.println(retrievedNode.data);
    }

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
