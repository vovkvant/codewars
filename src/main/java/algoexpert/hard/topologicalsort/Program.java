package algoexpert.hard.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Program {

    public static void main(String args[]) {
        //List<Integer> jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        //        //Integer[][] depsArray = new Integer[][]{{1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3}};
        List<Integer> jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Integer[][] depsArray = new Integer[][]{{3, 1}, {8, 1}, {8, 7}, {5, 7}, {5, 2},
                {1, 4}, {1, 6}, {1, 2}, {7, 6}};

        List<Integer[]> deps = new ArrayList<Integer[]>();
        fillDeps(depsArray, deps);

        System.out.println(topologicalSort(jobs, deps));
    }

    static void fillDeps(Integer[][] depsArray, List<Integer[]> deps) {
        for (Integer[] depArray : depsArray) {
            deps.add(depArray);
        }
    }


    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        Map<Integer, List<Integer>> forwardDependencies = new HashMap<>();
        Map<Integer, List<Integer>> backwardDependencies = new HashMap<>();

        for(Integer job: jobs) {
            forwardDependencies.put(job, new ArrayList<>());
            backwardDependencies.put(job, new ArrayList<>());
        }

        for(Integer[] dependency: deps) {
            forwardDependencies.get(dependency[0]).add(dependency[1]);
            backwardDependencies.get(dependency[1]).add(dependency[0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        for(Integer job: backwardDependencies.keySet()) {
            if(backwardDependencies.get(job).size() == 0) {
                visited.add(job);
            }
        }



        List<Integer> result = new ArrayList<>();
        result.addAll(visited);
        for(int i = 0; i < result.size(); i++) {
            Integer job = result.get(i);
            List<Integer> dependecies = forwardDependencies.get(job);
            for(Integer dependency: dependecies) {
                if(visited.contains(dependency)) continue;
                List<Integer> blockers = backwardDependencies.get(dependency);
                if(blockers.size() == 0 || visited.containsAll(blockers)) {
                    visited.add(dependency);
                    result.add(dependency);
                }
            }
        }
        if(result.size()!=jobs.size()) {
            return new ArrayList<>();
        }
        return result;
    }

}
