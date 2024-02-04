// Name: Yilang Liang   
// USC NetID: yilangli
// CS 455 PA1
// Spring 2024


import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
   Class SpiralGenerator
   
   It generates the spiral using Java display coordinate system, and the spiral 
   will move outward in counter-clockwise direction, following directions of right
   up, left and down relative to the screen.
   
   The nextSegment() method generates one segment at a time based on the current
   number of segements, unit length, and the current position of the segment
   
   The way to use the class is to create a SpiralGenerator instance with specific 
   startPosition and unitLength and call nextSegment() method to generate each line 
   segment of the spiral
 */
public class SpiralGenerator {
   /**
      The starting point of the first segment in the spiral
    */
   private Point startPosition;
   /**
      The current starting point for next line segement
    */
   private Point2D curPosition;
   /**
      The unit length for the segements in pixels, must be greater than 0
    */
   private final int unitLength;
   /**
      The counter that counts the number of line segements generated
    */
   private int counter;
   /**
      Directions represents the possible directions of the next line segment
      Right, up, left and down are the specific directions of the line extension.
      The spiral is generated following a sequence of right, up, left and down visually.
    */
   private enum Directions {
      RIGHT,
      UP,
      LEFT,
      DOWN
   }
   
   /**
      Constructor of the class which initializes an instance of the SpiralGenerator class 
      starting at startPositio. It will also initialize the unit length of the spiral
      Both values are in the Java graphics coordinate system. This constructor also sets 
      the curPosition to the startPosition and sets the segments counter to 0.
      
      @param startPosition starting point of the first segment in the spiral, and it is 
             also used as the initial curPosition for generating the line segment
      @param unitLength in pixels, must be greater than 0
    */
   public SpiralGenerator(Point startPosition, int unitLength) {
      this.startPosition = startPosition;
      this.curPosition = new Point2D.Double(this.startPosition.getX(), this.startPosition.getY());
      this.unitLength = unitLength;
      this.counter = 0;
   }

   /**
      The method generates the next line segment of the spiral from the curPosition. 
      The direction and length of the line depends on the number of line segments that 
      are already generated and the unitLength for the segments. The spiral extends 
      outward from the center with a direction of right, up, left, and down(visually). 
      The counter and the curPosition are updated after each segment is created.
      
      @return a Line2D object that represents the next new segment of the spiral generated 
              from curPosition to "to" position
    */
   public Line2D nextSegment() {
      double x = curPosition.getX();
      double y = curPosition.getY();
      // Get the direction of the next segment visually according to the coordinate
      Directions direction = Directions.values()[counter % 4];
      // n checks the unit length of the current segment
      int n = counter / 2 + 1;
      switch (direction) {
         case RIGHT:
            x += unitLength * n;
            break;
         case UP:
            y -= unitLength * n;
            break;
         case LEFT:
            x -= unitLength * n;
            break;
         case DOWN:
            y += unitLength * n;
            break;
      }
      counter += 1;
      // The line segment is generated starting from the curPosition to the "to" position
      Point2D.Double to = new Point2D.Double(x, y);
      Line2D.Double segment = new Line2D.Double(curPosition, to);
      curPosition = to;
      return segment;
   }
}
