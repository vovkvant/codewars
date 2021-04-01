package algoexpert.veryhard.minimumarearectangle;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        int[][] input =
                new int[][] {
                        {1, 5},
                        {5, 1},
                        {4, 2},
                        {2, 4},
                        {2, 2},
                        {1, 2},
                        {4, 5},
                        {2, 5},
                        {-1, -2}
                };
        Program p = new Program();
        System.out.println(p.minimumAreaRectangle(input));

    }



    public int minimumAreaRectangle(int[][] points) {
        // Write your code here.
        List<Rectangle> possibleRectList = new ArrayList<>();
        for(int i = 0; i < points.length; i++) {
            int [] curPoint = points[i];
            for(int j = i + 1; j < points.length; j++) {
                if(curPoint[0] != points[j][0] && curPoint[1] != points[j][1]) {
                    Rectangle rect = new Rectangle(curPoint[0], curPoint[1], points[j][0], points[j][1]);
                    possibleRectList.add(rect);
                }
            }
        }

        for(int i = 0; i < possibleRectList.size(); i++) {
            for(int j = 0; j < points.length; j++) {
                possibleRectList.get(i).addPointAndProcess(points[j][0], points[j][1]);
            }
        }

        int result = possibleRectList.stream().filter(r -> r.isRectComplete())
                .map(r -> r.S)
                .min(Integer::compareTo).orElse(0);
        return result;
    }

    static class Rectangle {
        final int x1, y1, x2, y2;
        final Integer S;
        boolean isPointOneAvailable = false;
        boolean isPointTwoAvailable = false;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            S = Math.abs((y2 - y1)*(x2 - x1));
        }

        public void addPointAndProcess(int x, int y){
            if(x1 == x && y2 == y) isPointOneAvailable = true;
            if(x2 == x && y1 == y) isPointTwoAvailable = true;
        }

        public boolean isRectComplete() {
            return isPointOneAvailable && isPointTwoAvailable;
        }
    }
}
