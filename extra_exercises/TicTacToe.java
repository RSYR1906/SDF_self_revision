package extra_exercises;

import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3]; // initialise empty board
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            if (currentPlayer == 'X') { // Human player
                System.out.println("Player " + currentPlayer + ", enter your move (row, column):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (isValidMove(row, col)) { // check if move is valid and not overlapping
                    board[row][col] = currentPlayer; // place 'X' on the grid
                    if (checkWin('X')) { // check if player wins
                        printBoard();
                        System.out.println("You win!");
                        break;
                    } else if (isBoardFull()) { // check if board is full
                        printBoard();
                        System.out.println("It's a tie!");
                        break;
                    }
                    changePlayer(); // switch to AI turn
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            } else { // AI move
                System.out.println("AI is making a move...");
                int[] bestMove = findBestMove(); // find the best possible move to win or prevent player from winning
                board[bestMove[0]][bestMove[1]] = currentPlayer;

                if (checkWin('O')) { // check if AI wins
                    printBoard();
                    System.out.println("You lose!");
                    break;
                } else if (isBoardFull()) { // check if board full
                    printBoard();
                    System.out.println("It's a tie!");
                    break;
                }
                changePlayer();
            }
        }

        scanner.close();
    }

    private static void initializeBoard() { // creating empty slots in the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() { // creating board layout
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("--+---+--");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("--+---+--");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    private static boolean isValidMove(int row, int col) { // checking if row and columns are 3x3 and no overlapping
                                                           // moves
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void changePlayer() { // turn basis
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    private static boolean checkWin(char player) {
        // Check rows, columns, and diagonals for three in a row for the given player
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2]; // best position for AI to place (rows,cols)

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == ' ') {
                    board[i][j] = 'O'; // AI's move
                    int score = minimax(0, false);
                    board[i][j] = ' '; // Undo the move

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int minimax(int depth, boolean isMaximizing) {
        if (checkWin('O'))
            return 10 - depth;
        if (checkWin('X'))
            return depth - 10;
        if (isBoardFull())
            return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimax(depth + 1, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimax(depth + 1, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}