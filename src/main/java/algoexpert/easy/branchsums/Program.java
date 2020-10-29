package algoexpert.easy.branchsums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Program {
    // This is the class of the input root. Do not edit it.
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        // Write your code here.
        if (root == null) return new ArrayList<>(Collections.emptyList());
        if (root.left == null && root.right == null) return new ArrayList<>(Collections.singletonList(root.value));

        List<Integer> leftResult = branchSums(root.left);
        List<Integer> rightResult = branchSums(root.right);
        leftResult.addAll(rightResult);
        ArrayList<Integer> result = leftResult.stream().map(r -> r + root.value)
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}
