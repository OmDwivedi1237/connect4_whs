package connect4_cvc;

import java.util.Random;

class TeamOpponent_cvc implements ConnectFourPlayer_cvc {
    Random random = new Random();
    private char piece;
    public TeamOpponent_cvc() { }
    // Method to make a move
    public int playerMove(char[][] board) {
        int col;
        do {
            col = random.nextInt(7); 
        } while (!isValidMove(board, col)); 
        return col;
    }
   
    // Method to check if the selected column is valid for a move
    boolean isValidMove(char[][] board, int col) {
        return board[0][col] == '-'; // Check if the top row of the column is empty
    }
    // Set the piece (R or B)
    public void setPiece(char piece) {
        this.piece = piece;
    }

    // Get the piece (R or B)
    public char getPiece() {
        return this.piece;
    }
}

