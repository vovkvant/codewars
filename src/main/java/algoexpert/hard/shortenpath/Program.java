package algoexpert.hard.shortenpath;

import java.util.LinkedList;

public class Program {

    public static void main(String args[]) {
        String input = "/foo/..";
        System.out.println(shortenPath(input));
    }

    public static String shortenPath(String path) {
        // Write your code here;
        LinkedList<String> stack = new LinkedList<>();
        int i = 0;
        boolean absolute = false;
        if (path.startsWith("/")) {
            absolute = true;
        }
        String[] str = path.split("/");
        

        for (; i < str.length; i++) {
            if ("..".equals(str[i])) {
                if (absolute) {
                    if (stack.size() > 0) {
                        stack.pollLast();
                    }
                } else {
                    if (stack.size() > 0 && !stack.getLast().endsWith("..")) {
                        stack.pollLast();
                    } else {
                        stack.addLast((stack.size() == 0 && !absolute ? "" : "/") + str[i]);
                    }
                }
            } else if (str[i].length() > 0 && !".".equals(str[i])) {
                stack.addLast((stack.size() == 0 && !absolute ? "" : "/") + str[i]);
            }
        }

        if(stack.size() == 0 && absolute) {
            return "/";
        }

        StringBuffer result = new StringBuffer();
        for (String el : stack) {
            result.append(el);
        }

        return result.toString();
    }

}
