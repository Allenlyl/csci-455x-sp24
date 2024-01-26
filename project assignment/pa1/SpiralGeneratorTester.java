import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 class SpiralGeneratorTester

 Tester class for SpiralGenerator.java
 Ensures the line segments generated for the spiral is correct:
 1. Check connection between adjacent line segments
 2. Check horizontality or verticality for line segments
 3. Check if adjacent line segments are perpendicular to each other
 4. Check if line segments have correct lengths and coordinate

 Run SpiralGenerator with unitlength and number of segments declared, iterate
 to generate each line and perform tests above
 */
public class SpiralGeneratorTester {
    public static void main(String[] args) {
        System.out.println("TEST 1:");
        Point p1 = new Point(0, 0);
        SpiralGenerator sg = new SpiralGenerator(p1, 1);
        System.out.println("Making a spiral starting from " + p1);
        Line2D line = sg.nextSegment();
        System.out.println("Expected:");
        System.out.println(line.getP1());
        System.out.println(line.getP2() + "\n");

        Line2D line2 = sg.nextSegment();
        System.out.println("Expected: p1 = [1.0, 0.0], p2 = [1.0, 1.0]");
        System.out.println("p1 is " + line2.getP1());
        System.out.println("p2 is " + line2.getP2() + "\n");

        Line2D line3 = sg.nextSegment();
        System.out.println("Expected: p1 = [1.0, 1.0], p2 = [-1.0, 1.0]");
        System.out.println("p1 is " + line3.getP1());
        System.out.println("p2 is " + line3.getP2() + "\n");

        System.out.println("TEST 2:");
        Point p2 = new Point(0, 0);
        int l1 = 1;
        int n1 = 8;
        SpiralGenerator sg2 = new SpiralGenerator(p1, l1);
        System.out.println("Making a spiral starting from " + p1);
        System.out.println("with a unit length of " + l1 + " and made up of " + n1 + " segments");
        for (int i = 0; i < n1; i++) {
            Line2D tempLine1 = sg2.nextSegment();
            Point2D tempP1 = tempLine1.getP1();
            Point2D tempP2 = tempLine1.getP2();
            System.out.println("Segment #" + (i + 1) + ": " + tempLine1.getP1() + tempLine1.getP2());

            // Check whether the line is horizontal or vertical or neither for the first segment in pair
            if (tempP1.getX() != tempP2.getX() && tempP1.getY() != tempP2.getY()) {
                System.out.println("FAILED: segment #" + (i + 1) + " is not horizontal or vertical. Skip pair test.");
                break;
            }
            // If it reaches the last index, loop ends and skip pairing
            if (i == n1 - 1) {
                break;
            }
            i++;
            Line2D tempLine2 = sg2.nextSegment();
            Point2D tempP3 = tempLine2.getP1();
            Point2D tempP4 = tempLine2.getP2();
            System.out.println("Segment #" + (i + 1) + ": " + tempLine2.getP1() + " " + tempLine2.getP2());
            // Check whether the line is horizontal or vertical or neither for the second segment in pair
            if (tempP1.getX() != tempP2.getX() && tempP1.getY() != tempP2.getY()) {
                System.out.println("FAILED: segment #" + (i + 1) + " is not horizontal or vertical. Skip pair test.");
                break;
            }
            // Check whether the two line segments are connected
            if (!tempP2.equals(tempP3)) {
                System.out.println("FAILED: segment #" + (i) + " and segment #" + (i + 1) + " are not connected.");
            }
            // Check whether the two line segments are perpendicular to each other
            if (tempP2.getX() != tempP3.getX() && tempP2.getX() != tempP4.getX()) {
                System.out.println("FAILED: segment #" + (i) + " and segment #" + (i + 1) + " are not perpendicular.");
            }
        }
        System.out.println("All segments have been generated");
    }
}
