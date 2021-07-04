package algoexpert.medium.findkthlargestvalueinbst;

import java.util.ArrayList;
import java.util.List;

public class Program {

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        Program.BST root = new Program.BST(15);
        root.left = new Program.BST(5);
        root.left.left = new Program.BST(2);
        root.left.left.left = new Program.BST(1);
        root.left.left.right = new Program.BST(3);
        root.left.right = new Program.BST(5);
        root.right = new Program.BST(20);
        root.right.left = new Program.BST(17);
        root.right.right = new Program.BST(22);
        int k = 3;
        int expected = 17;
        Program p = new Program();
        p.findKthLargestValueInBst(root, k);
    }

    //TODO second solution?
    public int findKthLargestValueInBst(BST tree, int k) {
        // Write your code here.
        List<Integer> list = new ArrayList<>();
        buildList(tree, list);
        return list.get(k-1);
    }

    void buildList(BST tree, List<Integer> result) {
        if(tree == null) return;
        if (tree.left == null && tree.right == null) {
            result.add(tree.value);
            return;
        }
        buildList(tree.right, result);
        result.add(tree.value);
        buildList(tree.left, result);
    }
}
