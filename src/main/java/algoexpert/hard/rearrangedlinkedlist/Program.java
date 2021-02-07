package algoexpert.hard.rearrangedlinkedlist;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String args[]) {
        LinkedList head = new LinkedList(3);
        head.next = new LinkedList(0);
        head.next.next = new LinkedList(5);
        head.next.next.next = new LinkedList(2);
        head.next.next.next.next = new LinkedList(1);
        head.next.next.next.next.next = new LinkedList(4);
        LinkedList result = Program.rearrangeLinkedList(head, 4);
        List array = linkedListToArray(result);
        System.out.println(array);
    }

    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        // Write your code here.
        LinkedList listToMoveHead = null;
        LinkedList listToMoveTail = null;
        LinkedList prev = null;
        LinkedList oldHead = head;
        while(head !=null) {
            List array = linkedListToArray(oldHead);
            System.out.println(array);
            if(prev!=null) {
                System.out.println(prev.value);
            } else {
                System.out.println("prev = null");
            }
            if(head.value == k) {
                if(listToMoveHead != null) {
                    prev.next = head;
                    listToMoveTail.next = head.next;
                    head.next = listToMoveHead;
                    listToMoveHead = head;
                } else{
                    listToMoveHead = head;
                    listToMoveTail = head;
                }
                head = head.next;
                continue;
            }
            if(head.value < k) {
                if(listToMoveHead != null) {
                    if(listToMoveHead == oldHead) {
                        oldHead = head;
                    }
                    if(prev!=null) {
                        prev.next = head;
                    }
                    prev = head;

                    listToMoveTail.next = head.next;
                    head.next = listToMoveHead;
                    head = listToMoveTail.next;
                    continue;
                } else {
                    prev = head;
                    head = head.next;
                }
            }
            if(head.value > k) {
                if(listToMoveHead == null) {
                    listToMoveHead = head;
                }
                listToMoveTail = head;
                head = head.next;
                continue;
            }
        }

        return oldHead;
    }

    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }

    public static List<Integer> linkedListToArray(Program.LinkedList head) {
        List array = new ArrayList<Integer>();
        LinkedList current = head;
        int i = 0;
        while (current != null && i < 10) {
            array.add(current.value);
            current = current.next;
            i++;
        }
        return array;
    }
}
