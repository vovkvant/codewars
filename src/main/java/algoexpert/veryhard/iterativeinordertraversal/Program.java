package algoexpert.veryhard.iterativeinordertraversal;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Program {
    public static void main(String[] args) {
        BinaryTree root = new Program.BinaryTree(1);
        root.left = new Program.BinaryTree(2, root);
        root.left.left = new Program.BinaryTree(4, root.left);
        root.left.left.right = new Program.BinaryTree(9, root.left.left);
        root.right = new Program.BinaryTree(3, root);
        root.right.left = new Program.BinaryTree(6, root.right);
        root.right.right = new Program.BinaryTree(7, root.right);

        iterativeInOrderTraversal(root, Program::testCallback);
    }

    public static Void testCallback(Program.BinaryTree tree) {
        System.out.println(tree.value);
        return null;
    }

    //TODO make it more optimal, without Set
    public static void iterativeInOrderTraversal(
            BinaryTree tree, Function<BinaryTree, Void> callback) {
        // Write your code here.
        Set<Integer> visited = new HashSet<>();
        while(true) {
            if(tree == null) break;
            if(visited.contains(tree.value)) {
                tree = tree.parent;
                continue;
            }
            if(tree.left == null || visited.contains(tree.left.value)) {
                callback.apply(tree);
                visited.add(tree.value);
                if(tree.right != null) {
                    tree = tree.right;
                } else {
                    tree = tree.parent;
                }
            } else {
                tree = tree.left;
            }
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree parent;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
