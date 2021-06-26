package algoexpert.easy.tournamentwinner;

import java.util.*;

class Program {

    public static void main(String args[]) {
        ArrayList<ArrayList<String>> competitions = new ArrayList<ArrayList<String>>();
        ArrayList<String> competition1 = new ArrayList<String>(Arrays.asList("HTML", "C#"));
        ArrayList<String> competition2 = new ArrayList<String>(Arrays.asList("C#", "Python"));
        ArrayList<String> competition3 = new ArrayList<String>(Arrays.asList("Python", "HTML"));
        competitions.add(competition1);
        competitions.add(competition2);
        competitions.add(competition3);
        ArrayList<Integer> results = new ArrayList<Integer>(Arrays.asList(0, 1, 1));
        Program p = new Program();
        System.out.println(p.tournamentWinner(competitions, results));
    }

    public String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        // Write your code here.
        Map<String, Integer> resultTable = new HashMap<>();
        for (int i = 0; i < competitions.size(); i++) {
            ArrayList<String> competitors = competitions.get(i);
            resultTable.compute(competitors.get(results.get(i) == 1 ? 0 : 1), (k, v) -> (v == null) ? 3 : v + 3);
            resultTable.compute(competitors.get(results.get(i) == 1 ? 1 : 0), (k, v) -> (v == null) ? 0 : v);
        }
        Integer maxPoint = 0;
        String winner = "";
        for(Map.Entry<String, Integer> e: resultTable.entrySet()) {
            if(e.getValue() > maxPoint) {
                winner = e.getKey();
                maxPoint = e.getValue();
            }
        }
        return winner;
    }
}
