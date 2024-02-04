// Name: Yilang Liang   
// USC NetID: yilangli
// CS 455 PA1
// Spring 2024


import javax.swing.JFrame;
import java.util.Scanner;

/**
   Class SpiralViewer

   It contains the main method. By running the main method, it prompts the user for the 
   initial unit length of the segment and number of segments. Then, it will create 
   JFrame containing the SpiralComponent and show the generated spiral in the frame.
 */
public class SpiralViewer {
   /**
      The width of the JFrame
   */
   private static final int FRAME_WIDTH = 800;
   /**
      The height of the JFrame
   */
   private static final int FRAME_HEIGHT = 500;
   
   /**
      Main method of the class. It will prompt for user input to enter unit length
      and number of segments. Creating a JFrame with width FRAMEWIDTH and height
      FRAMEHEIGHT and showing the spiral in the frame. Jframe could be resized
      and the spiral will be redrawn at the center of the frame. The operation 
      exit when the frame window is closed
    */
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      // Prompt the user to enter value for unitLength and numbers of segments
      String question1 = "Enter length of initial segment:";
      int unitLength = promptForInput(question1);
      String question2 = "Please enter the numbers of segment in the spiral:";
      int numbers = promptForInput(question2);
      // Create SpiralComponent instance with unitLength and numbers
      SpiralComponent sc = new SpiralComponent(unitLength, numbers);
      // The initial size of the frame is set to be FRAME_WIDTH(800) pixels in width and FRAME_HEIGHT(500) pixels in height
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("Spiral");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(sc);
      // The component will be created when the frame is set to be visible
      frame.setVisible(true);
   }
   
   /**
      The method prompts the user to enter a valid positive integer as the input
      It will keep asking for a valid input until the user enter a valid one. 
      It is invalid when the user enters "12 sd 12" or "12 ", the method will not 
      only check the first integer but also the entire input line.
      
      @param question is the question for the input either unitLength/numbers
      @return value which is the user valid input
    */
   private static int promptForInput(String question) {
      Scanner scanner = new Scanner(System.in);
      String input = "";
      int value = 0;
      while (true) {
         System.out.println(question);
         input = scanner.nextLine();
         int length = input.split("\\s+").length;
         // Check if the user enter multiple things separated by " "
         if (length != 1) {
            System.out.println("Please enter a valid positive integer");
            continue;
         }
         // Check if the only input is an integer
         try {
            value = Integer.parseInt(input.trim());
         } catch (NumberFormatException error) {
            System.out.println("Please enter a valid positive integer");
            continue;
         }
         // Check if the integer is greater than 0
         if (value <= 0) {
            System.out.println("Please enter a valid positive integer");
            continue;
         } 
         break;
      }
      return value;
   }
}
