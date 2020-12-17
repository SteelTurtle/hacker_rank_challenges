package com.gorillacorp.hackerrank;

import java.util.Scanner;
import java.util.stream.IntStream;

// A binary tree is a tree which is characterized by one of the following properties:
// -It can be empty (null).
// -It contains a root node only.
// -It contains a root node with a left subtree, a right subtree, or both. These subtrees are also binary
// trees.
// In-order traversal is performed as:
// -Traverse the left subtree.
// -Visit root.
// -Traverse the right subtree.
// For this in-order traversal, start from the left child of the root node and keep exploring the left
// subtree until you reach a leaf. When you reach a leaf, back up to its parent, check for a right child and
// visit it if there is one. If there is not a child, you've explored its left and right subtrees fully. If
// there is a right child, traverse its left subtree then its right in the same manner. Keep doing this
// until you have traversed the entire tree. You will only store the values of a node as you visit when one
// of the following is true:
// -it is the first node visited, the first time visited
// -it is a leaf, should only be visited once
//- all of its subtrees have been explored, should only be visited once while this is true
// -it is the root of the tree, the first time visited
// Swapping: Swapping subtrees of a node means that if initially node has left subtree L and right subtree
// R, then after swapping, the left subtree will be R and the right subtree, L.
//
// Input Format:
// The first line contains n, number of nodes in the tree.
// Each of the next n lines contains two integers, a b, where a is the index of left child, and b is the
// index of right child of i(nth) node.
// Note: -1 is used to represent a null node.
// The next line contains an integer, t, the size of "queries".
// Each of the next t lines contains an integer queries[i]", each being a value k.
//
// Output Format:
// For each k, perform the swap operation and store the indices of your in-order traversal to your result
// array. After all swap operations have been performed, return your result array for printing.
public class SwapNodesAlgorithm {

    private static void inorderTraverse(Node[] nodes, int index) {
        if (index != -1) {
            inorderTraverse(nodes, nodes[index].leftNode);
            System.out.print(index + " ");
            inorderTraverse(nodes, nodes[index].rightNode);
        }
    }

    static void swapNodes(Node[] nodes, int k, int maxDepth) {
        IntStream.iterate(k, h -> h <= maxDepth, h -> h + k)
                .forEach(h -> swapNodesInternal(nodes, h, 1, 1));
    }

    private static void swapNodesInternal(Node[] nodes,
                                          int depthToSwap,
                                          int currentIndex,
                                          int currentDepth) {
        if (currentIndex >= 1) {
            Node node = nodes[currentIndex];
            if (currentDepth == depthToSwap) node.swapTreeNodes();
            else {
                swapNodesInternal(nodes, depthToSwap, node.leftNode, currentDepth + 1);
                swapNodesInternal(nodes, depthToSwap, node.rightNode, currentDepth + 1);
            }
        }
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            int N = scanner.nextInt();
            Node[] array = new Node[N + 1]; // represents our tree
            IntStream.rangeClosed(1, N)
                    .forEach(i -> array[i] = new Node(scanner.nextInt(), scanner.nextInt()));
            /* Perform swaps and print inorder traversals */
            int T = scanner.nextInt();
            while (T-- > 0) {
                int K = scanner.nextInt();
                swapNodes(array, K, N);
                inorderTraverse(array, 1);
                System.out.println();
            }
        }
    }

    private static class Node {
        private int leftNode;
        private int rightNode;

        public Node(int leftNode, int rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public void swapTreeNodes() {
            int temp = leftNode;
            leftNode = rightNode;
            rightNode = temp;
        }
    }
}
