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

      //promptForInput(scanner, bookshelfKeeper);
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
      ArrayList<Integer> books = new ArrayList<>();
      System.out.println("Please enter initial arrangement of books followed by newline:");
      String input = scanner.nextLine();
      if (input.isEmpty()) {
         System.out.println("empty");
      } else {

      }
      String[] strings = input.split("\\s+");
      if (strings.length > 0) {
         for (String s : strings) {
            books.add(Integer.parseInt(s));
         }
      }
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

         if (function.equals("pick")) {
            pick(index);
         } else if (function.equals("put")) {
            put(index);
         } else if (function.equals("end")){
            System.out.println("Exiting Program.");
            System.exit(0);
         }
      }

   }

   private static void pick(int index) {
      return;
   }

   private static void put(int index) {
      return;
   }

}
