package com.selfdev.binomialexpansion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://www.codewars.com/kata/540d0fdd3b6532e5c3000b5b
//https://en.wikipedia.org/wiki/Binomial_theorem
public class KataSolution {

    public static String expand(String expr) {
        System.out.println("Input: " + expr);
        ParseResult parseResult = parse(expr);

        int k = parseResult.n;
        StringBuffer output = new StringBuffer("");
        if(parseResult.b == 0) {
            int a = (int) (Math.pow(parseResult.a, parseResult.n));
            if(a!=1) {
                output.append(a);
            }
            output.append(parseResult.var).append("^").append(parseResult.n);
            return output.toString();
        }

        List<Integer> binoKoefList = calculateBinoKoef(parseResult.n);
        for (int i = 0; i <= parseResult.n; i++) {
            int koef = binoKoefList.get(i); //calculate this index based on Paskal triangle
            long a = (long) (Math.pow(parseResult.a, k) * Math.pow(parseResult.b, i)*koef);
            System.out.println("k=" + k + " i=" + i + " koef=" + koef + " a=" + a);

            if (a > 0 && output.length() != 0) {
                output.append("+");
            }
            if (a < 0) {
                output.append("-");
            }
            if (Math.abs(a) != 1 || k == 0) {
                output.append(Math.abs(a));
            }
            if (k > 0) {
                output.append(parseResult.var);
                if (k > 1) {
                    output.append("^").append(k);
                }
            }
            k--;
        }
        System.out.println("Output: " + output.toString());
        return output.toString();
    }

    private static List<Integer> calculateBinoKoef(int n) {
        if (n == 0) return Collections.singletonList(1);
        List<Integer> prevResult = calculateBinoKoef(n - 1);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                result.add(prevResult.get(0));
                continue;
            }
            if (i == n) {
                result.add(prevResult.get(prevResult.size() - 1));
                continue;
            }
            result.add(prevResult.get(i - 1) + prevResult.get(i));
        }
        return result;
    }

    private static ParseResult parse(String expr1) {
        int cursor = 0;
        char currentCh;
        int sign = 1;
        ParseResult parseResult = new ParseResult();

        String[] str = expr1.split("\\^");
        parseResult.n = Integer.parseInt(str[1]);
        String expr = str[0];

        while (cursor < expr.length()) {
            currentCh = expr.charAt(cursor);
            if (new Character('(').equals(currentCh) || new Character(')').equals(currentCh)) {
                cursor++;
                continue;
            }

            if (new Character('-').equals(currentCh)) {
                sign = -1;
                cursor++;
                continue;
            }

            if (Character.isDigit(currentCh)) {
                StringBuffer sb = new StringBuffer();
                sb.append(currentCh);
                cursor++;
                currentCh = expr.charAt(cursor);
                while (Character.isDigit(currentCh)) {
                    sb.append(currentCh);
                    cursor++;
                    currentCh = expr.charAt(cursor);
                }
                cursor--;
                int koef = sign * Integer.valueOf(sb.toString());
                if (parseResult.a == null) {
                    parseResult.a = koef;
                } else {
                    parseResult.b = koef;
                }
                sign = 1;
            }

            if (Character.isLetter(currentCh)) {
                StringBuffer sb = new StringBuffer();
                sb.append(currentCh);
                parseResult.var = sb.toString();
                if (parseResult.a == null) {
                    parseResult.a = 1 * sign;
                    sign = 1;
                }
            }
            cursor++;
        }
        return parseResult;
    }

    private static class ParseResult {
        Integer a = null;
        String var = null;
        Integer b = null;
        Integer n = null;

        @Override
        public String toString() {
            return "Result{" +
                    "a=" + a +
                    ", var='" + var + '\'' +
                    ", b=" + b +
                    '}';
        }
    }
}
