/**
 * The TicTacToe class carries many attributes that define
 * a game of Tic Tac Toe. The board of the game is 2d array
 * of Move classes. The state variables player1 and player2
 * reference a player object from the class TicTacToePlayer.
 * The variable turn refers to the current TicTacToePlayer.
 * Variable comp is the reference to a Computer object if
 * the player decides to play against the computer.
 * The purpose of this class is used to implement a game of 
 * Tic Tac Toe.
 * 
 * AP Computer Science A MKS21X-01
 * @author Kareem Ibrahim
 * Date created: 1/10/17
 */
public class TicTacToe {
    private Move[][] board;
    private TicTacToePlayer player1;
    private TicTacToePlayer player2;
    private TicTacToePlayer turn;
    private Computer comp;
    
    /**
     * Initializes player1, player2, turn to player1 as with resetBoard(), and 
     * the board.
     * Resets the board to several default Move objects with move values of '_'
     * 
     * @param p1 The first player to initialize to player1
     * @param p2 The second player to initialize to player2
     */ 
    public TicTacToe(TicTacToePlayer p1, TicTacToePlayer p2) {
        player1 = p1;
        player2 = p2; // player2 can either be another or the computer
        board = new Move[3][3];
        resetBoard();
    }
    
    /**
     * Initializes player1, player2, turn to player1 as with resetBoard(), and 
     * the board. Initializes comp to a Computer object reference.
     * Resets the board to several default Move objects with move values of '_'
     * 
     * @param p1 The first player to initialize to player1.
     * @param p2 The second player to initialize. Its name should be 'Computer' rather anything else.
     * @param computer
     */
    public TicTacToe(TicTacToePlayer p1, TicTacToePlayer p2, Computer computer) {
    	player1 = p1;
    	player2 = p2;
    	comp = computer;
    	board = new Move[3][3];
    	resetBoard();
    }
    
    /**
     * Sets a specific Move from a TicTacToePlayer
     * at a specified index. The indices expected 
     * are 1 more than the actual index desired by
     * the user. This is for more intuitive play.
     * 
     * @param r 1+ the row of the expected index to place a move on the board
     * @param c 1+ the row of the expected index to place a move on the board
     */ 
    public void turn(int r, int c) { board[r - 1][c - 1].setMove(turn); }
    
    /**
     * With a 2 length array, set the move of a Move
     * object with a Computer object.
     * 
     * @param pos The 2 length array with the specific position at which to set a move.
     */
    public void compTurn(int[] pos) { board[pos[0]][pos[1]].setMove(comp); }
    
    /** 
     * Checks if a move at a specific index is valid and
     * can be changed. Expected indices are +1 than the
     * actual index for intuitive play.
     * 
     * @param r 1+ the expected row of the board
     * @param c 1+ the expected column
     * @return  True if move at specified index can be changed 
     */
    public boolean validMove(int r, int c) { return board[r - 1][c - 1].isDefault(); }
    
    /**
     * Changes the current player turn to the other
     * TicTacToePlayer. Checks which is the current
     * player then switches it.
     */ 
    public void changeTurn() {
        if (turn == player1) turn = player2;
        else turn = player1;
    }
    
    /**
     * Gives the current TicTacToePlayer turn. Used
     * to identify who is currently playing.
     * 
     * @return The current turn of the TicTacToPlayer.
     */ 
    public TicTacToePlayer getTurn() { return turn; }
    
    /**
     * Gives the current game board. Should only be used by
     * the Computer class.
     * 
     * @return The current game board with all Moves.
     */
    public Move[][] getBoard() { return board; }
    
    /**
     * Checks if there is a winner of a Tic Tac Toe game.
     * It follows the rules of Tic TacToe by checking
     * the diagonal, rows, and columns for three in a row.
     * 
     * @return True if there is a win on the board and false
     *         if there is no win on the board.
     */
    public boolean win() {
        if (Move.equals(board[0][0], board[1][1], board[2][2])) return true; // Check if left diagonal is equal
        
        if (Move.equals(board[0][2], board[1][1], board[2][0])) return true; // Check if right diagnol is equal
        
        for (int r = 0; r < board.length; r++) {
            if (Move.equals(board[r][0], board[r][1], board[r][2])) return true; // Check if a row is equal
            
            if (Move.equals(board[0][r], board[1][r], board[2][r])) return true; // Check if a column is equal
        }
        return false;
    }
    
    /**
     * Checks if there is a possible move between three Move
     * objects. If there is a possible move between the objects 
     * that is game winning in a Tic Tac Toe game, it returns 
     * true.
     * 
     * @param m1 First Move object to check for equality.
     * @param m2 Second Move object to check for equality.
     * @param m3 Third Move object to check for equality.
     * @return   If all the Moves are default ('_'), all not 
     *           'X', or all not 'O', it returns true. Otherwise it is
     *           false. 
     */ 
    public boolean possibleMove(Move m1, Move m2, Move m3) {
        if (m1.getMove() != 'X' && m2.getMove() != 'X' && m3.getMove() != 'X') { // If all not 'X', return true
            return true;
        } else if (m1.getMove() != 'O' && m2.getMove() != 'O' && m3.getMove() != 'O') { // If all not 'O', return true
            return true;
        }
        
        return false; // Default return false
    }
    
    /**
     * Checks if there any more possible wins on
     * the board. If there are no more possible
     * wins, then it returns true. Otherwise false.
     * 
     * @return True if there are no more moves that
     *         could win the game. False if there
     *         remains game winning moves.
     */ 
    public boolean noPossibleWin() {
        if (possibleMove(board[0][0], board[1][1], board[2][2])) return false; 
        
        if (possibleMove(board[0][2], board[1][1], board[2][0])) return false;
        
        for (int r = 0; r < board.length; r++) {
            if (possibleMove(board[r][0], board[r][1], board[r][2])) return false;
            
            if (possibleMove(board[0][r], board[1][r], board[2][r])) return false;
        }
        
        return true; // Default return true
    }
    
    /** 
     * Resets the game. Sets the board with default
     * '_' Move objects and sets turn to TicTacToePlayer
     * player1. Also sets the positions of the Move objects.
     */
    public void resetBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = new Move(); // Create the Move objects
                board[r][c].setPosition(new int[]{r, c}); // Set the Move objects positions
            }
        }
        turn = player1;
    }
    
    /** 
     * Prints the 2d array board to the console.
     */
    public void printBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c].getMove() + " ");
            }
            System.out.println();
        }
    }
}
