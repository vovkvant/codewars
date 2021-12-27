package algoexpert.medium.binarytreediameter;

class Program {
    // This is an input class. Do not edit.
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public int binaryTreeDiameter(BinaryTree tree) {
        int treeDiam = binaryTreeDiameter_1(tree);
        int maxSubTree = Math.max(binaryTreeDiameter_1(tree.left), binaryTreeDiameter_1(tree.right));
        return Math.max(treeDiam, maxSubTree);
    }

    public int binaryTreeDiameter_1(BinaryTree tree) {
        int leftHeight = tree == null ? 0 : bstHeight(tree.left);
        int rightHeight = tree == null ? 0 : bstHeight(tree.right);
        return leftHeight + rightHeight;
    }

    int bstHeight(BinaryTree tree) {
        if(tree == null) return 0;
        return 1 + Math.max(bstHeight(tree.left), bstHeight(tree.right));
    }
}
