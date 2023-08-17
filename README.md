# Battleship Game 

This project is a Java implementation of the classic game Battleship. It consists of three classes: `Battleship`, `GameHelper`, and `BattleshipGame`, and an entry point `Main` class to start the game.

## How the Code Should Function

### Battleship Class
The `Battleship` class represents a single battleship in the game. It has properties for the name, location, hit markers, and whether the ship is sunk. This class also includes methods to check a guess, detect collisions, and display the ship on the game board.

### GameHelper Class
The `GameHelper` class handles user input and manages the game board. It provides methods to place ships on the board, check if a ship fits in a location, check if a location is available, convert coordinates to alpha format, and mark hits and misses on the board.

### BattleshipGame Class
The `BattleshipGame` class contains the main game logic. It sets up the battleships' locations, handles the player's guesses, updates battleship status, and determines the game outcome. The game continues until either all battleships are sunk or the maximum number of guesses is reached.

### Main Class
The `Main` class is the entry point of the program. It creates a new `BattleshipGame` object and calls its `play()` method to start the game.

## How to Run the Code

1. Open your IntelliJ or VSCode.
2. Clone repo. 
5. Run the `Main` class to start the game.

Follow the prompts in the console to input your guesses and play the game. The game will display the game board, provide feedback on your guesses, and inform you when you win or run out of guesses.

Enjoy playing Battleship!
