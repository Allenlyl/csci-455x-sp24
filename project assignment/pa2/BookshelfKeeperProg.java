// Name: Yilang Liang
// USC NetID: yilangli
// CSCI455 PA2
// Spring 2024


import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * BookshelfKeeperProg contains the main method that allows the user to create a bookshelf and perform
 * a series of pickPos and putHeight operations on the bookshelf in an interactive mode with user commands
 * called pick and put. It can also be run in a batch mode by using input and output redirection.
 * Invalid command will be warned and the program will exit after warning.
 */
public class BookshelfKeeperProg {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      // Prompt for initializing a bookshelfKeeper object.
      BookshelfKeeper bookshelfKeeper = bookshelfKeeperInitialization(scanner);
      // Prompt for operations on the bookshelf.
      promptForInput(scanner, bookshelfKeeper);
   }

   /**
    * Prompt for user input for the initial arrangement of the books on the bookshelf and create a
    * BookshelfKeeper object if the input is valid and heights of the books are in non-decreasing order
    * An empty bookshelfKeeper will also be created if the input is empty. If the user enter invalid
    * input, the user will be warned and the program will exit.
    *
    * Return the initialized bookshelfKeeper
    */
   private static BookshelfKeeper bookshelfKeeperInitialization(Scanner scanner) {
      System.out.println("Please enter initial arrangement of books followed by newline:");
      // Get the input and turns it into arrays of string separated by empty space
      String input = scanner.nextLine().trim();
      String[] strings = input.split("\\s+");
      BookshelfKeeper bookshelfKeeper = null;
      // If the input is empty or length of strings is 0
      if (input.isEmpty() || strings.length == 0) {
         bookshelfKeeper = new BookshelfKeeper();
      } else {
         // Strings is not empty, parse string in strings as int, check if int is positive
         ArrayList<Integer> books = new ArrayList<>();
         for (String s : strings) {
            int num = Integer.parseInt(s);
            // Height should be positive
            if (num <= 0) {
               System.out.println("ERROR: Height of a book must be positive.");
               System.out.println("Exiting Program.");
               System.exit(0);
            }
            books.add(num);
         }
         // check if the books are in non-decreasing order
         Bookshelf bookshelf = new Bookshelf(books);
         if (!bookshelf.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            System.exit(0);
         }
         bookshelfKeeper = new BookshelfKeeper(bookshelf);
      }
      System.out.println(bookshelfKeeper);
      return bookshelfKeeper;
   }

   /**
    * Prompt for user input to perform a series of operations on the bookshelf. The valid operations
    * include put, pick and end, which is insert a book with a certain height, pick a book from a
    * certain location, and end the program. If user enters any invalid commands, it will be warned
    * and the program will exit after warning. For put, the height given must be positive and for
    * pick, the position given must be within the valid bounds of the bookshelf.
    */
   private static void promptForInput(Scanner scanner, BookshelfKeeper bookshelfKeeper) {
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      while (true){
         String input = scanner.nextLine().trim();
         String[] strings = input.split("\\s+");
         // If the input is empty or length of strings is more than 2
         if (input.isEmpty() || strings.length > 2) {
            System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
            System.out.println("Exiting Program.");
            System.exit(0);
         }
         // Input should be "function int", index could be the height of the book or index in bookshelf
         String function = strings[0];
         int index = 0;
         // For operation "end", it will not have index 1
         if (strings.length == 2) {
            index = Integer.parseInt(strings[1]);
         }
         // Function can be "pick", "put", "end", and others
         if (function.equals("pick")) {
            pick(index, bookshelfKeeper);
         } else if (function.equals("put")) {
            put(index, bookshelfKeeper);
         } else {
            if (!function.equals("end")) {
               System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
            }
            System.out.println("Exiting Program.");
            System.exit(0);
         }
      }
   }

   /**
    * Pick will pick out the certain book from a given index on the bookshelf, which means the book
    * will be removed from the bookshelf. For pick, the index given should be within the valid bound
    * for the bookshelf. If the index is out of bound the program will exit after warning. If the
    * input is valid, this book will be taken out with the least operations.
    *
    * PRE: 0 <= index < nums of books
    */
   private static void pick(int index, BookshelfKeeper bookshelfKeeper) {
      if (index < 0 || index >= bookshelfKeeper.getNumBooks()) {
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
         System.out.println("Exiting Program.");
         System.exit(0);
      } else {
         bookshelfKeeper.pickPos(index);
         System.out.println(bookshelfKeeper);
      }
   }

   /**
    * Put will put in the book with a given height on the bookshelf, which means the book will be
    * added to the bookshelf. For put, the height given should be positive. If the height is negative,
    * the program will exit after warning. If the input is valid, this book will be put in with the
    * least operations.
    *
    * PRE: 0 < height, height is a positive integer
    */
   private static void put(int height, BookshelfKeeper bookshelfKeeper) {
      if (height <= 0) {
         System.out.println("ERROR: Height of a book must be positive.");
         System.out.println("Exiting Program.");
         System.exit(0);
      } else {
         bookshelfKeeper.putHeight(height);
         System.out.println(bookshelfKeeper);
      }
   }
}
