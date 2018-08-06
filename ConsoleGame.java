
public class ConsoleGame extends Game {

    public ConsoleGame(int length, int width, int numBombs) {
        super(length, width, numBombs);
    }

    public void showBoard() {
        String[][] gameBoard = getShowingBoard();

        System.out.print(" ");
        for (int j = 0; j < gameBoard[0].length; j++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print("|");
            for (int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println("|");
        }
        System.out.print(" ");
        for (int j = 0; j < gameBoard[0].length; j++) {
            System.out.print("-");
        }
        System.out.println();
    }
}