import java.util.Scanner;

class Run {
    public static void main(String[] args) {
        int length, width, bombs;
        Scanner in = new Scanner(System.in);
        System.out.print("Length: ");
        length = in.nextInt();
        System.out.print("\nWidth: ");
        width = in.nextInt();
        System.out.print("\nBombs: ");
        bombs = in.nextInt();
        System.out.println("\n\n");

        Game minesweeper = new Game(length, width, bombs);
        while (!minesweeper.gameIsOver()) {
            textShow(minesweeper.getShowingBoard());
            // Easiest to just type rowNumber *space* colNumber
            int nextRow = in.nextInt();
            int nextCol = in.nextInt();
            minesweeper.open(nextRow, nextCol);
        }
        textShow(minesweeper.getShowingBoard());

        if (minesweeper.gameWon()) {
            System.out.println("Congrats! You won!");
        } else {
            System.out.println("You lost, try again!");
        }
    }

    private static void textShow(String[][] gameBoard) {
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