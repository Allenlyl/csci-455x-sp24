// Name: Yilang Liang
// USC NetID: yilangli
// CSCI455 PA2
// Spring 2024


import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class BookshelfTester
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
 *
 */

public class BookshelfTester {

    public static void main (String[] args) {
        // Test for Constructor
        emptyConstructorTest();
        ArrayList<Integer> books = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        constructorTest(books);

        // Test for toString()
        Bookshelf bookshelf = new Bookshelf(books);
        toStringTest(bookshelf, books);

        // Test for getHeight()
        getHeightTest(bookshelf, books);

        // Test for addFont()
        ArrayList<Integer> books1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5));
        addFrontTest(bookshelf, 1, books1);

        // Test for addLast()
        ArrayList<Integer> books2 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 6));
        addLastTest(bookshelf, 6, books2);

        // Test for removeLast()
        removeLastTest(bookshelf, books1);

        // Test for removeFront()
        removeFrontTest(bookshelf, books);

        // Test for isSorted()
        isSortedTest(bookshelf, true);

        // Test for size()
        sizeTest(bookshelf, 5);
    }

    /**
    * Test for empty constructor
     *
     * bookshelf should be initialized but it should be empty and size should be 0
     */
    private static void emptyConstructorTest() {
        System.out.println("Empty constructor test: ");
        Bookshelf bookshelf = new Bookshelf();
        if (bookshelf != null && bookshelf.size() == 0) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
    }

    /**
     * Test for empty constructor
     *
     * bookshelf should be initialized but it should be empty and size should be 0
     */
    private static void constructorTest(ArrayList<Integer> books) {
        System.out.println("Constructor test: ");
        int size = books.size();
        Bookshelf bookshelf = new Bookshelf(books);
        if (bookshelf.size() == size) {
            for (int i = 0; i < size; i++) {
                if (books.get(i) == bookshelf.getHeight(i)) {
                    System.out.println("PASSED: Book " + i + " on the shelf matches book " + i + " from the pile.");
                } else {
                    System.out.println("FAILED: Book " + i + " on the shelf does not match book " + i + " from the pile.");
                    return;
                }
            }
            System.out.println("PASSED: All books on the shelf match with books from pile.");
        } else {
            System.out.println("FAILED: Size does not match.");
        }
    }
    /**
     * Test for empty constructor
     *
     * bookshelf should be initialized but it should be empty and size should be 0
     */
    private static void toStringTest(Bookshelf bookshelf, ArrayList<Integer> books) {
        System.out.println("toString() test: ");
        String output = bookshelf.toString();
        String expected = books.toString();
        if (output.equals(expected)) {
            System.out.println("PASSED: output is " + output + "; Expected is " + expected + ".");
        } else {
            System.out.println("FAILED: output is " + output + "; Expected is " + expected + ".");
        }
    }

    /**
     * Test for getHeight
     *
     * The height of the books on the bookshelf should be the same as the books in pile in sequence
     */
    private static void getHeightTest(Bookshelf bookshelf, ArrayList<Integer> books) {
        System.out.println("getHeight() test: ");
        for (int i = 0; i < books.size(); i++) {
            int curHeight = bookshelf.getHeight(i);
            int expected = books.get(i);
            if (curHeight == expected) {
                System.out.println("PASSED: " + i + "th book has height of " + curHeight + "; Expected is " + expected + ".");
            } else {
                System.out.println("FAILED: " + i + "th book has height of " + curHeight + "; Expected is " + expected + ".");
                return;
            }
        }


    }

    /**
     * Test for addFront
     *
     * The height of the books on the bookshelf should be the same as the books in pile in sequence
     */
    private static void addFrontTest(Bookshelf bookshelf, int book, ArrayList<Integer> books) {
        System.out.println("addFront() test: ");
        String expected = books.toString();
        bookshelf.addFront(book);
        String after = bookshelf.toString();
        if (after.equals(expected)) {
            System.out.println("PASSED: Expected after inserting book " + book + "is " + expected + ". The actual is " + after + ".");
        } else {
            System.out.println("FAILED: Expected after inserting book " + book + "is " + expected + ". The actual is " + after + ".");
        }
    }

    /**
     * Test for addLast
     *
     * The height of the books on the bookshelf should be the same as the books in pile in sequence
     */
    private static void addLastTest(Bookshelf bookshelf, int book, ArrayList<Integer> books) {
        System.out.println("addLast() test: ");
        String expected = books.toString();
        bookshelf.addLast(book);
        String after = bookshelf.toString();
        if (after.equals(expected)) {
            System.out.println("PASSED: Expected after inserting book " + book + "is " + expected + ". The actual is " + after + ".");
        } else {
            System.out.println("FAILED: Expected after inserting book " + book + "is " + expected + ". The actual is " + after + ".");
        }
    }

    /**
     * Test for removeFront
     *
     * The first book should be removed from the bookshelf
     */
    private static void removeFrontTest(Bookshelf bookshelf, ArrayList<Integer> books) {
        System.out.println("removeFront() test: ");
        String expected = books.toString();
        bookshelf.removeFront();
        String after = bookshelf.toString();
        if (after.equals(expected)) {
            System.out.println("PASSED: Expected after removing is " + expected + ". The actual is " + after + ".");
        } else {
            System.out.println("FAILED: Expected after removing is " + expected + ". The actual is " + after + ".");
        }
    }

    /**
     * Test for removeLast
     *
     * The last book should be removed from the bookshelf
     */
    private static void removeLastTest(Bookshelf bookshelf, ArrayList<Integer> books) {
        System.out.println("removeLast() test: ");
        String expected = books.toString();
        bookshelf.removeLast();
        String after = bookshelf.toString();
        if (after.equals(expected)) {
            System.out.println("PASSED: Expected after removing is " + expected + ". The actual is " + after + ".");
        } else {
            System.out.println("FAILED: Expected after removing is " + expected + ". The actual is " + after + ".");
        }
    }

    /**
     * Test for isSorted()
     *
     * Identify if the book is sorted
     * @param expected is if the bookshelf is sorted or not
     */
    private static void isSortedTest(Bookshelf bookshelf, Boolean expected) {
        System.out.println("isSorted() test: ");
        if (bookshelf.isSorted() == expected) {
            System.out.println("PASSED: isSorted() works.");
        } else {
            System.out.println("FAILED: isSorted() does not works.");
        }
    }

    /**
     * Test for isSorted()
     *
     * Identify if the book is sorted
     */
    private static void sizeTest(Bookshelf bookshelf, int size) {
        System.out.println("size() test: ");
        if (bookshelf.size() == size) {
            System.out.println("PASSED: size() provides the correct size of the bookshelf.");
        } else {
            System.out.println("FAILED: size() does not provide the correct size of the bookshelf.");
        }
    }
}
