package com.seungmoo.codingtest.tmap;

import java.util.*;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        int[] A = {5,1,2,6,6,1,3,1,3,4,3,4,4,6,1,2,4,1,6,2};

        int pairLength = A.length / 2;

        int[] lefts = new int[pairLength];
        int[] rights = new int[pairLength];

        for (int i = 0; i < pairLength; i++) {
            int leftIndex = i * 2;
            int rightIndex = i * 2 + 1;
            int left = A[leftIndex];
            int right = A[rightIndex];
            lefts[i] = left;
            rights[i] = right;
        }

        System.out.println("rights: " + Arrays.toString(rights));
        System.out.println("lefts: " + Arrays.toString(lefts));

        int pairCount = 1;

        List<Pair> pairs = new LinkedList<>();

        for (int rightIdx = 0; rightIdx < rights.length - 1; rightIdx++) {
            for (int leftIdx = rightIdx + 1; leftIdx < lefts.length; leftIdx++) {
                if (rights[rightIdx] == lefts[leftIdx]) {
                    System.out.printf("right: %d,%d, rightIndex: %d, left: %d,%d, leftIndex: %d\n", lefts[rightIdx], rights[rightIdx], rightIdx, lefts[leftIdx], rights[leftIdx], leftIdx);
                    pairs.add(new Pair(new Node(lefts[rightIdx], rights[rightIdx], rightIdx), new Node(lefts[leftIdx], rights[leftIdx], leftIdx)));
                    rightIdx++;
                    leftIdx = rightIdx;
                }
            }
        }

        List<Pair> sortedPairs = pairs.stream()
                .sorted(Comparator.comparing(pair -> pair.rightNode.index))
                .collect(Collectors.toList());

        System.out.println();

        for (Pair pair : sortedPairs) {
            System.out.println(pair);
        }

        if (sortedPairs.isEmpty()) {
            System.out.println(pairLength - 1);
        } else {

            Pair pair = sortedPairs.get(0);
            while (true) {
                Pair finalPair = pair;
                Optional<Pair> matchedPair = sortedPairs.stream()
                        .filter(sortedPair -> sortedPair.leftNode.index == finalPair.rightNode.index)
                        .findFirst();

                if (matchedPair.isPresent()) {
                    pair = matchedPair.get();
                    pairCount++;
                } else {
                    break;
                }
            }

            System.out.println("pairCount: " + pairCount);

            int result = pairLength - pairCount - 1;
            System.out.println(result);
        }
    }

    public static class Pair {
        private final Node leftNode;
        private final Node rightNode;

        public Pair(Node leftNode, Node rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public int size() {
            return leftNode.index - rightNode.index;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }

    public static class Node {
        private final int left;
        private final int right;
        private final int index;

        public Node(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", index=" + index +
                    '}';
        }
    }
}
