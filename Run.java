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

        Game minesweeper = new ConsoleGame(length, width, bombs);
        while (!minesweeper.gameIsOver()) {
            minesweeper.showBoard();
            // Easiest to just type rowNumber *space* colNumber
            int nextRow = in.nextInt();
            int nextCol = in.nextInt();
            minesweeper.open(nextRow, nextCol);
        }
        in.close();
        minesweeper.showBoard();

        if (minesweeper.gameWon()) {
            System.out.println("Congrats! You won!");
        } else {
            System.out.println("You lost, try again!");
        }
    }
}