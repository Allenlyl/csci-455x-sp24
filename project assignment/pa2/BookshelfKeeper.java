// Name: Yilang Liang
// USC NetID: yilangli
// CSCI455 PA2
// Spring 2024


import java.awt.print.Book;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation. Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    * Representation invariant:
    *
    * All the books on the shelf is represented by bookshelf which record books by their heights
    * Books in the bookshelf should be sorted in non-decreasing order
    * The total counts of operations of mutators on the bookshelf is totalOperations
    * The last counts of operations of mutators after performing each method is lastOperation
    * Both totalOperation and lastOperation are non-negative integer
    */
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
      this.bookshelf = sortedBookshelf;
      this.totalOperations = 0;
      this.lastOperations = 0;
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
      if (position < size / 2) {
         for (int i = 0; i < position; i++) {
            temp.addLast(this.bookshelf.removeFront());
         }
         this.bookshelf.removeFront();
         for (int i = 0; i < position; i++) {
            this.bookshelf.addFront(temp.removeLast());
         }
         // operations(front) = taking out books + pick target book + putting back books
         counter += 1 + position * 2;
      } else {
         for (int i = size - 1; i > position; i--) {
            temp.addFront(this.bookshelf.removeLast());
         }
         this.bookshelf.removeLast();
         for (int i = size - 1; i > position; i--) {
            this.bookshelf.addLast(temp.removeFront());
         }
         // operations(back) = taking out books + pick target book + putting back books
         counter += 1 + (size - 1 - position) * 2;
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
      // Find the insert position, the return value is [isFromFront, position]
      int[] insertPosition = findInsertPosition(height);
      int isFromFront = insertPosition[0];
      int position = insertPosition[1];
      if (isFromFront == 1) {
         for (int i = 0; i < position; i++) {
            temp.addLast(this.bookshelf.removeFront());
         }
         this.bookshelf.addFront(height);
         for (int i = 0; i < position; i++) {
            this.bookshelf.addFront(temp.removeLast());
         }
         // operations(front) = taking out books + pick target book + putting back books
         counter += 1 + position * 2;
      } else {
         for (int i = size - 1; i > position; i--) {
            temp.addFront(this.bookshelf.removeLast());
         }
         this.bookshelf.addLast(height);
         for (int i = size - 1; i > position; i--) {
            this.bookshelf.addLast(temp.removeFront());
         }
         // operations(back) = taking out books + pick target book + putting back books
         counter += 1 + (size - 1 - position) * 2;
      }
      this.totalOperations += counter;
      this.lastOperations = counter;
      assert isValidBookshelfKeeper(): "BookshelfKeeper is invalid after calling the putHeight()";
      return counter;
   }

   /**
    * Return the index of the position for inserting the new book with "height"
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
      // return [isFromFront, position]
      // compare moving distance from the front and from the back, return the closer one
      // 1 if it is starting from the front, 0 from the back
      return l <= size - 1 - r ? new int[]{1, l} : new int[]{0, r};
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   private int getTotalOperations() {
      return this.totalOperations;
   }

   /**
    * Returns the last number of calls made to mutators on the last function call
    * i.e., all the ones done to perform all of the pick and put operations
    */
   private int getLastOperations() {
      return this.lastOperations;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
       return this.bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
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
      return this.bookshelf.isSorted() && this.lastOperations >= 0 && this.totalOperations >= 0;
   }

}
