package algoexpert.medium.sumlinkedlist;

public class Program {
    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        // Write your code here.
        int digitTransfer = 0;
        LinkedList head = null;
        LinkedList curr = null;
        while (linkedListOne != null || linkedListTwo != null) {
            int valueOne = linkedListOne == null ? 0 : linkedListOne.value;
            int valueTwo = linkedListTwo == null ? 0 : linkedListTwo.value;
            int result = valueOne + valueTwo + digitTransfer;
            if (result >= 10) {
                result = result - 10;
                digitTransfer = 1;
            } else {
                digitTransfer = 0;
            }
            if (head == null) {
                head = new LinkedList(result);
                curr = head;
            } else {
                curr.next = new LinkedList(result);
                curr = curr.next;
            }
            linkedListOne = linkedListOne == null ? null : linkedListOne.next;
            linkedListTwo = linkedListTwo == null ? null : linkedListTwo.next;
        }
        if (digitTransfer == 1) {
            curr.next = new LinkedList(digitTransfer);
        }

        return head;
    }
}
