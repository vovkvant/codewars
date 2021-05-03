package algoexpert.hard.maxpathinbinary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Program {
    public static void main(String args[]) {
        TestBinaryTree test = new TestBinaryTree(1);
        test.insert(new int[]{2, 3, 4, 5, 6, 7}, 0);
        //test.insert(new int[]{2, -1}, 0);
        //test.insert(new int[] {-5, 3, 6}, 0);
        System.out.println(maxPathSum(test));
    }

    public static int maxPathSum(BinaryTree root) {
        List<Integer> list = new ArrayList<>();
        int result = maxPathSum2(root, list);
        return Stream.concat(list.stream(), Stream.of(result)).max(Integer::compareTo).get();
    }

    public static int maxPathSum2(BinaryTree root, List<Integer> list) {
        if (root == null) return 0;
        int leftMaxPathSum = maxPathSum2(root.left, list);
        int rightMaxPathSum = maxPathSum2(root.right, list);

        int maxAll = root.value + leftMaxPathSum + rightMaxPathSum;
        leftMaxPathSum += root.value;
        rightMaxPathSum += root.value;

        list.add(maxAll);
        list.add(leftMaxPathSum);
        list.add(rightMaxPathSum);
        return Stream.of(leftMaxPathSum, rightMaxPathSum, root.value).max(Integer::compareTo).get();
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
