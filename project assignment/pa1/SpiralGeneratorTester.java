// Name: Yilang Liang   
// USC NetID: yilangli
// CS 455 PA1
// Spring 2024

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
   class SpiralGeneratorTester

   Tester class for SpiralGenerator.java. It tests if line segments generated for the spiral
   are correct.
 */
public class SpiralGeneratorTester {
   /**
      Main method of the class. It runs cases with specific parameters including starting point, 
      unit length, and numbers of line segments
    
    */
   public static void main(String[] args) {
      // Test case 1 starts from point (200, 300), drawing a spiral with unitLength of 5 and numbers of 10.
      System.out.println("Test case 1:");
      Point startPosition1 = new Point(200, 300);
      int unitLength1 = 5;
      int numbers1 = 10;
      testCase(startPosition1, unitLength1, numbers1);
      
      // Test case 2 starts from point (-200, -300), drawing a spiral with unitLength of 1 and numbers of 20.
      System.out.println("Test case 2:");
      Point startPosition2 = new Point(-200, -300);
      int unitLength2 = 1;
      int numbers2 = 20;
      testCase(startPosition2, unitLength2, numbers2);
      
      // Test case 3 starts from point (0, -100), drawing a spiral with unitLength of 10 and numbers of 5.
      System.out.println("Test case 3:");
      Point startPosition3 = new Point(0, -100);
      int unitLength3 = 10;
      int numbers3 = 5;
      testCase(startPosition3, unitLength3, numbers3);
   }
   
   /**
      This is a test case given a specific unitLength, numbers and startPosition. It will test
      drawing a spiral starting at startPosition with unitLength as unit length and numbers as 
      numbers of line segments. Ensures the line segments generated for the spiral is correct:
         1. Check horizontality or verticality for each line segment
         2. Check connection between each line segments pair (except last odd one) 
         3. Check if line segments pair are perpendicular to each other (except last odd one) 
      
      @param startPosition is the start coordinate of the spiral
      @param unitLength is the unit length of the spiral
      @param numbers is the numbers of the segments in spiral
    */
   public static void testCase(Point startPosition, int unitLength, int numbers) {
      SpiralGenerator sg = new SpiralGenerator(startPosition, unitLength);
      System.out.println("Making a spiral starting from " + startPosition);
      System.out.println("with a unit length of " + unitLength + ", and made up of " + numbers + " segments:");
      
      for (int i = 0; i < numbers; i++) {
         Line2D line1 = sg.nextSegment();
         System.out.println("Segment #" + (i + 1) + ": " + line1.getP1() + " " + line1.getP2());
         // Check whether the line is horizontal or vertical or neither for the first segment in the pair
         if (!isHorizontalOrVerticalTest(line1)) {
            System.out.println("FAILED: segment is not horizontal or vertical.  Skipping pair tests.");
            break;
         }
         // If it reaches the last index, loop ends and skip pair testing
         if (i == numbers - 1) {
            break;
         }
         // Update i to the next segment index
         i++;
         Line2D line2 = sg.nextSegment();
         System.out.println("Segment #" + (i + 1) + ": " + line2.getP1() + " " + line2.getP2());
         // Check whether the line is horizontal or vertical or neither for the second segment in pair
         if (!isHorizontalOrVerticalTest(line2)) {
            System.out.println("FAILED: segment is not horizontal or vertical.  Skipping pair tests.");
            break;
         }   
         // Check whether the two line segments are connected
         if (!isConnectedTest(line1, line2)) {
            System.out.println("FAILED: last two segments are not connected");
         }
         // Check whether the two line segments are perpendicular to each other
         if (!isPerpendicularTest(line1, line2)) {
            System.out.println("FAILED: last two segments are not perpendicular");
         }
      }
      System.out.println("All segments have been generated");
   }
   
   /**
      The method calculates the slope of a line segment.
      
      @param line the segment for calculation
      @return the slope of the line
    */
   public static double slope(Line2D line) {
      double slope = (line.getY1() - line.getY2()) / (line.getX1() - line.getX2());
      slope = Math.abs(slope);
      return slope;
   }
   
   /**
      The method checks whether the line segement is horizontal or vertical, if it is neither
      return false.
      
      @param line the segment for checking
      @return true or false
    */
   public static boolean isHorizontalOrVerticalTest(Line2D line) {
      double slope = slope(line);
      if (slope == 0 || slope == Double.POSITIVE_INFINITY) {
         return true;
      }
      return false;
   }
   
   /**
      The method checks whether the two line segements are connected. Return true if connected 
      and false otherwise.
      
      @param line1 the first segment for checking
      @param line2 the second segment for checking
      @return true or false
    */
   public static boolean isConnectedTest(Line2D line1, Line2D line2) {
      if (line1.getP2().equals(line2.getP1())) {
         return true;
      }
      return false;
   }
   
   /**
      The method checks whether the two line segements are perpendicular. Return true if 
      perpendicular and false otherwise. The method will be called after checking 
      isHorizontalOrVerticalTest for each segment and isConnectedTest for the pair.
      
      @param line1 the first segment for checking
      @param line2 the second segment for checking
      @return true or false
    */
   public static boolean isPerpendicularTest(Line2D line1, Line2D line2) {
      double slope1 = slope(line1);
      double slope2 = slope(line2);
      return slope1 != slope2;
   }
   
}
