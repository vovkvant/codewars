package com.selfdev.simplifying;

import java.util.*;

/**
 * Created by vovkv on 9/4/2019.
 */
public class Simplifying {


    final static Set<Character> signs = new HashSet(Arrays.asList(new Character[]{'-', '+', '(', ')', '='}));

    public static String simplify(String[] examples, String formula) {
        char chFormula[] = formula.toCharArray();

        Map<String, String> dictionary = new HashMap<>();
        Arrays.stream(examples).forEach(example -> {
            example = example.replaceAll(" ", "");
            String[] result = example.split("=");
            if (result.length >= 2) {
                dictionary.put(result[1], result[0]);
            }
        });
        System.out.println(dictionary);


        System.out.println(eval(formula, 1));

        return null;
    }

    static Map<String, Integer> eval(String formula, int extKoef) {
        Map<String, Integer> table = new HashMap<>();
        formula = formula.replaceAll(" ", "");
        int cursor = 0;
        char currentCh;
        int koef = 1;
        int sign = 1;
        String var = null;
        while (cursor < formula.length()) {
            currentCh = formula.charAt(cursor);
            if (new Character('-').equals(currentCh)) {
                sign = -1;
            }
            if (new Character('(').equals(currentCh)) {
                cursor++;
                currentCh = formula.charAt(cursor);
                StringBuffer subFormula = new StringBuffer();
                while (!new Character(')').equals(currentCh)) {
                    subFormula.append(currentCh);
                    cursor++;
                    currentCh = formula.charAt(cursor);
                }
                Map<String, Integer> subTable = eval(subFormula.toString(), koef * sign);
                subTable.forEach((k, v) -> {
                    table.put(k, table.getOrDefault(k, 0) + v);
                });
            }

            if (Character.isDigit(currentCh)) {
                StringBuffer sb = new StringBuffer();
                sb.append(currentCh);
                cursor++;
                currentCh = formula.charAt(cursor);
                while (Character.isDigit(currentCh)) {
                    sb.append(currentCh);
                    cursor++;
                    currentCh = formula.charAt(cursor);
                }
                cursor--;
                koef = Integer.valueOf(sb.toString());
            }

            if (Character.isLetter(currentCh)) {
                var = new String(new char[]{currentCh});
            }
            if (var != null) {
                table.put(var, table.getOrDefault(var, 0) + koef * sign);
                koef = 1;
                var = null;
                sign = 1;
            }
            cursor++;
        }
        if (extKoef != 1) {
            table.replaceAll((k, v) -> v * extKoef);
        }

        return table;
    }

    public static void main(String args[]) {
        simplify(new String[]{"a + a = b", "b - d = c", "a + b = d"}, "5(c - 3b)-5a + 2b");
    }
}
