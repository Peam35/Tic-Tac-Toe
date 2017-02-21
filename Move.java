package tictactoe;

/**
 * The Move class is defined by its mutability, its move, and position. The
 * class is meant to be used as an object in the 2d array board
 * in the TicTacToe class. The class's mutability is checked with
 * a method to check whether or not the class has a default move value of
 * '_'. The class has a specific position on the game board which should
 * be set at the initialization of the game board. The values 
 * of the state variables are accessible through 
 * get methods. The class has a static method to check the equivalence of 
 * three Move classes in order to check for win conditions in the
 * TicTacToe class.
 * 
 * AP Computer Science A MKS21X-01
 * @author Kareem Ibrahim
 * Date created: 1/10/17
 */
public class Move {
    private char move;
    private int[] position;
    
    /**
     * Initializes move to an underscore and position as a length 2 array.
     */ 
    public Move() { 
        move = '_'; // Default value of move
        position = new int[2];
    }
    
    /**
     * Outputs the value of move, default is an underscore.
     * 
     * @return The value of move
     */ 
    public char getMove() { return move; }
    
    /**
     * Changes the value of move from a TicTacToePlayer.
     * 
     * @param player Accepts a TicTacToePlayer and calls the method getMove() 
     *               from it in order to change the value of move.
     */
    public void setMove(TicTacToePlayer player) { move = player.getMove(); }
    
    /**
     * Changes the value of the object's 'move' through a
     * computer object by calling its getMove.
     * 
     * @param comp The computer object to retrieve its move from.
     */
    public void setMove(Computer comp) { move = comp.getMove(); }
    
    /**
     * Gives the position of the Move object on the
     * game board. 
     * 
     * @return The position of the object on the board.
     */
    public int[] getPosition() { return position; }
    
    /**
     * Sets the position of the current Move object on
     * the game board. Should be initialized once on 
     * creation of the game board.
     * 
     * @param pos A 2 length array with the specific position of the object.
     */
    public void setPosition(int[] pos) { position = pos; }
    
    /**
     * Checks if the current Move instance has the
     * default '_' value.
     * 
     * @return True if the Move instance has a move equal to default '_'
     */ 
    public boolean isDefault() { return move == '_'; }
    
    /** 
     * Checks if three Move objects are equal.
     * First makes sure it is not checking a default value of Move, which is an underscore
     * If the value is not an underscore then it is possible for all three objects to be an 'X' or an 'O'
     * Next it checks if three objects of Move have the value of the variable 'move'
     * The method should only be called on by the class in order to check three separate Move objects
     * 
     * @param m1 First Move object to be checked
     * @param m2 Second Move object to be checked
     * @param m3 Third Move object to be checked
     * @return   True if all three Move objects have equivilant 'move' variables
     */
    public static boolean equals(Move m1, Move m2, Move m3) {
        if (m1.isDefault()) return false; // automatically not equal if a Move object is default
        return m1.getMove() == m2.getMove() && m2.getMove() == m3.getMove();
    }
}