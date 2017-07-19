/**
 * The TicTacToePlayer class is the fundamental class in running
 * the Tic Tac Toe game. The class is defined by what is move is,
 * for example 'X' or 'O', and its name, for example 'Player 1' or
 * 'Player 2'. There are get methods in the class to identify the
 * player and their move.
 * 
 * AP Computer Science A MKS21X-01
 * @author Kareem Ibrahim
 * Date created: 1/10/17
 */
public class TicTacToePlayer {
    private char move;
    private String player;

    /**
     * The constructor sets the state variables
     * move and player. Cannot be changed at a
     * later point.
     * 
     * @param mov char variable to set move.
     * @param p   String variable to set player.
     */ 
    public TicTacToePlayer(char mov, String p) {
        mov = move;
        player = p;
    }
    
    /**
     * Gives the value of char move. Move is used
     * set a character to the class Move.
     * 
     * @return The value of the char move.
     */ 
    public char getMove() { return move; }
    
    /**
     * Gives the String variable player. Player
     * is the name of the current player.
     * 
     * @return The value of the player name.
     */ 
    public String getPlayer() { return player; }
}
