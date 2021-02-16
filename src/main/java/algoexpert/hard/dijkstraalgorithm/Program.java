package algoexpert.hard.dijkstraalgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Program {

    public static void main(String[] args) {
        int[][][] edges1 = new int[][][]{{{1, 7}}, {{2, 6}, {3, 20}, {4, 3}}, {{3, 14}}, {{4, 2}}, {}, {}};
        int[][][] edges2 = new int[][][]{
                {{1, 4}, {7, 11}},
                {{0, 4}, {2, 11}, {7, 14}},
                {{1, 11}, {3, 10}, {5, 7}, {8, 5}},
                {{2, 10}, {4, 12}, {5, 17}},
                {{3, 12}, {5, 13}, {6, 3}},
                {{2, 7}, {3, 17}, {4, 13}, {6, 5}},
                {{4, 3}, {5, 6}, {7, 4}, {9, 8}},
                {{0, 11}, {1, 14}, {6, 4}, {8, 10}},
                {{2, 5}, {6, 9}, {7, 10}},
                {}
        };
        Program p = new Program();
        int[] result = p.dijkstrasAlgorithm(8, edges1);
        System.out.println(Arrays.toString(result));
    }


    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        // Write your code here.
        int[] distTo = new int[edges.length];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Element> queue = new PriorityQueue<>();
        distTo[start] = 0;
        queue.add(new Element(start, 0));
        while (!queue.isEmpty()) {
            findDistance(queue, distTo, edges);
        }
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) distTo[i] = -1;
        }

        return distTo;
    }

    private void findDistance(Queue<Element> queue, int[] distTo, int[][][] edges) {
        Element currEl = queue.poll();
        int[][] adjEl = edges[currEl.number];
        for (int[] elAndDist : adjEl) {
            int elNumber = elAndDist[0];
            int dist = elAndDist[1];
            if (distTo[currEl.number] + dist < distTo[elNumber]) {
                distTo[elNumber] = distTo[currEl.number] + dist;
                Element anotherEl = new Element(elNumber, dist);
                queue.add(anotherEl);
            }

        }
    }

    static class Element implements Comparable<Element> {
        private int number;
        private int dist;

        public Element(int number, int dist) {
            this.number = number;
            this.dist = dist;
        }

        @Override
        public int compareTo(Element other) {
            if (other.dist > this.dist) return 1;
            else if (other.dist < this.dist) return -1;
            else return 0;
        }
    }
}
