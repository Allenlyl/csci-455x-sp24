public class MineSweeperTester {
    private static boolean[][] smallMineField =
            {{false, false, false, false},
            {true, false, false, false},
            {false, true, false, false},
            {false, true, false, true}};

    private static boolean[][] emptyMineField =
            {{false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false}};

    private static boolean[][] almostEmptyMineField =
            {{false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, true, false, false}};

    public static void main(String[] ars) {
        // it should be creating a defensive copy of the boolean[][],
        // which means i cant change the mineField by changing the emptyMineField
        // mineField are hard coded so it will not need to be populated
        MineField mf1 = new MineField(emptyMineField);
        VisibleField vf1 = new VisibleField(mf1);

        // press on an empty field and open up all the blocks


    }

}
