import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeperProg {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      BookshelfKeeper bookshelfKeeper = bookshelfKeeperInitialization(scanner);

      promptForInput(scanner, bookshelfKeeper);
   }

   /**
    * Class BookshelfKeeperProg
    *
    * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
    * non-decreasing order by height, with the restriction that single books can only be added
    * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
    * operation.  Pick or put operations are performed with minimum number of such adds or removes.
    */
   private static BookshelfKeeper bookshelfKeeperInitialization(Scanner scanner) {
      System.out.println("Please enter initial arrangement of books followed by newline:");
      // get the input and turns it into arrays of string separated by empty space
      String input = scanner.nextLine();
      String[] strings = input.split("\\s+");
      // if the input is empty or length of strings is 0
      if (input.isEmpty() || strings.length == 0) {
         BookshelfKeeper bookshelfKeeper = new BookshelfKeeper();
         System.out.println(bookshelfKeeper.toString());
         return new BookshelfKeeper();
      }
      // strings is not empty, parse string in strings as int, check if int is positive
      ArrayList<Integer> books = new ArrayList<>();
      for (String s : strings) {
         int num = Integer.parseInt(s);
         if (num < 0) {
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
      BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf);
      System.out.println(bookshelfKeeper);
      return bookshelfKeeper;
   }

   /**
    * Class BookshelfKeeperProg
    *
    * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
    * non-decreasing order by height, with the restriction that single books can only be added
    * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
    * operation.  Pick or put operations are performed with minimum number of such adds or removes.
    */
   private static void promptForInput(Scanner scanner, BookshelfKeeper bookshelfKeeper) {
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      while (true){
         String function = scanner.next();
         int index = scanner.nextInt();
         // function can be pick, put, end, and others
         if (function.equals("pick")) {
            pick(index, bookshelfKeeper);
         } else if (function.equals("put")) {
            put(index, bookshelfKeeper);
         } else if (function.equals("end")){
            System.out.println("Exiting Program.");
            System.exit(0);
         } else {
            System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
            System.out.println("Exiting Program.");
            System.exit(0);
         }
      }

   }

   private static void pick(int index, BookshelfKeeper bookshelfKeeper) {
      if (index >= bookshelfKeeper.getNumBooks()) {
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
         System.out.println("Exiting Program.");
      } else {
         bookshelfKeeper.pickPos(index);
         System.out.println(bookshelfKeeper.toString());
      }
   }

   private static void put(int height, BookshelfKeeper bookshelfKeeper) {
      if (height < 0) {
         System.out.println("ERROR: Height of a book must be positive.");
         System.out.println("Exiting Program.");
      } else {
         bookshelfKeeper.putHeight(height);
         System.out.println(bookshelfKeeper.toString());
      }
   }

}
