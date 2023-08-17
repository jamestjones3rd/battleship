import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipGame {
    private static final int GRID_SIZE = 7;
    private static final int NUM_BATTLESHIPS = 3;
    private static final int MAX_GUESSES = 30;

    private Battleship[] battleships;
    private GameHelper helper;
    private int numGuesses;

    public BattleshipGame() {
        battleships = new Battleship[NUM_BATTLESHIPS];
        helper = new GameHelper(GRID_SIZE);
        numGuesses = 0;
        setupGame();
    }

    public void setupGame() {
        ArrayList<String> locations;
        for (int i = 0; i < NUM_BATTLESHIPS; i++) {
            do {
                locations = helper.placeStartup(Battleship.SIZE);
            } while (collision(locations));
            battleships[i] = new Battleship("Battleship " + (i + 1), locations);
        }
    }

    private boolean collision(ArrayList<String> locations) {
        for (Battleship ship : battleships) {
            if (ship != null && ship.collision(locations)) {
                return true;
            }
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (numGuesses < MAX_GUESSES && !allSunk()) {
            System.out.println("Guesses remaining: " + (MAX_GUESSES - numGuesses));
            helper.displayBoard();
            String guess = helper.getUserInput("Enter a guess");
            boolean hit = false;
            for (Battleship ship : battleships) {
                if (ship.checkGuess(guess)) {
                    hit = true;
                    if (ship.isSunk()) {
                        System.out.println("You sunk " + ship.getName() + "!");
                    } else {
                        System.out.println("You hit " + ship.getName() + "!");
                    }
                    break;
                }
            }
            if (!hit) {
                System.out.println("You missed.");
                helper.markMiss(guess);
            } else {
                helper.markHit(guess);
            }
            numGuesses++;
        }

        System.out.println("Game over.");
        helper.displayBoard();
        if (allSunk()) {
            System.out.println("Congratulations! You sunk all the battleships in " + numGuesses + " guesses.");
        } else {
            System.out.println("You ran out of guesses.");
        }
    }

    private boolean allSunk() {
        for (Battleship ship : battleships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.play();
    }
}

