import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipGame {
    private static final int GRID_SIZE = 7;
    private static final int NUM_BATTLESHIPS = 3;
    private static final int MAX_GUESSES = 30; //maximum number of guesses allowed in the game.

    private Battleship[] battleships; //array of Battleship objects representing the battleships in the game.
    private GameHelper helper; //instance of the GameHelper class used for game-related operations.
    private int numGuesses; //integer tracking the number of guesses made.

    //Initializes the battleships array, the GameHelper object,
    // and the numGuesses variable. It also calls the setupGame() method.
    public BattleshipGame() {
        battleships = new Battleship[NUM_BATTLESHIPS];
        helper = new GameHelper(GRID_SIZE);
        numGuesses = 0;
        setupGame();
    }

    //Sets up the game by placing the battleships on the grid using
    //the GameHelper object. It ensures that the battleships do not collide with each other.
    public void setupGame() {
        ArrayList<String> locations;
        for (int i = 0; i < NUM_BATTLESHIPS; i++) {
            do {
                locations = helper.placeStartup(Battleship.SIZE);
            } while (collision(locations));
            battleships[i] = new Battleship("Battleship " + (i + 1), locations);
        }
    }

    //Checks if the given locations collide with any battleships in the game.
    private boolean collision(ArrayList<String> locations) {
        for (Battleship ship : battleships) {
            if (ship != null && ship.collision(locations)) {
                return true;
            }
        }
        return false;
    }

    //Starts the game loop. It prompts the user for guesses, checks if the guesses hit or
    // miss the battleships, and updates the game state accordingly. It continues until
    // the maximum number of guesses is reached or all battleships are sunk.
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

    //Checks if all battleships are sunk.
    private boolean allSunk() {
        for (Battleship ship : battleships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    //The entry point of the program.
    // It creates an instance of the BattleshipGame class and calls the play() method to start the game.
    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.play();
    }
}

