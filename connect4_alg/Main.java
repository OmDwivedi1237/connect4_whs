package connect4_alg;

public class Main {
    public static void main(String[] args) {
        ConnectFourPlayer redPlayer = new Team9();
        ConnectFourPlayer blackPlayer = new TeamOpponent(); 
        ConnectFour game = new ConnectFour(redPlayer, blackPlayer);

        char winner = '-';
        int moves = 0;
        while (winner == '-' && !game.isBoardFull()) {
            game.displayBoard();
            if (moves % 2 == 0) {
                System.out.println("Red player's turn");
                game.makeMove(redPlayer, 'R');
            } else {
                System.out.println("Black player's turn");
                game.makeMove(blackPlayer, 'B');
            }
            winner = game.checkWinner();
            moves++;
        }
        game.displayBoard();
        if (winner == '-') {
            System.out.println("It's a draw!");
        } else if (winner == 'R') {
            System.out.println("Red player wins!!!!!!!!!!");
        } else {
            System.out.println("Black player wins!!!!!!!!!!!");
        }
    }
}


