package connect4_cvc;

public class Main_cvc {
    public static void main(String[] args) {
        ConnectFourPlayer_cvc redPlayer = new Team9_cvc(); // Team9 AI is always red cuz i want it too be lol
        ConnectFourPlayer_cvc blackPlayer = new TeamOpponent_cvc(); 
        ConnectFour_cvc game = new ConnectFour_cvc(redPlayer, blackPlayer);

        char winner = '-';
        int moves = 0;
        while (winner == '-' && !game.isBoardFull()) {
            game.displayBoard();
            if (moves % 2 == 0) {
                System.out.println("Red player's turn (AI)");
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
