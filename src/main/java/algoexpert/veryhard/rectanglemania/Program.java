package algoexpert.veryhard.rectanglemania;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class Program {
    public static int rectangleMania(Point[] coords) {
        // Write your code here.
        Set coordSet = Arrays.stream(coords).collect(Collectors.toSet());
        int count = 0;
        for(int i = 0; i < coords.length; i++) {
            for(int j = i + 1; j < coords.length; j++) {
                int xdif = coords[j].x - coords[i].x;
                int ydif = coords[j].y - coords[i].y;
                if( (xdif  > 0 && ydif > 0) || (xdif  < 0 && ydif < 0)) {
                    //find two other coords
                    Point p1 = new Point(coords[j].x, coords[i].y);
                    Point p2 = new Point(coords[i].x, coords[j].y);
                    if(coordSet.contains(p1) && coordSet.contains(p2)) {
                        System.out.println("Rect:" + xdif + " " + ydif);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
