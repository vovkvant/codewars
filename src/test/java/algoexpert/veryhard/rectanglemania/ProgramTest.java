package algoexpert.veryhard.rectanglemania;

import org.junit.Test;
import org.junit.Assert;

public class ProgramTest {

    @Test
    public void TestCase1() {
        Program.Point[] coords =
                new Program.Point[] {
                        new Program.Point(0, 0),
                        new Program.Point(0, 1),
                        new Program.Point(1, 1),
                        new Program.Point(1, 0),
                        new Program.Point(2, 1),
                        new Program.Point(2, 0),
                        new Program.Point(3, 1),
                        new Program.Point(3, 0)
                };
        System.out.println(Program.rectangleMania(coords));
        Assert.assertTrue(Program.rectangleMania(coords) == 6);
    }

    @Test
    public void TestCase2() {
        Program.Point[] coords =
                new Program.Point[] {
                        new Program.Point(0, 0),
                        new Program.Point(0, 1),
                        new Program.Point(1, 1),
                        new Program.Point(1, 0),
                        new Program.Point(2, 1),
                        new Program.Point(2, 0),
                        new Program.Point(3, 1),
                        new Program.Point(3, 0),
                        new Program.Point(1, 3),
                        new Program.Point(3, 3)
                };
        System.out.println(Program.rectangleMania(coords));
        Assert.assertTrue(Program.rectangleMania(coords) == 8);
    }
}
