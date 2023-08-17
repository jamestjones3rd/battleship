import java.util.ArrayList;

public class Battleship {
    public static final int SIZE = 3;  //integer representing the size of the battleship.

    private String name; // string representing the name of the battleship.
    private ArrayList<String> location; //ArrayList of strings representing the coordinates occupied by the battleship on the game grid.
    private boolean[] hitMarkers; //array of booleans representing whether each section of the battleship has been hit.
    private boolean isSunk; //boolean indicating whether the battleship is sunk.

    //Initializes the name, location, hitMarkers, and isSunk variables.
    public Battleship(String name, ArrayList<String> location) {
        this.name = name;
        this.location = location;
        this.hitMarkers = new boolean[SIZE];
        this.isSunk = false;
    }

    //Returns the name of the battleship.
    public String getName() {
        return name;
    }

    //Returns the location of the battleship.
    public ArrayList<String> getLocation() {
        return location;
    }

    //Returns whether the battleship is sunk.
    public boolean isSunk() {
        return isSunk;
    }

    //Checks if the guessed location matches any part of the battleship.
    // If a hit is registered, it marks the hit in the hitMarkers array and checks
    // if all parts of the battleship are hit (sunk).
    public boolean checkGuess(String guess) {
        int index = location.indexOf(guess);
        if (index >= 0) {
            hitMarkers[index] = true;
            if (allHit()) {
                isSunk = true;
            }
            return true;
        }
        return false;
    }

    //Checks if all parts of the battleship are hit
    private boolean allHit() {
        for (boolean hit : hitMarkers) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    //Checks if the battleship's location collides with any other locations.
    public boolean collision(ArrayList<String> locations) {
        for (String location : locations) {
            if (this.location.contains(location)) {
                return true;
            }
        }
        return false;
    }

    //Displays the hit markers on the battleship as 'X' for hits and 'O' for misses.
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (hitMarkers[i * SIZE + j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }
}
