package algoexpert.veryhard.rightsmallerеhan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(8, 5, 11, -1, 3, 4, 2);
        System.out.println(rightSmallerThan(array));
    }

    public static List<Integer> rightSmallerThan(List<Integer> array) {
        // Write your code here.
        Node root = null;
        int[] result = new int[array.size()];
        Arrays.fill(result, 0);
        for (int i = 0; i < array.size(); i++) {
            root = insert(root, i, array.get(i), result);
        }

        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    static class Node {
        Node right;
        Node left;
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static Node insert(Node root, int index, int value, int[] result) {
        if (root == null) {
            root = new Node(value, index);
        } else if (root.value < value) {
            root.right = insert(root.right, index, value, result);
        } else if (root.value > value) {
            result[root.index]++;
            update(root.right, result);
            root.left = insert(root.left, index, value, result);
        }

        return root;
    }

    static void update(Node root, int[] result) {
        if (root == null) return;
        result[root.index]++;
        update(root.left, result);
        update(root.right, result);
    }
}
