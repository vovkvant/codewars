package algoexpert.medium.minheightbst;

import java.util.Arrays;
import java.util.List;

class Program {
    public static void main(String args[]) {
        List<Integer> array = Arrays.asList(1, 2, 5, 7, 10, 13, 14, 15, 22);
        minHeightBst(array);
    }

    public static BST minHeightBst(List<Integer> array) {
        int mid = (array.size() - 1) / 2;
        BST bst = new BST(array.get(mid));
        minHeightBst(array, 0, mid - 1, bst);
        minHeightBst(array, mid + 1, array.size() - 1, bst);
        return bst;
    }

    public static void minHeightBst(List<Integer> array, int low, int high, BST bst) {
        if (low > high) return;
        if (low == high) {
            bst.insert(array.get(low));
            return;
        }

        int middle = (low + high) / 2;
        bst.insert(array.get(middle));
        minHeightBst(array, low, middle - 1, bst);
        minHeightBst(array, middle + 1, high, bst);
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            System.out.println("insert " + value);
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}

