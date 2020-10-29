package algoexpert.medium.linkedlist;

public class Program {
    static class DoublyLinkedList {
        public Program.Node head = null;
        public Program.Node tail = null;

        private void extractNode(Program.Node node){
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next == null) {
                    tail = node.prev;
                }
            }
            if (node.next != null) {
                node.next.prev = node.prev;
                if (node.prev == null) {
                    head = node.next;
                }
            }
        }

        private void insertBetween(Program.Node leftNode, Program.Node rightNode, Program.Node nodeToInsert) {
            if(leftNode!=null) {
                leftNode.next = nodeToInsert;
            } else {
                head = nodeToInsert;
            }
            nodeToInsert.prev = leftNode;

            if(rightNode!=null) {
                rightNode.prev = nodeToInsert;
            } else {
                tail = nodeToInsert;
            }
            nodeToInsert.next = rightNode;
        }

        public void setHead(Program.Node node) {
            // Write your code here.
            extractNode(node);
            insertBetween(null, head, node);
        }

        public void setTail(Program.Node node) {
            // Write your code here.
            extractNode(node);
            insertBetween(tail, null, node);
        }

        public void insertBefore(Program.Node node, Program.Node nodeToInsert) {
            extractNode(nodeToInsert);
            insertBetween(node.prev, node, nodeToInsert);
        }

        public void insertAfter(Program.Node node, Program.Node nodeToInsert) {
            extractNode(nodeToInsert);
            insertBetween(node, node.next, nodeToInsert);
        }

        public void insertAtPosition(int position, Program.Node nodeToInsert) {
            Program.Node curr = head;
            if (curr == null) {
                head = nodeToInsert;
                tail = nodeToInsert;
                return;
            }
            for (int i = 1; i < position; i++) {
                if (curr != null) {
                    curr = curr.next;
                }
            }
            extractNode(nodeToInsert);
            insertBetween(curr.prev, curr, nodeToInsert);
        }

        public void removeNodesWithValue(int value) {
            // Write your code here.
            Program.Node curr = head;
            while (curr != null) {
                if (curr.value == value) {
                    extractNode(curr);
                    if(curr.next==null && curr.prev==null) {
                        head = null;
                        tail = null;
                    }
                }
                curr = curr.next;
            }
        }

        public void remove(Program.Node node) {
            // Write your code here.
            extractNode(node);
            if(node.next==null && node.prev==null) {
                head = null;
                tail = null;
            }
        }

        public boolean containsNodeWithValue(int value) {
            // Write your code here.
            Program.Node curr = head;
            while (curr.next != null) {
                if (curr.value == value) return true;
                curr = curr.next;
            }
            return false;
        }

    }

    // Do not edit the class below.
    static class Node {
        public int value;
        public Program.Node prev;
        public Program.Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
