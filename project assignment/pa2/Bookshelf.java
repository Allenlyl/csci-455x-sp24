// Name: Yilang Liang
// USC NetID: yilangli
// CSCI455 PA2
// Spring 2024


import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

   /**
      Representation invariant:

      <put rep. invar. comment here>

   */
   
   // <add instance variables here>
   private ArrayList<Integer> bookshelf;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      this.bookshelf = new ArrayList<>();
      assert isValidBookshelf() : "Bookshelf is invalid after calling the constructor";
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.bookshelf = new ArrayList<>(pileOfBooks);
      assert isValidBookshelf() : "Bookshelf is invalid after calling the constructor";
   }

//   /**
//    * Return an ArrayList of books that are on the bookshelf
//    * 好像不能加这个function
//    * PRE: bookshelf is a valid Bookshelf
//    */
//   public ArrayList<Integer> toArrayList() {
//      assert isValidBookshelf() : "Bookshelf is invalid after calling the toArrayList()";
//      return this.bookshelf;
//   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      this.bookshelf.add(0, height);
      assert isValidBookshelf() : "Bookshelf is invalid after calling addFront()";
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      this.bookshelf.add(height);
      assert isValidBookshelf() : "Bookshelf is invalid after calling addLast()";

   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      assert isValidBookshelf() : "Bookshelf is invalid after calling the removeFront()";

      return this.bookshelf.remove(0);

   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      assert isValidBookshelf() : "Bookshelf is invalid after calling the removeLast()";

      return this.bookshelf.remove(bookshelf.size() - 1);
   }

   /**
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      assert isValidBookshelf() : "Bookshelf is invalid after calling the getHeight";

      return this.bookshelf.get(position);
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf() : "Bookshelf is invalid after calling the size";

      return this.bookshelf.size();

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
//      String output = "";
//      for (int book : this.bookshelf) {
//         output += book + ", ";
//      }
//      return "[" + output + "]";
      assert isValidBookshelf() : "Bookshelf is invalid after calling the toString";

      return this.bookshelf.toString();
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      int prev = this.bookshelf.get(0);
      for (int book : this.bookshelf) {
         if (book < prev) {
            return false;
         }
         prev = book;
      }
      assert isValidBookshelf() : "Bookshelf is invalid after calling the isSorted";

      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * Representation invariant:
    * All elements in bookshelf should have be a positive integer
    */
   private boolean isValidBookshelf() {
      for (int book : this.bookshelf) {
         if (book <= 0) {
            return false;
         }
      }
      return true;
   }

   public static void main(String[] args) {
      Bookshelf bookshelf = new Bookshelf();
      for (int i = -2; i < 5; i++) {
         bookshelf.addFront(i);
      }
      System.out.println(bookshelf.isSorted());
      System.out.println(bookshelf.toString());
   }
}
