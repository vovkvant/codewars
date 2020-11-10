package algoexpert.medium.permutations;

import java.util.*;

public class Program {

        public static void main(String args[]) {
            List<Integer> array = Arrays.asList(1, 2, 3);
            List<List<Integer>> result = getPermutations(array);
            System.out.println(result);
        }

        public static List<List<Integer>> getPermutations(List<Integer> array) {
            // Write your code here.
            List<List<Integer>> permutations = new ArrayList<>();
            for(int i = 0; i < array.size(); i++) {
                List<Integer> perm = new ArrayList<>();
                perm.add(array.get(i));
                Set<Integer> excludeIndexes = new HashSet<>();
                excludeIndexes.add(i);
                subPermutation(permutations, array, perm, excludeIndexes);
            }
            return permutations;
        }

        public static void subPermutation(List<List<Integer>> permutations, List<Integer> array,
                                          List<Integer> perm, Set<Integer> excludeIndexes) {
            if(excludeIndexes.size() == array.size()) {
                permutations.add(perm);
                return;
            }

            for(int i = 0; i < array.size(); i++) {
                if(!excludeIndexes.contains(i)) {
                    Set<Integer> excludeIndexes_1 = new HashSet<>(excludeIndexes);
                    excludeIndexes_1.add(i);
                    List<Integer> perm_1 = new ArrayList<>(perm);
                    perm_1.add(array.get(i));
                    subPermutation(permutations, array, perm_1, excludeIndexes_1);
                }
            }
        }

}
