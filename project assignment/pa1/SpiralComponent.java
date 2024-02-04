// Name: Yilang Liang   
// USC NetID: yilangli
// CS 455 PA1
// Spring 2024


import javax.swing.*;
import java.awt.*;

/**
   Class SpiralComponent

   It is a component that extends JComponent to draw a spiral. It uses SpiralGenerator
   class and its method to calculate and create each segment of the spiral based on 
   specific unit length and numbers of segments. The spiral will start from the center 
   of the JFrame and will redraw to adjust the start position when the frame is resized. 
   The drawing logic is within the paintComponent method.
   
   The way to use the class is to create a SpiralComponent instance with specific unitLength 
   and numbers of segments and add the component in JFrame in the SpiralViewer class
 */
public class SpiralComponent extends JComponent {
   /**
      The unit length for the segments in the spiral
    */
   private int unitLength;
   /**
      The numbers of the segments in the spiral
    */
   private int numbers;
   
   /**
      Constructor of the class which intialize an instance of the SpiralComponent class. 
      This constructor sets the unit length of the line segments in the spiral and 
      the number of line segments in spiral that needs to be generated
      
      @param unitLength in pixels, must be greater than 0
      @param numbers represents the number of segments that needs to be in the spiral,
             must be greater than 0
    */
   public SpiralComponent(int unitLength, int numbers) {
      this.unitLength = unitLength;
      this.numbers = numbers;
   }
   /**
      The method overrides paintComponent method in JComponent to draw a spiral on this component.
      This method is called when the component needs to be rendered or rerendered (JFrame changes)
      and it will generate the spiral using SpiralGenerator class to draw and create the line 
      segments iteratively based on specific startPosition(the center of the frame), unitLength 
      and numbers of segements.
      
      @param g is a graphics object that stores the graphics state for the drawing operations
             It is used to draw the line segments of the spiral after casting to Graphics2D
    */
   public void paintComponent(Graphics g) {
      // Cast g into Graphics2D
      Graphics2D g2d = (Graphics2D) g;
      // The startPosition changes based on the JFrame window size and should be at the center
      Point startPosition = new Point(this.getWidth() / 2, this.getHeight() / 2);
      SpiralGenerator sg = new SpiralGenerator(startPosition, unitLength);
      for (int i = 0; i < numbers; i++) {
         g2d.draw(sg.nextSegment());
      }
   }
}

