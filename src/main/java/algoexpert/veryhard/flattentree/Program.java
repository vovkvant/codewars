package algoexpert.veryhard.flattentree;

class Program {
    public static void main(String args[]) {
        BinaryTree root = new BinaryTree(1);
        BinaryTree left = new BinaryTree(2);
        BinaryTree right = new BinaryTree(3);
        root.left = left;
        root.right = right;
        BinaryTree left_left = new BinaryTree(4);
        BinaryTree left_right = new BinaryTree(5);
        left.left = left_left;
        left.right = left_right;
        BinaryTree left_right_left = new BinaryTree(7);
        BinaryTree left_right_right = new BinaryTree(8);
        left_right.left = left_right_left;
        left_right.right = left_right_right;

        BinaryTree result = flattenBinaryTree(root);

        BinaryTree curEl = result;
        do {
            System.out.println(curEl.value);
            curEl = curEl.right;
        } while (curEl != null);
    }

    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        return flatten(root, "left");
    }

    static BinaryTree flatten(BinaryTree node, String rightOrLeft) {
        if(node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return node;
        }
        BinaryTree flattenLeft = flatten(node.left, "right");
        BinaryTree flattenRight = flatten(node.right, "left");
        //get most right node from falttenLeft
        if(flattenLeft!=null) {
            flattenLeft.right = node;
            node.left = flattenLeft;
        } else {
            flattenLeft = node;
        }
        if(flattenRight!=null) {
            flattenRight.left = node;
            node.right = flattenRight;
        } else {
            flattenRight = node;
        }
        if ("right".equals(rightOrLeft)) {
            while(flattenRight.right!=null) {
                flattenRight = flattenRight.right;
            }
            return flattenRight;
        }
        else {
            while(flattenLeft.left!=null) {
                flattenLeft = flattenLeft.left;
            }
            return flattenLeft;
        }
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
}
