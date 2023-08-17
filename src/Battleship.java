import java.util.ArrayList;

public class Battleship {
    public static final int SIZE = 3;

    private String name;
    private ArrayList<String> location;
    private boolean[] hitMarkers;
    private boolean isSunk;

    public Battleship(String name, ArrayList<String> location) {
        this.name = name;
        this.location = location;
        this.hitMarkers = new boolean[SIZE];
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public boolean isSunk() {
        return isSunk;
    }

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

    private boolean allHit() {
        for (boolean hit : hitMarkers) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public boolean collision(ArrayList<String> locations) {
        for (String location : locations) {
            if (this.location.contains(location)) {
                return true;
            }
        }
        return false;
    }

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
