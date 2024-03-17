// Name:
// USC NetID:
// CS 455 PA3
// Spring 2024


import java.util.Stack;

/**
 * VisibleField class
 * This is the data that's being displayed at any one point in the game (i.e., visible field, because
 * it's what the user can see about the minefield). Client can call getStatus(row, col) for any
 * square.  It actually has data about the whole current state of the game, including the underlying
 * mine field (getMineField()).  Other accessors related to game status: numMinesLeft(),
 * isGameOver().  It also has mutators related to actions the player could do (resetGameDisplay(),
 * cycleGuess(), uncover()), and changes the game state accordingly.
 * <p>
 * It, along with the MineField (accessible in mineField instance variable), forms the Model for the
 * game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.  It
 * contains the MineField that it's partially displaying.  That MineField can be accessed
 * (or modified) from outside this class via the getMineField accessor.
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus values [0,8] mentioned in comments below) are the
   // possible states of one location (a "square") in the visible field (all are values that can be
   // returned by public method getStatus(row, col)).

   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):

   // values in the range [0,8] corresponds to number of mines adjacent to this opened square

   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already
   // (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of
   // losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused
   // you to lose)
   private final int[][] DIRECTIONS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

   // ----------------------------------------------------------   
   // mineField information
   private MineField mineField;
   // The current status table for each field
   private int[][] statusField;
   // Record whether the game is over. Either by finding all the uncovered or stepping on a mine
   private boolean gameOver;
   // Number of mines that are marked (could be wrong)
   private int numMinesMarked;
   // Number of safe uncovered
   private int numSafeUncovered;

   /**
    * Create a visible field that has the given underlying mineField.
    * The initial state will have all the locations covered, no mines guessed, and the game not
    * over.
    *
    * @param mineField the minefield to use for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      this.statusField = new int[mineField.numRows()][mineField.numCols()];
      resetGameDisplay();
   }


   /**
    * Reset the object to its initial state (see constructor comments), using the same underlying
    * MineField.
    */
   public void resetGameDisplay() {
      for (int i = 0; i < statusField.length; i++) {
         for (int j = 0; j < statusField[0].length; j++) {
            statusField[i][j] = COVERED;
         }
      }
      this.gameOver = false;
      this.numSafeUncovered = 0;
      this.numMinesMarked = 0;
   }


   /**
    * Returns a reference to the mineField that this VisibleField "covers"
    *
    * @return the minefield
    */
   public MineField getMineField() {
      return this.mineField;
   }


   /**
    * Returns the visible status of the square indicated.
    *
    * @param row row of the square
    * @param col col of the square
    * @return the status of the square at location (row, col).  See the public constants at the
    * beginning of the class for the possible values that may be returned, and their meanings.
    * PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      return this.statusField[row][col];
   }


   /**
    * Returns the number of mines left to guess.  This has nothing to do with whether the mines
    * guessed are correct or not.  Just gives the user an indication of how many more mines the user
    * might want to guess.  This value will be negative if they have guessed more than the number of
    * mines in the minefield.
    *
    * @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return this.getNumMines() - this.getNumMinesMarked();
   }


   /**
    * Cycles through covered states for a square, updating number of guesses as necessary.  Call on
    * a COVERED square changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to
    * QUESTION;  call on a QUESTION square changes it to COVERED again; call on an uncovered square
    * has no effect.
    *
    * @param row row of the square
    * @param col col of the square
    *            PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if (statusField[row][col] == COVERED) {
         this.numMinesMarked++;
         statusField[row][col] = MINE_GUESS;
      } else if (statusField[row][col] == MINE_GUESS) {
         this.numMinesMarked--;
         statusField[row][col] = QUESTION;
      } else if (statusField[row][col] == QUESTION) {
         statusField[row][col] = COVERED;
      }
   }


   /**
    * Uncovers this square and returns false iff you uncover a mine here.
    * If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in the
    * neighboring area that are also not next to any mines, possibly uncovering a large region.
    * Any mine-adjacent squares you reach will also be uncovered, and form (possibly along with
    * parts of the edge of the whole field) the boundary of this region.
    * Does not uncover, or keep searching through, squares that have the status MINE_GUESS.
    * Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
    * or a loss (opened a mine).
    *
    * @param row of the square
    * @param col of the square
    * @return false   iff you uncover a mine at (row, col)
    * PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if (getMineField().hasMine(row, col)) {
         this.gameOver = true;
         updateLostStatus(row, col);
         System.out.println(this.mineField);
         System.out.println(toString());
         return false;
      }
      boolean[][] visited = new boolean[getMineField().numRows()][getMineField().numCols()];
      dfs(row, col, visited);
      System.out.println("safe uncovered: " + this.getNumSafeUncovered() + " total is: " + this.getTotalSafeUncovered());
      if (this.getNumSafeUncovered() == this.getTotalSafeUncovered()) {
         this.gameOver = true;
         updateWinStatus();
      }
      System.out.println(this.mineField);
      System.out.println(toString());
      return true;
   }

   private void dfs(int row, int col, boolean[][] visited) {
      // Skip uncovered
      if (visited[row][col] || statusField[row][col] == -2) {
         return;
      }
      visited[row][col] = true;
      int adjMines = getMineField().numAdjacentMines(row, col);
      this.statusField[row][col] = adjMines;
      this.numSafeUncovered++;
      if (adjMines != 0) {
         return;
      }
      for (int[] direction : DIRECTIONS) {
         int curRow = row + direction[0];
         int curCol = col + direction[1];
         if (getMineField().inRange(curRow, curCol) && !isUncovered(curRow, curCol)) {
            dfs(curRow, curCol, visited);
         }
      }
   }

   private void updateLostStatus(int row, int col) {
      statusField[row][col] = EXPLODED_MINE;
      for (int i = 0; i < getMineField().numRows(); i++) {
         for (int j = 0; j < getMineField().numCols(); j++) {
            // if there is a mine in the current location
            if (mineField.hasMine(i, j)) {
               // if the status is covered or question, make it mine(black)
               if (statusField[i][j] == COVERED || statusField[i][j] == QUESTION) {
                  statusField[i][j] = MINE;
               }
            } else {
               // if the status is mine-guess, make it incorrect-guess(x)
               if (statusField[i][j] == MINE_GUESS) {
                  statusField[i][j] = INCORRECT_GUESS;
               }
            }

         }
      }

   }

   private void updateWinStatus() {
      for (int i = 0; i < getMineField().numRows(); i++) {
         for (int j = 0; j < getMineField().numCols(); j++) {
            // if there is a mine in the current location
            if (mineField.hasMine(i, j)) {
               // if the status is covered or question, make it mine(black)
               if (statusField[i][j] == COVERED || statusField[i][j] == QUESTION) {
                  statusField[i][j] = MINE_GUESS;
               }
            }
         }
      }
   }

   /**
    * Returns whether the game is over.
    * (Note: This is not a mutator.)
    *
    * @return whether game has ended
    */
   public boolean isGameOver() {
      return this.gameOver;
   }


   /**
    * Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states,
    * vs. any one of the covered states).
    *
    * @param row of the square
    * @param col of the square
    * @return whether the square is uncovered
    * PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      int status = statusField[row][col];
      if (status == COVERED || status == MINE_GUESS || status == QUESTION) {
         return false;
      }
      return true;
   }

   public String toString() {
      String s = "";
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            s += this.statusField[i][j] + " ";
         }
         s += "\n";
      }
      return s;
   }


   // <put private methods here>
   private int getNumSafeUncovered() {
      return this.numSafeUncovered;
   }

   private int getTotalSafeUncovered() {
      int size = this.getMineField().numRows() * this.getMineField().numCols();
      System.out.println("total safe uncovered is " + (size - this.getNumMines()));
      return size - this.getNumMines();
   }

   private int getNumMinesMarked() {
      return this.numMinesMarked;
   }

   private int getNumMines() {
      return this.getMineField().numMines();
   }
}
