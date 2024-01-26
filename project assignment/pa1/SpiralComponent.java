import javax.swing.*;
import java.awt.*;

/**
 class SpiralComponent

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
public class SpiralComponent extends JComponent {
    private int callCount;
    private int unitLength;
    private int numbers;
    public SpiralComponent(int unitLength, int numbers) {
        this.callCount = 0;
        this.unitLength = unitLength;
        this.numbers = numbers;
    }
    public void paintComponent(Graphics g) {
        // Cast g into Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        // print the component called out time
        callCount++;
        System.out.println("paintComponent is called " + callCount + " times!");
        // the startPosition changes based on the size of the component/frame
        Point startPosition = new Point(this.getWidth() / 2, this.getHeight() / 2);
        SpiralGenerator sg = new SpiralGenerator(startPosition, unitLength);
        for (int i = 0; i < numbers; i++) {
            g2d.draw(sg.nextSegment());
        }
    }
}
