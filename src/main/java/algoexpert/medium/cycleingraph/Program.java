package algoexpert.medium.cycleingraph;

import java.util.HashSet;
import java.util.Set;

public class Program {

    public static void main(String args[]) {
        int[][] input =
                new int[][] {
                        {1, 3},
                        {2, 3, 4},
                        {0},
                        {},
                        {2, 5},
                        {}
                };
        Program p = new Program();
        System.out.println(p.cycleInGraph(input));
    }

    public boolean cycleInGraph(int[][] edges) {
        // Write your code here.
        for(int i = 0; i < edges.length; i++) {
            Set<Integer> visited = new HashSet<>();
            if(findLoop(edges, i, visited)) return true;
        }
        return false;
    }

    public boolean findLoop(int[][] edges, int e, Set<Integer> visited) {
        if(visited.contains(e)) return true;

        visited.add(e);
        int[] edge = edges[e];
        for(int o: edge) {
            if(findLoop(edges, o, visited)) return true;
        }
        visited.remove(e);
        return false;
    }
}
