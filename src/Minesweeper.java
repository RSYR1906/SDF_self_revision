import java.util.Random;
import java.util.Scanner;

class Cell {
    boolean isMine;
    boolean isRevealed;
    int adjacentMines;

    Cell() {
        isMine = false;
        isRevealed = false;
        adjacentMines = 0;
    }
}

class Board {
    private final int rows;
    private final int cols;
    private final int mineCount;
    private final Cell[][] grid;

    public Board(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.grid = new Cell[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }

        // Place mines
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineCount) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!grid[row][col].isMine) {
                grid[row][col].isMine = true;
                placedMines++;
                updateAdjacentCounts(row, col);
            }
        }
    }

    private void updateAdjacentCounts(int mineRow, int mineCol) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = mineRow + i;
                int newCol = mineCol + j;
                if (isValidCell(newRow, newCol) && !grid[newRow][newCol].isMine) {
                    grid[newRow][newCol].adjacentMines++;
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean revealCell(int row, int col) {
        if (!isValidCell(row, col) || grid[row][col].isRevealed) {
            return false;
        }
        
        grid[row][col].isRevealed = true;
        
        if (grid[row][col].isMine) {
            return true;  // Game over if a mine is revealed
        }
        
        // Reveal neighboring cells if no adjacent mines
        if (grid[row][col].adjacentMines == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(row + i, col + j);
                }
            }
        }
        return false;
    }

    public void printBoard(boolean revealMines) {
        System.out.print("   ");
        for (int c = 0; c < cols; c++) System.out.print(c + " ");
        System.out.println();
        for (int r = 0; r < rows; r++) {
            System.out.print(r + " |");
            for (int c = 0; c < cols; c++) {
                if (grid[r][c].isRevealed) {
                    if (grid[r][c].isMine) {
                        System.out.print("* ");
                    } else {
                        System.out.print(grid[r][c].adjacentMines + " ");
                    }
                } else if (revealMines && grid[r][c].isMine) {
                    System.out.print("* ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public boolean isWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine && !grid[i][j].isRevealed) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Minesweeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        System.out.print("Enter number of mines: ");
        int mineCount = scanner.nextInt();

        Board board = new Board(rows, cols, mineCount);

        System.out.println("Welcome to Minesweeper!");
        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard(false);
            System.out.print("Enter row and column to reveal (e.g., 0 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            gameOver = board.revealCell(row, col);
            if (gameOver) {
                System.out.println("Game over! You hit a mine.");
                board.printBoard(true);
            } else if (board.isWin()) {
                System.out.println("Congratulations! You cleared the board!");
                board.printBoard(true);
                break;
            }
        }
        scanner.close();
    }
}
