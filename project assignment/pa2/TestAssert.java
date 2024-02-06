import java.util.ArrayList;
import java.util.Arrays;

public class TestAssert {
    public static void main(String[] args) {
        ArrayList<Integer> books1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Bookshelf bookshelf1 = new Bookshelf(books1);
        bookshelf1.addFront(-1);

//        ArrayList<Integer> books2 = new ArrayList<>(Arrays.asList(1, -10, 3, -3, 5));
//        Bookshelf bookshelf2 = new Bookshelf(books2);
    }
}
