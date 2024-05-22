package connect4_alg;

import java.util.Random;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private char[][] board;
    private ConnectFourPlayer redPlayer;
    private ConnectFourPlayer blackPlayer;
    private Random random;

    public ConnectFour(ConnectFourPlayer redPlayer, ConnectFourPlayer blackPlayer) {
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
        board = new char[ROWS][COLS];
        random = new Random();
        resetBoard();

        // Set the players' pieces
        this.redPlayer.setPiece('R');
        this.blackPlayer.setPiece('B');
    }

    // Method to reset the board to its initial state
    private void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Method to display the current board state
    public void displayBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    // Method to make a move for a player
    public void makeMove(ConnectFourPlayer player, char symbol) {
        int col = player.playerMove(board);
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == '-') {
                board[i][col] = symbol;
                break;
            }
        }
    }

    // Method to check if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if a player has won
    public char checkWinner() {
        // Check rows
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i][j + 1] &&
                        board[i][j] == board[i][j + 2] &&
                        board[i][j] == board[i][j + 3]) {
                    return board[i][j];
                }
            }
        }
        // Check columns
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i + 1][j] &&
                        board[i][j] == board[i + 2][j] &&
                        board[i][j] == board[i + 3][j]) {
                    return board[i][j];
                }
            }
        }
        // Check diagonals (top-left to bottom-right)
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i + 1][j + 1] &&
                        board[i][j] == board[i + 2][j + 2] &&
                        board[i][j] == board[i + 3][j + 3]) {
                    return board[i][j];
                }
            }
        }
        // Check diagonals (bottom-left to top-right)
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i - 1][j + 1] &&
                        board[i][j] == board[i - 2][j + 2] &&
                        board[i][j] == board[i - 3][j + 3]) {
                    return board[i][j];
                }
            }
        }
        return '-'; // Return '-' if there is no winner
    }

    // Method to simulate a match between two players
    public char simulateMatch() {
        ConnectFourPlayer currentPlayer = random.nextBoolean() ? redPlayer : blackPlayer;
        char currentSymbol = random.nextBoolean() ? 'R' : 'B';

        while (true) {
            makeMove(currentPlayer, currentSymbol);
            if (checkWinner() != '-') {
                return checkWinner();
            }
            if (isBoardFull()) {
                return 'D'; // Draw
            }
            currentPlayer = (currentPlayer == redPlayer) ? blackPlayer : redPlayer;
            currentSymbol = (currentSymbol == 'R') ? 'B' : 'R';
        }
    }
}

