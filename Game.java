import java.awt.Point;
import java.util.*;

public class Game {
   
   
   /*
    * # is closed
    * X is bomb
    * a number is how many bombs is surrounding it
    * space is when it is empty
   */
   private String[][] board;
   private boolean[][] showing;
   private List<Point> bombLocations = new ArrayList<Point>();
   private boolean gameOver = false;
   private boolean gameWon = false;
   
   private Random random = new Random();
   
   public Game(int length, int width, int numBombs) {
      if (length < 1) length = 1;
      if (width < 1) width = 1;
      board = new String[length][width];
      showing = new boolean[length][width];
      numBombs = Math.min(numBombs, length*width);
      for (int bomb = 0; bomb < numBombs; bomb++) {
         int i = random.nextInt(length);
         int j = random.nextInt(width);
         if (board[i][j] != null && board[i][j].equals("X"))
            bomb--;
         else {
            board[i][j] = "X";
            bombLocations.add(new Point(i, j));
         }
      }
      for (int row = 0; row < length; row++) {
         for (int col = 0; col < width; col++) {
            if (board[row][col] == null) {
               int surroundingBombs = 0;
               for (int i = Math.max(row-1, 0); i <= Math.min(row+1, length-1); i++) {
                  for (int j = Math.max(col-1, 0); j <= Math.min(col+1, width-1); j++) {
                     if (!(i == row && j == col) && (board[i][j] != null && board[i][j].equals("X")))
                        surroundingBombs++;
                  }
               }
               if (surroundingBombs == 0)
                  board[row][col] = " ";
               else
                  board[row][col] = ""+surroundingBombs;
            }
         }
      }
   }
   
   public void open(int r, int c) {
      if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && showing[r][c] == false) {
         if (board[r][c].equals("X")) {
            for (Point p : bombLocations) {
              showing[p.x][p.y] = true;
              gameOver = true;
            }
         }
         else if (board[r][c].equals(" ")) {
            showing[r][c] = true;
            for (int i = Math.max(r-1, 0); i <= Math.min(r+1, board.length-1); i++) {
               for (int j = Math.max(c-1, 0); j <= Math.min(c+1, board[0].length-1); j++) {
                  open(i, j);
               }
            }

         }
         else {
            showing[r][c] = true;
         }
      }
   }
   
   public String[][] getShowingBoard() {
      String[][] showingBoard = new String[board.length][board[0].length];
      for (int r = 0; r < showingBoard.length; r++) {
         for (int c = 0; c < showingBoard[0].length; c++) {
            if (showing[r][c])
              showingBoard[r][c] = board[r][c];
            else
              showingBoard[r][c] = "#";
         }
      }
      return showingBoard;
   }
   
   public boolean gameIsOver() {
      if (gameOver) return gameOver;
      int countNotShowing = 0;
      for (int r = 0; r < showing.length; r++) {
         for (int c = 0; c < showing[0].length; c++) {
            if (!showing[r][c])
               countNotShowing++;
         }
      }
      if (countNotShowing == bombLocations.size()) {
         for (Point p : bombLocations) {
            showing[p.x][p.y] = true;
            gameOver = true;
         }
         gameWon = true;
      }
      return gameOver;
   }
   
   public boolean gameWon() {
      return gameWon;
   }
}