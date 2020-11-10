package algoexpert.medium.powerset;

import java.util.*;

class Program {
    //1, 2, 3
    public static void main(String args[]) {
        System.out.println(Program.powerset(new ArrayList<Integer>(Arrays.asList(1, 2, 3))));
    }


    public static List<List<Integer>> powerset_1(List<Integer> array) {
        // Write your code here.

        Set<Integer> excluded = new HashSet();
        List<List<Integer>> powersets = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            List<Integer> powerset = new ArrayList<>();
            powerset.add(array.get(i));
            powersets.add(powerset);
            excluded.add(i);
            Set<Integer> excluded_1 = new HashSet<>(excluded);
            subPowerSet(array, excluded_1, powersets, powerset);
        }

        return powersets;
    }

    public static List<List<Integer>> powerset(List<Integer> array) {
        // Write your code here.
        Set<Integer> excluded = new HashSet();
        List<List<Integer>> powersets = new ArrayList<>();
        List<Integer> powerset = new ArrayList<>();
        powersets.add(powerset);
        subPowerSet(array, excluded, powersets, powerset);
        return powersets;
    }

    public static void subPowerSet(List<Integer> array, Set<Integer> excluded, List<List<Integer>> powersets,
                                   List<Integer> powerset) {
        if (excluded.size() == array.size()) {
            return;
        }

        for (int i = 0; i < array.size(); i++) {
            if (!excluded.contains(i)) {
                List<Integer> powerset_1 = new ArrayList<>(powerset);
                powerset_1.add(array.get(i));
                powersets.add(powerset_1);
                excluded.add(i);
                Set<Integer> excluded_1 = new HashSet<>(excluded);
                subPowerSet(array, excluded_1, powersets, powerset_1);
            }
        }
    }
}
