package extra_exercises;
import java.io.*;

public class GameOfLife {

    public static void main(String[] args) throws IOException {
        String filePath = "files/gol/glider.gol"; // Path to the file containing the configuration
        int[][] grid = readInitialConfiguration(filePath);

        if (grid == null) {
            System.out.println("Error: Failed to load the grid.");
            return;
        }

        int generations = 5; // Number of generations to simulate

        // Simulate each generation
        for (int generation = 0; generation < generations; generation++) {
            System.out.println("Generation " + generation + ":");
            printGrid(grid);
            grid = nextGeneration(grid); // Calculate the next generation
        }
    }

    // Read the initial configuration from a file
    public static int[][] readInitialConfiguration(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        int rows = 0, cols = 0;
        int startX = 0, startY = 0;
        boolean inDataSection = false;
        String line;

        int[][] grid = null;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            // Ignore comments
            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }

            // Parse grid size
            if (line.startsWith("GRID")) {
                String[] parts = line.split(" ");
                rows = Integer.parseInt(parts[1]);
                cols = Integer.parseInt(parts[2]);
                grid = new int[rows][cols]; // Initialize grid with all cells dead (0)
                continue;
            }

            // Parse starting position (not needed in current context, but could be useful)
            if (line.startsWith("START")) {
                String[] parts = line.split(" ");
                startX = Integer.parseInt(parts[1]);
                startY = Integer.parseInt(parts[2]);
                continue;
            }

            // Once DATA is reached, we begin reading the actual configuration
            if (line.equals("DATA")) {
                inDataSection = true;
                continue;
            }

            // Fill grid based on DATA section, '*' represents live cells
            if (inDataSection) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '*') {
                        grid[startY][startX + i] = 1; // Alive cell
                    }
                }
                startY++;
            }
        }

        br.close();
        return grid;
    }

    // Print the current state of the grid
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "X " : ". "); // X for alive, . for dead
            }
            System.out.println();
        }
        System.out.println();
    }

    // Compute the next state of the grid based on the rules of the Game of Life
    public static int[][] nextGeneration(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] newGrid = new int[rows][cols];

        // Loop over every cell and calculate its next state
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int aliveNeighbors = countAliveNeighbors(grid, row, col);

                // Apply Conway's Game of Life rules
                if (grid[row][col] == 1) { // The cell is alive
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        newGrid[row][col] = 0; // Cell dies
                    } else {
                        newGrid[row][col] = 1; // Cell stays alive
                    }
                } else { // The cell is dead
                    if (aliveNeighbors == 3) {
                        newGrid[row][col] = 1; // Cell becomes alive
                    }
                }
            }
        }
        return newGrid;
    }

    // Count the number of alive neighbors around a given cell
    public static int countAliveNeighbors(int[][] grid, int row, int col) {
        int aliveCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Check all 8 neighboring cells
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Skip the cell itself
                }

                int newRow = row + i;
                int newCol = col + j;

                // Check if the neighbor is within the grid boundaries
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    aliveCount += grid[newRow][newCol]; // Add 1 if the neighbor is alive (1)
                }
            }
        }

        return aliveCount;
    }
}