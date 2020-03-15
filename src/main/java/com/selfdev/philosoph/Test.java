package com.selfdev.philosoph;

import java.util.*;

/**
 * Created by vovkvant on 12.07.2017.
 */
public class Test implements Runnable {

    LinkedList<String> parseString(String input) {
        LinkedList<String> lexems = new LinkedList<String>();
        Character[] nums = new Character[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        Set<Character> numbers = new HashSet(Arrays.asList(nums));

        String cipher = "";
        for (Character ch : input.toCharArray()) {
            if (numbers.contains(ch)) {
                cipher = cipher + ch.toString();
                continue;
            } else {
                if (!cipher.isEmpty()) {
                    lexems.add(cipher);
                    cipher = "";
                }
                lexems.add(ch.toString());
            }
        }
        if (!cipher.isEmpty()) {
            lexems.add(cipher);
        }
        return lexems;
    }


    int eval(ListIterator<String> li) {
        String prev = li.next();
        int intPrev = 0;
        if(prev.equals("(")) {
            prev = li.next();
        }
        intPrev = Integer.parseInt(prev);

        if(li.hasNext()){
            String curLex = li.next();
            if(curLex.equals(")")) return Integer.parseInt(prev);
            if (curLex.equals("-"))
                return intPrev - eval(li);
            else if (curLex.equals("+"))
                return intPrev + eval(li);
            else if (curLex.equals("*"))
                return intPrev * eval(li);
        }
        return Integer.parseInt(prev);
    }

    public static void main(String[] args) {
        Test t = new Test();
        LinkedList<String> lex = t.parseString("10+2*2+10");
        for (String l : lex) System.out.println(l);
        System.out.println("====");
        System.out.println(t.eval(lex.listIterator()));
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            System.out.println("Thread counter" + i);
    }


    public void test() {
        int arr[] = new int[]{1, 20, 3, 40, 5, 50, 23};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > arr[i])
                    swap(i, j, arr);
            }
        }

        for (int h : arr) {
            System.out.println(h);
        }

        Thread th = new Thread(new Test());
        th.start();

        Map<Key, String> map = new HashMap<Key, String>();
        Key k = new Key();
        k.i = 1;
        map.put(k, "hello");
        k.i = 2;
        Key k2 = new Key();
        k2.i = 1;
        System.out.println(map.get(k2));

        k2.i = 2;
        System.out.println(map.get(k2));
    }

    static class Key {
        public int i;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            return i == key.i;

        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
