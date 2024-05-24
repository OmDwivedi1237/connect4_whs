package connect4_alg;

class Team9 implements ConnectFourPlayer {
    private char piece;
    private static final int DEPTH = 10; // Adjust depth depending on how OP you want AI to be (WILL INTRODUCE HIGH TIME COMPLEXITY)
    private static final int[][] heuristicGrid = {
        {3, 4, 5, 7, 5, 4, 3},
        {4, 6, 8, 9, 8, 6, 4},
        {5, 8, 11, 13, 11, 8, 5},
        {5, 8, 11, 13, 11, 8, 5},
        {4, 6, 8, 9, 8, 6, 4},
        {3, 4, 5, 7, 5, 4, 3}
    };

    public Team9() { }

    public int playerMove(char[][] board) {
        return minimaxDecision(board, DEPTH, true, Integer.MIN_VALUE, Integer.MAX_VALUE).col;
    }
    public void setPiece(char piece) {
        this.piece = piece;
    }
    public char getPiece() {
        return this.piece;
    }
    
    private class Move {
        int score;
        int col;

        Move(int score, int col) {
            this.score = score;
            this.col = col;
        }
    }

    private Move minimaxDecision(char[][] board, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (depth == 0 || isTerminalNode(board)) {
            return new Move(evaluateBoard(board), -1);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            int bestCol = -1;
            for (int col = 0; col < 7; col++) {
                if (isValidMove(board, col)) {
                    char[][] newBoard = makeMove(board, col, piece);
                    int eval = minimaxDecision(newBoard, depth - 1, false, alpha, beta).score;
                    if (eval > maxEval) {
                        maxEval = eval;
                        bestCol = col;
                    }
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return new Move(maxEval, bestCol);
        } else {
            int minEval = Integer.MAX_VALUE;
            int bestCol = -1;
            char opponentPiece = (piece == 'R') ? 'B' : 'R';
            for (int col = 0; col < 7; col++) {
                if (isValidMove(board, col)) {
                    char[][] newBoard = makeMove(board, col, opponentPiece);
                    int eval = minimaxDecision(newBoard, depth - 1, true, alpha, beta).score;
                    if (eval < minEval) {
                        minEval = eval;
                        bestCol = col;
                    }
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return new Move(minEval, bestCol);
        }
    }

    private int evaluateBoard(char[][] board) {
        int score = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == piece) {
                    score += heuristicGrid[i][j];
                } else if (board[i][j] != '-') {
                    score -= heuristicGrid[i][j];
                }
            }
        }
        return score;
    }

    private boolean isValidMove(char[][] board, int col) {
        return board[0][col] == '-';
    }

    private char[][] makeMove(char[][] board, int col, char piece) {
        char[][] newBoard = copyBoard(board);
        for (int i = 5; i >= 0; i--) {
            if (newBoard[i][col] == '-') {
                newBoard[i][col] = piece;
                break;
            }
        }
        return newBoard;
    }

    private boolean isTerminalNode(char[][] board) {
        // Checks if the game is won or if the board is full
        return checkWinner(board) != '-' || isBoardFull(board);
    }

    private boolean isBoardFull(char[][] board) {
        for (int j = 0; j < 7; j++) {
            if (board[0][j] == '-') {
                return false;
            }
        }
        return true;
    }

    private char checkWinner(char[][] board) {
        // Horizontal, vertical, and diagonal checks for a winner
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != '-' &&
                        (checkDirection(board, i, j, 1, 0) ||
                        checkDirection(board, i, j, 0, 1) ||
                        checkDirection(board, i, j, 1, 1) ||
                        checkDirection(board, i, j, 1, -1))) {
                    return board[i][j];
                }
            }
        }
        return '-';
    }

    private boolean checkDirection(char[][] board, int row, int col, int rowDir, int colDir) {
        char start = board[row][col];
        for (int k = 1; k < 4; k++) {
            int r = row + k * rowDir;
            int c = col + k * colDir;
            if (r < 0 || r >= 6 || c < 0 || c >= 7 || board[r][c] != start) {
                return false;
            }
        }
        return true;
    }

    private char[][] copyBoard(char[][] board) {
        char[][] newBoard = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
}
