// Name: 
// USC NetID: 
// CSCI455 PA2
// Spring 2024


import java.awt.print.Book;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
      Representation invariant:

      All the books on the shelf should be sorted in non-decreasing order

   */
   
   // <add instance variables here>
   private Bookshelf bookshelf;
   private int totalOperations;
   private int lastOperations;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      this.bookshelf = new Bookshelf();
      this.totalOperations = 0;
      this.lastOperations = 0;
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      this.bookshelf = new Bookshelf();
      this.totalOperations = 0;
      this.lastOperations = 0;
      for (int i = 0; i < sortedBookshelf.size(); i++) {
         bookshelf.addLast(sortedBookshelf.getHeight(i));
      }
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      int size = this.bookshelf.size();
      Bookshelf temp = new Bookshelf();
      int counter = 0;
      // if 4 size, put in position 1, 4/2 = 2, 1<2, start from front
      // if 4 size, put in position 2, 4/2 = 2, 2<=2, start from front
      // if 3 size, put in position 1, 3/2 = 1, 1<=1, start from front
      // if 5 size, put in position 2, 2/2 = 2, 2<=2, start from front
      // Start removing from front of the shelf
      if (position < size / 2) {
         for (int i = 0; i < position; i++) {
            temp.addLast(this.bookshelf.removeFront());
            counter += 1;
         }
         this.bookshelf.removeFront();
         counter += 1;
         for (int i = 0; i < position; i++) {
            this.bookshelf.addFront(temp.removeLast());
            counter += 1;
         }
      } else {
         for (int i = size - 1; i > position; i--) {
            temp.addFront(this.bookshelf.removeLast());
            counter += 1;
         }
         this.bookshelf.removeLast();
         counter += 1;
         for (int i = size - 1; i > position; i--) {
            this.bookshelf.addLast(temp.removeFront());
            counter += 1;
         }
      }
      this.totalOperations += counter;
      this.lastOperations = counter;
      assert isValidBookshelfKeeper(): "BookshelfKeeper is invalid after calling the pickPos()";
      return counter;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0, 0 <= position < size()
    */
   public int putHeight(int height) {
      // Find the closest position to put the book
      int size = this.bookshelf.size();
      int counter = 0;
      Bookshelf temp = new Bookshelf();
      int[] insertPosition = findInsertPosition(height);
      int fromFront = insertPosition[0];
      int position = insertPosition[1];
      if (fromFront == 1) {
         for (int i = 0; i < position; i++) {
            temp.addLast(this.bookshelf.removeFront());
            counter += 1;
         }
         this.bookshelf.addFront(height);
         counter += 1;
         for (int i = 0; i < position; i++) {
            this.bookshelf.addFront(temp.removeLast());
            counter += 1;
         }
      } else {
         for (int i = size - 1; i > position; i--) {
            temp.addFront(this.bookshelf.removeLast());
            counter += 1;
         }
         this.bookshelf.addLast(height);
         counter += 1;
         for (int i = size - 1; i > position; i--) {
            this.bookshelf.addLast(temp.removeFront());
            counter += 1;
         }
      }
      this.totalOperations += counter;
      this.lastOperations = counter;
      assert isValidBookshelfKeeper(): "BookshelfKeeper is invalid after calling the putHeight()";
      return counter;
   }

   /**
    * Return the index of the position for inserting the new book
    * Return position should be 0 <= position < size()
    *
    * PRE: height > 0
    */
   private int[] findInsertPosition(int height) {
      int size = this.bookshelf.size();
      int l = 0;
      int r = size - 1;
      while (l < size) {
         if (this.bookshelf.getHeight(l) >= height) {
            break;
         }
         l++;
      }
      while (r > -1) {
         if (this.bookshelf.getHeight(r) <= height) {
            break;
         }
         r--;
      }
      if (l <= size - 1 - r) {
         System.out.println("we are moving from the front add book before index " + l);
         System.out.println("step should be " + (l * 2 + 1));
      } else {
         System.out.println("we are moving from the back add book after index " + r);
         System.out.println("step should be " + ((size - 1 - r) * 2 + 1));

      }
      // return [front?, index]
      // 1 if it is starting from the front, 0 from the back
      return l <= size - 1 - r ? new int[]{1, l} : new int[]{0, r};
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      return this.totalOperations;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getLastOperations() {
      return this.lastOperations;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
       return this.bookshelf.size();   // dummy code to get stub to compile
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      String output = this.bookshelf.toString();
      output += " " + this.getLastOperations() + " " + this.getTotalOperations();
      return output;
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return this.bookshelf.isSorted();
   }

   public static void main(String[] args) {
      // [1, 2, 3, 4, 5, 6]
      Bookshelf bookshelf = new Bookshelf();
      for (int i = 1; i < 7; i++) {
         bookshelf.addLast(i);
      }
      BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf);
      System.out.println(bookshelfKeeper.toString());
      // take out 3
      bookshelfKeeper.pickPos(2);
      System.out.println(bookshelfKeeper.toString());
      bookshelfKeeper.putHeight(5);
      System.out.println(bookshelfKeeper.toString());
      bookshelfKeeper.putHeight(5);
      System.out.println(bookshelfKeeper.toString());
      bookshelfKeeper.putHeight(4);
      System.out.println(bookshelfKeeper.toString());
      bookshelfKeeper.pickPos(5);
      System.out.println(bookshelfKeeper.toString());
   }

}
