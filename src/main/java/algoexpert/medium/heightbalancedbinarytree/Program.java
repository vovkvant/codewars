package algoexpert.medium.heightbalancedbinarytree;

import java.util.ArrayList;
import java.util.List;

public class Program {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public boolean heightBalancedBinaryTree111(BinaryTree tree) {
        // Write your code here.
        List<Integer> result = new ArrayList<>();
        //heightBalancedBinaryTree2(tree);
        int minHeight = result.stream().min(Integer::compareTo).orElseGet(() -> 0);
        int maxHeight = result.stream().max(Integer::compareTo).orElseGet(() -> 0);
        return maxHeight - minHeight <= 1;
    }

    public boolean heightBalancedBinaryTree(BinaryTree tree) {
        // Write your code here.
        if (tree.left == null && tree.right == null) return true;
        if (tree.left == null && tree.right != null) {
            BinaryTree right = tree.right;
            if(right.right != null || right.left!=null) return false; else return true;
        } else if(tree.right == null && tree.left!=null) {
            BinaryTree left = tree.left;
            if(left.right != null || left.left!=null) return false; else return true;
        } else {
            return heightBalancedBinaryTree(tree.right) && heightBalancedBinaryTree(tree.left);
        }
    }
}
