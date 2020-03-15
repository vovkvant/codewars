package com.selfdev.philosoph;

public class PathTest {

    static boolean pathFinder(String maze) {
        // Your code here!!
        String lines[] = maze.split("\n");
        char[][] charLines = new char[lines.length][lines[0].length()];
        for(int i =0; i < lines.length; i++){
            charLines[i] = lines[i].toCharArray();
        }
        if(charLines.length == 1) return true;

        return pathFinder(charLines, 1, 0) || pathFinder(charLines, 0, 1);
    }

    static boolean pathFinder(char[][] charLines, int positionNum, int lineNum) {
        int maxLineNum = charLines.length;
        int maxPositionNum = charLines[0].length;

        if ((positionNum + 1) == maxPositionNum && (lineNum + 1) == maxLineNum) {
            return true;
        }

        if (positionNum < 0 || lineNum < 0) return false;
        if (positionNum == maxPositionNum || lineNum == maxLineNum) return false;

        String currentStr = String.valueOf(charLines[lineNum][positionNum]);
        if("d".equals(currentStr)) {
            return false;
        }
        charLines[lineNum][positionNum] = "d".charAt(0);
        if (!"W".equals(currentStr)) {
            return pathFinder(charLines, positionNum + 1, lineNum) ||
                    pathFinder(charLines, positionNum, lineNum + 1) ||
                    pathFinder(charLines, positionNum - 1, lineNum) ||
                    pathFinder(charLines, positionNum, lineNum - 1);
        } else return false;
    }


    public static void main(String args[]) {
        String a = ".W.\n" +
                ".W.\n" +
                "...";
        String b = ".W.\n" +
                ".W.\n" +
                "W..";

        String c = "......\n" +
                   "......\n" +
                   "......\n" +
                   "......\n" +
                   "......\n" +
                   "......";

        String d = "..W...\n" +
                   "..W...\n" +
                   "..W...\n" +
                   "..W...\n" +
                   "..W...\n" +
                   "..W...";

        String a1 = ".";


        System.out.println(pathFinder(a1));

        System.out.println(pathFinder(b));
        System.out.println(pathFinder(c));
        System.out.println(pathFinder(d));
    }

}
