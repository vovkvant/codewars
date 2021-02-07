package algoexpert.hard.maxpathinbinary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Program {
    public static void main(String args[]) {
        TestBinaryTree test = new TestBinaryTree(1);
        //test.insert(new int[] {2, 3, 4, 5, 6, 7}, 0);
        test.insert(new int[] {2, -1}, 0);
        maxPathSum(test);
    }

    public static int maxPathSum(BinaryTree tree) {
        // Write your code here.
        List<Integer> leftSum = sum(tree.left);
        List<Integer> rightSum = sum(tree.right);
        leftSum.add(0);
        rightSum.add(0);
        List<Integer> newList = new ArrayList<>();
        for(Integer ls : leftSum) {
            for(Integer rs : rightSum) {
                newList.add(ls + rs + tree.value);
            }
        }
        System.out.println(newList);
        return Collections.max(newList);
    }

    static List<Integer> sum(BinaryTree tree) {
        if(tree.left == null && tree.right == null) {
            List<Integer> newList = new ArrayList<>();
            newList.add(tree.value);
            return newList;
        }
        if(tree.left == null || tree.right == null) {
            return new ArrayList<>();
        }
        List<Integer> leftSum = sum(tree.left);
        List<Integer> rightSum = sum(tree.right);
        List<Integer> newList = new ArrayList<>();
        for(int i = 0; i < leftSum.size(); i++) {
            newList.add(leftSum.get(i) + tree.value);
        }
        for(int i = 0; i < rightSum.size(); i++) {
            newList.add(rightSum.get(i) + tree.value);
        }
        return newList;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    static class TestBinaryTree extends Program.BinaryTree {
        public TestBinaryTree(int value) {
            super(value);
        }

        public void insert(int[] values, int i) {
            if (i >= values.length) {
                return;
            }
            ArrayDeque<BinaryTree> queue = new ArrayDeque<Program.BinaryTree>();
            queue.addLast(this);
            while (queue.size() > 0) {
                Program.BinaryTree current = queue.pollFirst();
                if (current.left == null) {
                    current.left = new Program.BinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.left);
                if (current.right == null) {
                    current.right = new Program.BinaryTree(values[i]);
                    break;
                }
                queue.addLast(current.right);
            }
            insert(values, i + 1);
        }
    }
}
