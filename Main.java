import java.util.Scanner;
/**
 * AP Computer Science A MKS21X-01
 * @author Kareem Ibrahim
 * Date created: 1/10/17
 * 
 * You will create a tic tac toe game that can be played between two players. 
 * The directions are purposely vague in order for you to develop the game
 * based on what you need. There are a few requirements to this project and the rest is up to you.
 * 1) You must create another class. You game should run in this main method by calling on methods or objects from the other class
 * 2) Your methods and class variables must make sense. You should be able to answer questions like: why did you make that method static?
 * 3) The game is played by two people who will enter moves through the keyboard, we are not introducing a computer player at this time.
 * 4) Your game should work and should work intuitively (add text, add functionality, add choice)
 * 5) Your game board should be formed by a 2d array. You should print the board after every move.
 * 6) Your program must contain author rem, method headers, and line rems,
 * 
 * Some methods you may need:
 * 1) print the gameboard
 * 2) change the player
 * 3) get the player's move
 * 4) check the validity of the player's move
 * 5) check for win conditions
 * 
 */
public class Main {
	/*
	  There is much more to work on with this project and many more fixes but the current version right now works.
	 */
	// Scan and game should be used across all methods. In order to use
	// the methods in main, all variables and methods must be static.
    
    /**
     * The main method asks for user input on playing a Tic Tac Toe game.
     * All input is checked to see if they're proper. The user has options
     * to play against another player or the AI.
     * 
     * @param args Command line arguments. Not used here.
     */
    public static void main(String[] args) {
    	scan = new Scanner(System.in);
        //TODO Allow user to go up against the computer
        
        // Directions
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("The rules are simple, player 1 will choose whether or not they want to be an \'X\' or a \'O\'.");
        System.out.println("You will then take turns filling up the \'_\'\'s in the board.");
        System.out.println("Write the row and column number separated by a space you would like to fill in, i.e. 1 1 would refer to the upper leftmost spot.");
        System.out.println("Numbers must be between 1 and 3 inclusive");
        stop();
        
        // Accept 'X' or 'O' input and check for proper user input
        System.out.println("PLAYER 1! CHOOSE YOUR WEAPON, O OR X? ");
        char weapon = scan.next().toUpperCase().charAt(0);
        while (weapon != 'X' && weapon != 'X') {
        	System.out.println("Please enter in an X or an O bruh."); // Checks if user input is an 'X' or 'O'
        	weapon = scan.next().toUpperCase().charAt(0); // Gets input, turns it to uppercase, and gets the first char
        }
        
        // Create the TicTacToePlayers and the TicTacToe game
        TicTacToePlayer player1 = new TicTacToePlayer(weapon, "Player 1");
        if (weapon == 'O') weapon = 'X'; else weapon = 'O'; // Changes value to 'X' if 'O' and vice versa
        // Ask if the user wants to play against the computer or not
        System.out.println("Would you like to play against the computer? Enter Y or N to respond.");
        
        char response = scan.next().toUpperCase().charAt(0);
        // Check for valid input
        if (response == 'Y') {
        	TicTacToePlayer player2 = new TicTacToePlayer(weapon, "The Computer");
        	
        	Computer comp = new Computer(weapon);
        	
        	
            playerVersusComputer(player1, comp);
        } else if (response == 'N') {
        	TicTacToePlayer player2 = new TicTacToePlayer(weapon, "Player 2");
        	
<<<<<<< HEAD
        	game = new TicTacToe(player2, player1);
=======
>>>>>>> bugs
        	
            playerVersusPlayer(player1, player2);
        } else {
        	
            System.out.println("You didn't write the appropiate letter. Ending...");
        }
    }
    
    /**
     * This method allows the user to play against another player.
     * 
     * @param player1 First TicTacToePlayer
     * @param player2 Second TicTacToePlayer
     */
    public static void playerVersusPlayer(TicTacToePlayer player1, TicTacToePlayer player2) {
        while (true) {
            game.printBoard();
            
            
            int pos1 = scan.next().charAt(0), pos2 = scan.next().charAt(0); // Take the numerical char value of the user's input for two positions
            if (!correctPositions(pos1, pos2)) {
                System.out.println("WHAT DID I SAY? Numbers between 1 and 3 please.");
                continue;
            }
            if (!game.validMove(pos1 - 48, pos2 - 48)) { // Check if the user's move is valid
                System.out.println("You can't put it there! Choose again!");
                continue;
            }
            game.turn(pos1 - 48, pos2 - 48); // Make the next turn in the game by inserting the actual numerical values of the input
            
            if (won()) game.changeTurn(); else break;
        }
    }
    
    /**
     * Allows the user to play against the Computer
     * 
     * @param player1 The user TicTacToePlayer
     * @param comp    The Computer object to play against
     */
    public static void playerVersusComputer(TicTacToePlayer player1, Computer comp) {
        while (true) {
        	game.printBoard();
        	
        	System.out.print(game.getTurn().getPlayer() + ": "); // Print current player name
            
            int pos1 = scan.next().charAt(0), pos2 = scan.next().charAt(0); // Take the numerical char value of the user's input for two positions
            if (!correctPositions(pos1, pos2)) {
                System.out.println("WHAT DID I SAY? Numbers between 1 and 3 please.");
                continue;
            }
            if (!game.validMove(pos1 - 48, pos2 - 48)) { // Check if the user's move is valid
                System.out.println("You can't put it there! Choose again!");
                continue;
            }
            game.turn(pos1 - 48, pos2 - 48); // Make the next turn in the game by inserting the actual numerical values of the input
            
            if (won()) {
            	game.changeTurn();
            	comp.update(game.getBoard()); // Update the computer with the most recent game board
            	game.compTurn(comp.nextMove()); // Perform the Computer's next move
            	if (won()) game.changeTurn(); else break;
            } else {
            	break;
            }
        }
    }
    
    /**
     * Make sure that the positions on the game board by the user
     * are correct in their scope, that is between 1 and 3 inclusive.
     * The method uses ASCII values to check.
     * 
     * @param a First position to check.
     * @param b Second position to check.
     * @return  True if the positions are between 1 and 3 inclusive, false otherwise.
     */
    public static boolean correctPositions(int a, int b) {
        return !(a > 51 || a < 49 || b > 51 || b < 49);
    }
    
    /**
     * Checks if any player has won the game. If there is a
     * win, the program asks if the players want to continue.
     * This also checks for no more possible wins in which case
     * it ends the current game. Otherwise the game continues.
     * This method has to change the games turn in order to match
     * up with player1.     
     * @return True if the program should continue. False if the game will end.
     */
    public static boolean won() {
        if (game.win()) { // Check if anyone has won. If someone did then reset the game.
            game.printBoard();
            System.out.println(game.getTurn().getPlayer() + " has won!");
            System.out.println("Enter Y or N to start another game or not.");
            char answer = scan.next().toUpperCase().charAt(0);
            if (answer == 'Y') {
                stop();
                game.resetBoard();
                game.changeTurn();
                return true;
            } else if (answer == 'N') {
                System.out.println("Bye!");
                return false;
            } else {
                System.out.println("THAT\'S NOT A CHOICE! BREAKING OUT! PEACE");
                return false;
            }
        } else if (game.noPossibleWin()) {
            game.printBoard();
            System.out.println("No more winning moves can be made. Restarted!");
            stop();
            game.resetBoard();
            game.changeTurn();
            return true;
        } else {
            return true;
        }
    }
    
    /**
     * Waits for arbitrary user input to continue the program by
     * calling next() on the scan object.
     */ 
    public static void stop() {
        System.out.println("Press any letter and enter to continue...");
        scan.next();
    }
}

