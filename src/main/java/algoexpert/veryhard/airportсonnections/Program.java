package algoexpert.veryhard.airportсonnections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {
    public static void main(String args[]) {
        List<String> airports = new ArrayList<String>(
                Arrays.asList(
                        "BGI", "CDG", "DEL", "DOH", "DSM", "EWR", "EYW", "HND", "ICN", "JFK", "LGA", "LHR",
                        "ORD", "SAN", "SFO", "SIN", "TLV", "BUD"));
        String startingAirport = "LGA";
        List<List<String>> routes = new ArrayList<List<String>>();
        routes.add(new ArrayList<String>(Arrays.asList("DSM", "ORD")));
        routes.add(new ArrayList<String>(Arrays.asList("ORD", "BGI")));
        routes.add(new ArrayList<String>(Arrays.asList("BGI", "LGA")));
        routes.add(new ArrayList<String>(Arrays.asList("SIN", "CDG")));
        routes.add(new ArrayList<String>(Arrays.asList("CDG", "SIN")));
        routes.add(new ArrayList<String>(Arrays.asList("CDG", "BUD")));
        routes.add(new ArrayList<String>(Arrays.asList("DEL", "DOH")));
        routes.add(new ArrayList<String>(Arrays.asList("DEL", "CDG")));
        routes.add(new ArrayList<String>(Arrays.asList("TLV", "DEL")));
        routes.add(new ArrayList<String>(Arrays.asList("EWR", "HND")));
        routes.add(new ArrayList<String>(Arrays.asList("HND", "ICN")));
        routes.add(new ArrayList<String>(Arrays.asList("HND", "JFK")));
        routes.add(new ArrayList<String>(Arrays.asList("ICN", "JFK")));
        routes.add(new ArrayList<String>(Arrays.asList("JFK", "LGA")));
        routes.add(new ArrayList<String>(Arrays.asList("EYW", "LHR")));
        routes.add(new ArrayList<String>(Arrays.asList("LHR", "SFO")));
        routes.add(new ArrayList<String>(Arrays.asList("SFO", "SAN")));
        routes.add(new ArrayList<String>(Arrays.asList("SFO", "DSM")));
        routes.add(new ArrayList<String>(Arrays.asList("SAN", "EYW")));

        System.out.println(airportConnections(airports, routes, startingAirport));
        System.out.println(airportConnections(airports, new ArrayList<>(), startingAirport));

    }

    //TODO refactor this solution
    public static int airportConnections(
            List<String> airports, List<List<String>> routes, String startingAirport) {
        // Write your code here.
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, AirportNode> nodes = new HashMap<>();
        for (String airport : airports) {
            graph.put(airport, new ArrayList<>());
            AirportNode airportNode = new AirportNode(airport, new HashSet<>(), startingAirport.equals(airport));
            nodes.put(airport, airportNode);
        }
        for (List<String> route : routes) {
            graph.compute(route.get(0), (key, val) -> {
                val.add(route.get(1));
                return val;
            });
        }
        System.out.println(graph.toString());

        Set<String> reachableAirports = depthSearch(graph, startingAirport);
        reachableAirports.stream().forEach(a -> {
            nodes.get(a).isReachable = true;
        });


        List<AirportNode> arr = new ArrayList<>();
        for(Map.Entry<String, AirportNode> e: nodes.entrySet()) {
            if(!e.getValue().isReachable) {
                e.getValue().connections = depthSearch(graph, e.getKey());
                System.out.println( e.getKey() + " : " + e.getValue().connections);
                arr.add(e.getValue());
            }
        }

        Collections.sort(arr, (a1, a2) -> {
            if(a1.connections.size() > a2.connections.size()) return -1;
            if(a1.connections.size() < a2.connections.size()) return 1;
            return 0;
        });
        System.out.println(arr);

        Set<String> skip = new HashSet<>();
        skip.add(startingAirport);
        for(int i = 0; i < arr.size(); i++) {
            AirportNode node = arr.get(i);
            if(skip.contains(node.airport)) continue;
            for(String c : node.connections) {
                skip.add(c);
                nodes.remove(c);
            }
        }
        System.out.println(nodes);
        return (int)nodes.entrySet().stream().filter(k -> !k.getValue().isReachable).count();
    }

    static Set<String> depthSearch(Map<String, List<String>> graph, String startingAirport) {
        Set<String> result = new HashSet<>();
        LinkedList<String> startingAirports = new LinkedList<>();
        startingAirports.addLast(startingAirport);
        for (int i = 0; i < startingAirports.size(); i++) {
            List<String> reachable = graph.get(startingAirports.get(i));
            for (String airport : reachable) {
                if (!result.contains(airport) && !startingAirport.equals(airport)) {
                    result.add(airport);
                    startingAirports.add(airport);
                }
            }
        }

        return result;
    }

    static class AirportNode {
        String airport;
        Set<String> connections;
        boolean isReachable;

        public AirportNode(String airport, Set<String> connections, boolean isReachable) {
            this.airport = airport;
            this.connections = connections;
            this.isReachable = isReachable;
        }
    }
}
