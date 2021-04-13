package algoexpert.veryhard.rightsiblingtree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        LinkedList<BinaryTree> binaryTreeList = new LinkedList<>();
        binaryTreeList.add(null);

        Program.BinaryTree root = new Program.BinaryTree(1);
        insert(root, new int[] {2, 3, 4, 5, 6, 7, 8, 9});
        root.left.right.right = new Program.BinaryTree(10);
        root.right.left.left = new Program.BinaryTree(11);
        root.right.right.left = new Program.BinaryTree(12);
        root.right.right.right = new Program.BinaryTree(13);
        root.right.left.left.left = new Program.BinaryTree(14);
        Program.BinaryTree mutatedRoot = Program.rightSiblingTree(root);
        List<Integer> actual = getDfsOrder(mutatedRoot);
        System.out.println(actual);
        //var expected = Arrays.asList(1, 2, 4, 8, 9, 5, 6, 11, 14, 7, 12, 13, 3, 6, 11, 14, 7, 12, 13);

    }

    public static BinaryTree rightSiblingTree(BinaryTree root) {
        // Write your code here.
        List<BinaryTree> binaryTreeList = new ArrayList<>();
        binaryTreeList.add(root);

        while(true) {
            List<BinaryTree> newbinaryTreeList = new ArrayList<>();
            for(int i = 0; i< binaryTreeList.size(); i++) {
                newbinaryTreeList.add(binaryTreeList.get(i) !=null ? binaryTreeList.get(i).left : null);
                newbinaryTreeList.add(binaryTreeList.get(i) !=null ? binaryTreeList.get(i).right : null);
                BinaryTree next = null;
                if(i + 1 < binaryTreeList.size()) {
                    next = binaryTreeList.get(i+1);
                }
                if(binaryTreeList.get(i) != null) {
                    binaryTreeList.get(i).right = next;
                }
            }
            binaryTreeList.clear();
            binaryTreeList.addAll(newbinaryTreeList);

            if(binaryTreeList.stream().filter(e -> e!=null).count() == 0) break;
        }

        return root;
    }

    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void insert(Program.BinaryTree root, int[] values) {
        insert(root, values, 0);
    }

    public static void insert(Program.BinaryTree root, int[] values, int i) {
        if (i >= values.length) {
            return;
        }
        Deque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
        queue.addLast(root);
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
        insert(root, values, i + 1);
    }

    public static List<Integer> getDfsOrder(Program.BinaryTree tree) {
        List<Integer> values = new ArrayList<Integer>();
        values.add(tree.value);
        if (tree.left != null) {
            values.addAll(getDfsOrder(tree.left));
        }
        if (tree.right != null) {
            values.addAll(getDfsOrder(tree.right));
        }
        return values;
    }


}
