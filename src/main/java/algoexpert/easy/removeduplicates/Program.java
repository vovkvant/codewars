package algoexpert.easy.removeduplicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String args[]) {
        Program.LinkedList input = new Program.LinkedList(1);
        addMany(input, new ArrayList<Integer>(Arrays.asList(1, 2, 2)));
        Program.LinkedList output = new Program().removeDuplicatesFromLinkedList(input);
        System.out.println(getNodesInArray(output));
    }

    public static LinkedList addMany(Program.LinkedList ll, List<Integer> values) {
        Program.LinkedList current = ll;
        while (current.next != null) {
            current = current.next;
        }
        for (int value : values) {
            current.next = new Program.LinkedList(value);
            current = current.next;
        }
        return ll;
    }

    public static List<Integer> getNodesInArray(Program.LinkedList ll) {
        List<Integer> nodes = new ArrayList<Integer>();
        Program.LinkedList current = ll;
        while (current != null) {
            nodes.add(current.value);
            current = current.next;
        }
        return nodes;
    }

    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        // Write your code here.
        int prevValue = linkedList.value;
        LinkedList prev = linkedList;
        LinkedList current = linkedList.next;
        while (current != null) {
            if (prevValue == current.value) {
                prev.next = current.next;
                current = current.next;
            } else {
                prev = current;
                prevValue = current.value;
                current = current.next;
            }
        }
        return linkedList;
    }
}
