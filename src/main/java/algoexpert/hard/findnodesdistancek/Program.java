package algoexpert.hard.findnodesdistancek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Program {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        Program.BinaryTree root = new Program.BinaryTree(1);
        root.left = new Program.BinaryTree(2);
        root.right = new Program.BinaryTree(3);
        root.left.left = new Program.BinaryTree(4);
        root.left.right = new Program.BinaryTree(5);
        root.right.right = new Program.BinaryTree(6);
        root.right.right.left = new Program.BinaryTree(7);
        root.right.right.right = new Program.BinaryTree(8);
        int target = 3;
        int k = 3;

        Program p = new Program();
        System.out.println(p.findNodesDistanceK(root, target, k));
    }

    public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        // Write your code here.
        LinkedList<BinaryTree> pathToTarget = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        pathToTarget = findTargetNode(tree, target, pathToTarget);
        findTop(pathToTarget, k, 0, result, null);

        return result;
    }

    public void findTop(LinkedList<BinaryTree> pathToTarget, int k, int i,
                        List<Integer> result, BinaryTree lastExcluded) {
        BinaryTree current = pathToTarget.pollLast();
        if (current != null) {
            if (i == k) {
                result.add(current.value);
                return;
            }
            findTop(pathToTarget, k, i + 1, result, current);
            System.out.println(current.value);

            if (current.right != null && (lastExcluded == null || current.right.value != lastExcluded.value)) {
                findBottom(current.right, k, i + 1, result);
            }
            if (current.left != null && (lastExcluded == null || current.left.value != lastExcluded.value)) {
                findBottom(current.left, k, i + 1, result);
            }
        }
    }

    public void findBottom(BinaryTree tree, int k, int i, List<Integer> result) {
        if (tree == null) return;
        if (i == k) {
            result.add(tree.value);
            return;
        }
        findBottom(tree.right, k, i + 1, result);
        findBottom(tree.left, k, i + 1, result);
    }


    public LinkedList<BinaryTree> findTargetNode(BinaryTree tree, int target, LinkedList<BinaryTree> pathToTarget) {
        if (tree == null) return null;
        if (tree.value == target) {
            pathToTarget.addLast(tree);
            return pathToTarget;
        } else {
            pathToTarget.addLast(tree);
            LinkedList<BinaryTree> leftResult = findTargetNode(tree.left, target, new LinkedList<>(pathToTarget));
            LinkedList<BinaryTree> rightResult = findTargetNode(tree.right, target, new LinkedList<>(pathToTarget));
            if (leftResult != null) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }
}
