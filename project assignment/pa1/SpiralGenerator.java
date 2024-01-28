// Name:
// USC NetID:
// CS 455 PA1
// Spring 2024


import javax.sound.sampled.Line;
import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
   class SpiralGenerator
   
   Generates a "rectangular" spiral, using Java display coordinate system, moving 
   outward from the center of the spiral in a counter-clockwise direction.
   That is, it will head rightward on screen, then, upward, then left, then down, etc.
  
   Length of initial line is unitLength.
   Padding between "layers" of the spiral is also unitLength.
   
   NOTE: we have provided the public interface for this class.  Do not change
   the public interface.  You can add private instance variables, constants, 
   and private methods to the class.  You will also be completing the 
   implementation of the methods given. 
   
 */
public class SpiralGenerator {

   private Point startPosition;
   private Point2D curPosition;
   private final int unitLength;
   private int counter;

   /**
    * Creates a SpiralGenerator starting at startPosition, with length of first segnment and
    * distance between "layers" both given as unitLength.  Both values are assuming the Java
    * graphics coordinate system.
    *
    * @param startPosition starting point of the first segment in the spiral
    * @param unitLength in pixels, must be > 0
    * @param curPosition in Point2D, the current starting point for next line segement
    * @param counter in int, counts the number of line segements generated
    */

   public SpiralGenerator(Point startPosition, int unitLength) {
      this.startPosition = startPosition;
      this.curPosition = new Point2D.Double(this.startPosition.getX(), this.startPosition.getY());
      this.unitLength = unitLength;
      this.counter = 0;
   }

   /**
      Return the coordinates of the next line segment in the spiral.
    */
   public Line2D nextSegment() {
      double x = curPosition.getX();
      double y = curPosition.getY();
      int s = counter % 4;
      int n = counter / 2 + 1;
      switch (s) {
         case 0:
            x += unitLength * n;
            break;
         case 1:
            y -= unitLength * n;
            break;
         case 2:
            x -= unitLength * n;
            break;
         case 3:
            y += unitLength * n;
            break;
      }
      counter += 1;
      // The line is generated from "curPosition" to "to"
      Point2D.Double to = new Point2D.Double(x, y);
      Line2D.Double segment = new Line2D.Double(curPosition, to);
      curPosition = to;
      return segment;
   }

}
