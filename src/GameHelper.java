//this class provides functionality for managing the game grid,
// placing startups, getting user input, and displaying the game board

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
    private static final String ALPHABET = "abcdefg"; //string representing the alphabet used for grid column labels.
    private static final int MAX_ATTEMPTS = 200; //maximum number of attempts allowed for placing a startup.

    private final int gridSize; //integer representing the size of the game grid.
    private final char[][] grid; // 2D character array representing the game grid.
    private final Random random = new Random(); //class used for generating random numbers.

    private int startupCount = 0; // integer tracking the number of startups placed.

    //Initializes the gridSize variable and creates a grid filled with dots ('.').
    public GameHelper(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new char[gridSize][gridSize];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
    }
//Asks the user for input and returns it as a lowercase string.
    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    //Attempts to place a startup on the grid. It generates random coordinates and checks if
    // the startup fits and the coordinates are available. If successful, it marks the position
    // on the grid and returns the alpha coordinates as an ArrayList of strings.
    public ArrayList<String> placeStartup(int startupSize) {
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = getIncrement();

        while (!success && attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(gridSize * gridSize);

            for (int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;
                location += increment;
            }

            if (startupFits(startupCoords, increment)) {
                success = coordsAvailable(startupCoords);
            }
        }

        savePositionToGrid(startupCoords);
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    //Checks if the startup coordinates fit within the grid based on the increment value.
    boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == 1) {
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < gridSize * gridSize;
        }
    }

    //Checks if the given startup coordinates are available (not already occupied) on the grid.
    boolean coordsAvailable(int[] startupCoords) {
        for (int coord : startupCoords) {
            int row = calcRowFromIndex(coord);
            int col = calcColFromIndex(coord);
            if (grid[row][col] != '.') {
                return false;
            }
        }
        return true;
    }

    void savePositionToGrid(int[] startupCoords) {
        for (int coord : startupCoords) {
            int row = calcRowFromIndex(coord);
            int col = calcColFromIndex(coord);
            grid[row][col] = ' ';
        }
    }

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<>();
        for (int coord : startupCoords) {
            int row = calcRowFromIndex(coord);
            int col = calcColFromIndex(coord);
            String alphaCoords = getAlphaCoordsFromIndex(row, col);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }

    String getAlphaCoordsFromIndex(int row, int col) {
        String letter = ALPHABET.substring(col, col + 1);
        return letter + row;
    }

    private int calcRowFromIndex(int index) {
        return index / gridSize;
    }

    private int calcColFromIndex(int index) {
        return index % gridSize;
    }

    private int getIncrement() {
        if (startupCount % 2 == 0) {
            return 1;
        } else {
            return gridSize;
        }
    }

    public void displayBoard() {
        System.out.print("  ");
        for (int i = 0; i < gridSize; i++) {
            System.out.print(ALPHABET.charAt(i) + " ");
        }
        System.out.println();

        for (int row = 0; row < gridSize; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < gridSize; col++) {
                char displayChar = grid[row][col];
                if (displayChar == 'X') {
                    System.out.print(displayChar + " ");
                } else if (displayChar == 'O') {
                    System.out.print(displayChar + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void markHit(String guess) {
        int row = Integer.parseInt(guess.substring(1)) - 1;
        int col = ALPHABET.indexOf(guess.charAt(0));
        grid[row][col] = 'X';
    }

    public void markMiss(String guess) {
        int row = Integer.parseInt(guess.substring(1)) - 1;
        int col = ALPHABET.indexOf(guess.charAt(0));
        grid[row][col] = 'O';
    }
}
