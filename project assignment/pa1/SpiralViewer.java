import javax.swing.JFrame;
import java.util.Scanner;

/**
 class SpiralGenerator

 Contains the main method. It prompts the user for the initial unit length of the segment
 and number of segments. Then it will create JFrame containing the SpiralComponent and show
 the generated spiral in the frame

 Length of initial line is unitLength, the length has to be a positive integer
 Numbers of the segments is the total line segments generated to compose the spiral
 */
public class SpiralViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Scanner scan = new Scanner(System.in);
        int unitLength = 0;
        int numbers = 0;
        // While loop for prompting the initialization of unitLength and numbers
        // Check for valid input and re-prompt if input is invalid
        while (true) {
            System.out.println("Please enter the initial length of the spiral:");
            if (scan.hasNextInt()) {
                unitLength = scan.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid positive number");
                scan.next();
            }
        }
        while (true) {
            System.out.println("Please enter the numbers of segment in the spiral:");
            if (scan.hasNextInt()) {
                numbers = scan.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid positive number");
                scan.next();
            }
        }
        SpiralComponent sc = new SpiralComponent(unitLength, numbers);
        // The initial size of the frame is set to be 800 pixels in width and 500 pixels in height
        frame.setSize(800, 500);
        frame.setTitle("Spiral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(sc);
        // The component will be created when the frame is set to be visible
        frame.setVisible(true);
    }
}
