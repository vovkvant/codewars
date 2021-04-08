package algoexpert.hard.shortenpath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    public static void main(String args[]) {
        String input = "/foo/..";
        System.out.println(shortenPath(input));
    }

    public static String shortenPath(String path) {
        // Write your code here;
        LinkedList<String> stack = new LinkedList<>();
        boolean absolute = false;
        if (path.startsWith("/")) {
            absolute = true;
        }
        String[] arr = path.split("/");

        List<String> stringList = Arrays.stream(arr)
                .filter(s -> !s.equals(".")).filter(s -> s.length() > 0).collect(Collectors.toList());

        for (String str : stringList) {
            if ("..".equals(str)) {
                if (absolute) {
                    if (stack.size() > 0) {
                        stack.pollLast();
                    }
                } else {
                    if (stack.size() > 0 && !stack.getLast().endsWith("..")) {
                        stack.pollLast();
                    } else {
                        stack.addLast((stack.size() == 0 ? "" : "/") + str);
                    }
                }
            } else {
                stack.addLast((stack.size() == 0 && !absolute ? "" : "/") + str);
            }
        }

        if (stack.size() == 0 && absolute) {
            return "/";
        }

        return stack.stream().collect(Collectors.joining());
    }

}
