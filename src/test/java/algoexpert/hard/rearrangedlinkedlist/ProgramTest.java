package algoexpert.hard.rearrangedlinkedlist;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProgramTest {

    @Test
    public void test1() {
        Program.LinkedList head = new Program.LinkedList(3);
        head.next = new Program.LinkedList(0);
        head.next.next = new Program.LinkedList(5);
        head.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next = new Program.LinkedList(1);
        head.next.next.next.next.next = new Program.LinkedList(4);
        Program.LinkedList result = Program.rearrangeLinkedList(head, 4);
        List array = Program.linkedListToArray(result);

        List<Integer> expected = Arrays.asList(new Integer[] {3, 0, 2, 1, 4, 5});
        assertTrue(expected.equals(array));
        System.out.println(array);
    }

    @Test
    public void test2() {
        Program.LinkedList head = new Program.LinkedList(3);
        head.next = new Program.LinkedList(0);
        head.next.next = new Program.LinkedList(5);
        head.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next = new Program.LinkedList(1);
        head.next.next.next.next.next = new Program.LinkedList(4);
        Program.LinkedList result = Program.rearrangeLinkedList(head, 3);
        List array = Program.linkedListToArray(result);

        List<Integer> expected = Arrays.asList(new Integer[] {0, 2, 1, 3, 5, 4});
        assertTrue(expected.equals(array));
        System.out.println(array);
    }

    @Test
    public void test3() {
        //input 4 0 6 2 1 5
        //expected 0 2 1 4 6 5
        Program.LinkedList head = new Program.LinkedList(4);
        head.next = new Program.LinkedList(0);
        head.next.next = new Program.LinkedList(6);
        head.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next = new Program.LinkedList(1);
        head.next.next.next.next.next = new Program.LinkedList(5);
        Program.LinkedList result = Program.rearrangeLinkedList(head, 3);
        List array = Program.linkedListToArray(result);
        System.out.println(array);
        List<Integer> expected = Arrays.asList(new Integer[] {0, 2, 1, 4, 6, 5});
        assertTrue(expected.equals(array));

    }

    @Test
    public void test4() {
        //input 4 0 6 2 1 -5
        //expected -5, 0, 2, 1, 4, 6
        Program.LinkedList head = new Program.LinkedList(4);
        head.next = new Program.LinkedList(0);
        head.next.next = new Program.LinkedList(6);
        head.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next = new Program.LinkedList(1);
        head.next.next.next.next.next = new Program.LinkedList(-5);
        Program.LinkedList result = Program.rearrangeLinkedList(head, -5);
        List array = Program.linkedListToArray(result);
        System.out.println(array);
        List<Integer> expected = Arrays.asList(new Integer[] {-5, 4, 0, 6, 2, 1});
        assertTrue(expected.equals(array));
    }


    @Test
    public void test5() {
        //input 4 0 6 2 2 2 1 5
        // 0 1 2 2 2 4 6 5
        Program.LinkedList head = new Program.LinkedList(4);
        head.next = new Program.LinkedList(0);
        head.next.next = new Program.LinkedList(6);
        head.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next.next = new Program.LinkedList(2);
        head.next.next.next.next.next.next = new Program.LinkedList(1);
        head.next.next.next.next.next.next.next = new Program.LinkedList(5);
        Program.LinkedList result = Program.rearrangeLinkedList(head, 2);
        List array = Program.linkedListToArray(result);
        System.out.println(array);
        List<Integer> expected = Arrays.asList(new Integer[] {0, 1, 2, 2, 2, 4, 6, 5});
        assertTrue(expected.equals(array));
    }
}
