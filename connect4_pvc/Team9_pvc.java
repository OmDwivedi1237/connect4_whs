

import java.util.Random;

class Team9_pvc implements ConnectFourPlayer_pvc {
    Random random = new Random();
    private char piece;
    public Team9_pvc() { }
    // Method to make a move
    public int playerMove(char[][] board) {
        int col;
        do {
            col = random.nextInt(7); 
        } while (!isValidMove(board, col)); 
        return col;
    }
    // Method to make a move for a computer player
    int computerPlayerMove(char[][] board) {
        int col;
        do {
            col = random.nextInt(7); // Generate a random column index (0-6)
        } while (!isValidMove(board, col)); // Repeat if the column is full
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
