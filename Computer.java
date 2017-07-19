/** 
 * AP Computer Science A MKS21X-01
 * @author Kareem Ibrahim
 * Date created: 1/17/17
 * 
 * The Computer class is the running Artificial Intelligence (AI)
 * for the Tic Tac Toe game. It contains several private/helper 
 * methods in order to find the optimal place to put a move. The
 * class is defined with a Move board derived from an instance of
 * TicTacToe, the move of the opposing player, and the Computer's
 * move. The class must be constantly updated with the most recent
 * game board.
 */
public class Computer {
    private Move[][] board;
    private char userMove;
    private char move;
    
    /**
     * This constructor sets the value of move to compMove and
     * sets userMove to the opposite of move.
     */
    public Computer(char compMove) {
        move = compMove;
        if (move == 'O') userMove = 'X'; else userMove = 'O';
    }
    
    /**
     * Updates the Move board with the newest version
     * 
     * @param b The newest version of the game board
     */
    public void update(Move[][] b) { board = b; }
    
    /**
     * Provides the next index that the computer should place a move.
     * First checks if there is any spot that could win the computer
     * the game, then checks if there is a spot that could win the player
     * the game in which the computer blocks it, and then it just picks any
     * spot
     * 
     * @return a length 2 array that is the position of the next Computer
     * 		   move.
     */
    public int[] nextMove() { 
    	if (canCompWin()) return possibleWinMoveForComp();
    	if (canPlayerWin()) return possibleWinMoveForPlayer();
    	return possiblePosition();
    }
    
    /**
     * Gives back the value of move which is the
     * Computer's char move.
     * 
     * @return The computer's move.
     */
    public char getMove() { return move; }
    
    // The following private methods are meant to only be used by the Computer class, not by any object
    
    /**
     * Checks for a possible move at that could later 
     * on win the game. Checks if there are any opponent
     * moves within three Move objects and returns true if
     * there are no opponent moves in that range.
     * 
     * @param m1 First Move object to check.
     * @param m2 Second Move object to check.
     * @param m3 Third Move object to check.
     * @return True if a Move can be placed between the three Move
     *         objects. False if there is the opposite char in them.
     */
    private boolean possibleMoveForComp(Move m1, Move m2, Move m3) {
        if (m1.isDefault() && m2.isDefault() && m3.isDefault()) { // If all default, return true
            return true;
        } else if (m1.getMove() != userMove && m2.getMove() != userMove && m3.getMove() != userMove) { // If all not all char a, return true
            return true;
        }
        return false; // Default return false
    }
    
    /**
     * If there are no other logical moves to make,
     * randomPosition just finds a random position
     * at which a move can be made.
     * 
     * @return A random move position
     */
    private int[] randomPosition() {
    	for (int r = 0; r < board.length; r++) {
    		for (int c = 0; c < board[0].length; c++) {
    			if (board[r][c].isDefault()) return board[r][c].getPosition();
    		}
    	}
    	return new int[]{}; // This line should never execute as if the game ends, there are already no more moves to play
    }
    
    /**
     * Finds a possible position for the computer AI to
     * use         bugs.
     * 
     * @return An array of two ints that specifies the row and
     *         column respectively that the computer should pick.
     */
    private int[] possiblePosition() {
        // Checks if there is a possible move from the left diagnol for the computer
        if (possibleMoveForComp(board[0][0], board[1][1], board[2][2])) {
            int c = 0;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c].isDefault()) return board[r][c].getPosition();
                c++;
            }
        }
        
        // Checks if there is a possible move from the right diagnol for the computer
        if (possibleMoveForComp(board[0][2], board[1][1], board[2][0])) {
            int c = 2;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c].isDefault()) return board[r][c].getPosition();
                c--;
            }
        }
        
        for (int r = 0; r < board.length; r++) {
            // Checks if there is a possible move in a row for the computer
            if (possibleMoveForComp(board[r][0], board[r][1], board[r][2])) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[r][c].isDefault()) return board[r][c].getPosition();
                }
            }
            
            // Checks if there is a possible move in a column for the computer
            if (possibleMoveForComp(board[0][r], board[1][r], board[2][r])) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[c][r].isDefault()) return board[c][r].getPosition();
                }
            }
        }
        
        return randomPosition(); // If all else fails, return a random position
    }
    
    /**
     * Check for a possible win among three Move objects.
     * Specifically checks that there are two objects with
     * a move value equal to char a and one value that is
     * default. Fails automatically if all three objects 
     * are not default.
     * 
     * @param m1 First Move object to check
     * @param m2 Second Move object to check
     * @param m3 Third Move object to check
     * @param a  The char used to check equivalence among the three Moves.
     * @return   True if there exactly two Move objects that are equal and
     * 		     false otherwise.
     */
    private boolean possibleWinAtChar(Move m1, Move m2, Move m3, char a) {
    	if (!m1.isDefault() && !m2.isDefault() && !m3.isDefault()) return false;
    	// Check if the moves equal the char with the next three variables
    	boolean first = a == m1.getMove();
    	boolean second = a == m2.getMove();
    	boolean third = a == m3.getMove();
    	// Checks for if only two of the computer's moves are within the 3 Moves
    	return (first && second && !third) || (first && third && !second) || (second && third && !first);
    }
    
    /**
     * Checks if there are any winning moves at char a.
     * 
     * @param a The char to check for a winning move with.
     * @return  True if there is a winning move with char,
     * 			false otherwise.
     */
    private boolean canWinAtChar(char a) {
    	if (possibleWinAtChar(board[0][0], board[1][1], board[2][2], a)) return true; 
        
        if (possibleWinAtChar(board[0][2], board[1][1], board[2][0], a)) return true; 
        
        for (int r = 0; r < board.length; r++) {
            if (possibleWinAtChar(board[r][0], board[r][1], board[r][2], a)) return true; 
            
            if (possibleWinAtChar(board[0][r], board[1][r], board[2][r], a)) return true;
        }
        
        return false;
    }
    
    /**
     * Checks specifically for a win with the computer's move.
     * 
     * @return True if the computer has a winning move, false otherwise.
     */
    private boolean canCompWin() { return canWinAtChar(move); }
    
    /**
     * Checks specifically for a win with the player's move.
     * 
     * @return True if the player has a winning move, false otherwise.
     */
    private boolean canPlayerWin() { return canWinAtChar(userMove); }
    
    /**
     * Finds a default Move among three Move objects.
     * This method however assumes a call on 
     * possibleWinAtChar(m1, m2, m3) returns true.
     * 
     * @param m1 First Move to check.
     * @param m2 Second Move to check.
     * @param m3 Third Move to check. 
     * @return   A default Move object from m1, m2, and m3.
     */
    private int[] possibleWinPosition(Move m1, Move m2, Move m3) {
    	if (m1.isDefault()) {
    		return m1.getPosition();
    	} else if (m2.isDefault()) {
    		return m2.getPosition();
    	} else {
    		return m3.getPosition();
    	}
    }
    
    /**
     * Goes through the whole board and checks for
     * any winning moves with char a and returns 
     * the winning move. Assumes that there is a
     * winning move for char a.
     * 
     * @param a The char to check for winning moves.
     * @return  The winning position on the board.
     */
    private int[] possibleWinMoveAtChar(char a) {
    	if (possibleWinAtChar(board[0][0], board[1][1], board[2][2], a)) return possibleWinPosition(board[0][0], board[1][1], board[2][2]);
        
        if (possibleWinAtChar(board[0][2], board[1][1], board[2][0], a)) return possibleWinPosition(board[0][2], board[1][1], board[2][0]);
        
        for (int r = 0; r < board.length; r++) {
            if (possibleWinAtChar(board[r][0], board[r][1], board[r][2], a)) return possibleWinPosition(board[r][0], board[r][1], board[r][2]);
            
            if (possibleWinAtChar(board[0][r], board[1][r], board[2][r], a)) return possibleWinPosition(board[0][r], board[1][r], board[2][r]);
        }
        
        return randomPosition(); // Should not execute as a call on this method should return a winning position
    }
    
    /**
     * Gives a winning position for the computer's
     * move. Assumes that there is a winning move
     * for the computer.
     * 
     * @return The winning position for the computer
     */
    private int[] possibleWinMoveForComp() { return possibleWinMoveAtChar(move); }
    
    /**
     * Gives a winning position for the player's move.
     * Assumes that there is a winning move for the player.
     * 
     * @return The winning position for the player
     */
    private int[] possibleWinMoveForPlayer() { return possibleWinMoveAtChar(userMove); }
}
