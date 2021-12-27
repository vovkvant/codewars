package algoexpert.medium.reconstructbst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Program {
    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(10, 4, 2, 1, 5, 17, 19, 18);
        Program p = new Program();
        p.reconstructBst(list);
    }

    public BST reconstructBst(List<Integer> preOrderTraversalValues) {
        //return reconstructTree1(preOrderTraversalValues);
        return reconstructTree2(preOrderTraversalValues, 0, preOrderTraversalValues.size() - 1);
    }

    //solution 1
    public BST reconstructTree1(List<Integer> preOrderTraversalValues) {
        int rootValue = preOrderTraversalValues.get(0);
        BST bst = new BST(rootValue);
        List<Integer> leftList = preOrderTraversalValues.stream().skip(1)
                .filter(n -> n < rootValue).collect(Collectors.toList());
        List<Integer> rightList = preOrderTraversalValues.stream().skip(1)
                .filter(n -> n >= rootValue).collect(Collectors.toList());
        if(!leftList.isEmpty()) {
            bst.left = reconstructTree1(leftList);
        }
        if(!rightList.isEmpty()) {
            bst.right = reconstructTree1(rightList);
        }
        return bst;
    }

    //solution 2
    public BST reconstructTree2(List<Integer> preOrderTraversalValues, int low, int high) {
        int rootValue = preOrderTraversalValues.get(low);
        BST bst = new BST(rootValue);
        if(low!=high) {
            int leftLow = low + 1;
            int leftHigh = leftLow ;
            while(leftHigh < preOrderTraversalValues.size() &&
                    preOrderTraversalValues.get(leftHigh) < rootValue) {
                leftHigh++;
            }

            if(leftLow <= leftHigh - 1) {
                bst.left = reconstructTree2(preOrderTraversalValues, leftLow, leftHigh - 1);
            }
            if(leftHigh <= high) {
                bst.right = reconstructTree2(preOrderTraversalValues, leftHigh, high);
            }
        }
        return bst;
    }

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }
}
