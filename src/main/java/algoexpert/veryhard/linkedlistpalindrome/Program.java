package algoexpert.veryhard.linkedlistpalindrome;

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

    public static void main(String args[]) {
        LinkedList head = new LinkedList(0);
        /*
        head.next = new LinkedList(1);
        head.next.next = new LinkedList(2);
        head.next.next.next = new LinkedList(2);
        head.next.next.next.next = new LinkedList(1);
        head.next.next.next.next.next = new LinkedList(0);
        */


        Program p =new Program();
        System.out.print(p.linkedListPalindrome(head));
    }

    public boolean linkedListPalindrome(LinkedList head) {
        // Write your code here.
        LinkedList slowNode = head;
        LinkedList fastNode = head;
        while(fastNode!=null && fastNode.next!=null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        LinkedList middleHead = slowNode;

        //revert from middle
        LinkedList prev = middleHead;
        LinkedList current = middleHead.next;
        LinkedList next = null;
        if(current!=null) {
            next = current.next;
        }
        prev.next = null;
        while(current!=null) {
            current.next = prev;
            prev = current;
            current = next;
            if(next!=null) {
                next = next.next;
            }
        }

        LinkedList tail = prev;
        while(tail!=null && head!=null) {
            if(tail.value != head.value) {
                return false;
            }
            tail = tail.next;
            head = head.next;
        }

        return true;
    }
}
