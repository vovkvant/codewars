package algoexpert.veryhard.ziplinkedlist;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        TestLinkedList head = new TestLinkedList(1);
        head.addMany(new int[] {2, 3, 4, 5, 6});
        Program p = new Program();
        List<Integer> actual = p.getNodesInArray(new Program().zipLinkedList(head));
        System.out.println(actual);
    }

    public LinkedList zipLinkedList(LinkedList linkedList) {
        // Write your code here.
        LinkedList head = linkedList;
        int length = 0;
        while (linkedList != null) {
            linkedList = linkedList.next;
            length++;
        }

        if(length < 3) {
            return head;
        }


        //split
        //can be done faster
        int middle = length / 2;
        LinkedList middleHead = head;
        for (int count = 0; count < middle; count++) {
            middleHead = middleHead.next;
        }

        //reverse
        /*
        LinkedList prev = middleHead;
        LinkedList current = middleHead.next;
        LinkedList next = middleHead.next.next;
        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if(current!=null) {
                next = current.next;
            }
        }
        */
        LinkedList current = middleHead.next;
        LinkedList next = middleHead.next.next;
        while (current != null) {
            current.next = middleHead;
            middleHead = current;
            current = next;
            if(current!=null) {
                next = current.next;
            }
        }

        LinkedList tail = middleHead;
        LinkedList realHead = head;

        for (int count = 0; count < middle; count++) {
            LinkedList headTemp = head.next;
            LinkedList tailTemp = tail.next;
            head.next = tail;
            tail.next = headTemp;
            tail = tailTemp;
            head = headTemp;
        }
        head.next = null;

        return realHead;
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

    public List<Integer> getNodesInArray(LinkedList linkedList) {
        List<Integer> nodes = new ArrayList<Integer>();
        LinkedList current = linkedList;
        while (current != null) {
            nodes.add(current.value);
            current = current.next;
        }
        return nodes;
    }

    static class TestLinkedList extends Program.LinkedList {
        public TestLinkedList(int value) {
            super(value);
        }

        public void addMany(int[] values) {
            Program.LinkedList current = this;
            while (current.next != null) {
                current = current.next;
            }
            for (int value : values) {
                current.next = new Program.LinkedList(value);
                current = current.next;
            }
        }
    }
}
