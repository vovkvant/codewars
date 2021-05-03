package algoexpert.hard.validatethreenodes;

public class Program {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    //TODO invetigate the solution from algoexpert
    //TODO create more optimal solution
    public static void main(String args[]) {
        BST root = new Program.BST(5);
        root.left = new Program.BST(2);
        root.right = new Program.BST(7);
        root.left.left = new Program.BST(1);
        root.left.right = new Program.BST(4);
        root.right.left = new Program.BST(6);
        root.right.right = new Program.BST(8);
        root.left.left.left = new Program.BST(0);
        root.left.right.left = new Program.BST(3);

        BST nodeOne = root;
        BST nodeTwo = root.left;
        BST nodeThree = root.left.right.left;

        Program p = new Program();
        System.out.println(p.validateThreeNodes(nodeOne, nodeTwo, nodeThree));
    }

    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        // Write your code here.
        boolean result_1 = search(nodeOne, nodeOne, nodeTwo, nodeThree, 0, 0, 0);
        boolean result_2 = search(nodeThree, nodeOne, nodeTwo, nodeThree, 0, 0, 0);
        return result_1 || result_2;
    }

    static int counter = 0;

    boolean search(BST root, BST nodeOne, BST nodeTwo, BST nodeThree, int oneIdx, int twoIdx, int threeIdx) {
        if (root == null)
            return (oneIdx < twoIdx && twoIdx < threeIdx) || (oneIdx > twoIdx && twoIdx > threeIdx);
        if (root.value == nodeTwo.value) {
            twoIdx = counter++;
        }
        if (root.value == nodeOne.value) {
            oneIdx = counter++;
        }
        if (root.value == nodeThree.value) {
            threeIdx = counter++;
        }
        return search(root.right, nodeOne, nodeTwo, nodeThree, oneIdx, twoIdx, threeIdx) ||
                search(root.left, nodeOne, nodeTwo, nodeThree, oneIdx, twoIdx, threeIdx);

    }
}
