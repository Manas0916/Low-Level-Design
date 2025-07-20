package TicTacToe;
import java.util.Scanner;
public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String piece1 = scanner.next();
        String player1 = scanner.next();
        String piece2 = scanner.next();
        String player2 = scanner.next();
        
        TicTacToe game = (piece1.equals("X")) ? new TicTacToe(player1, player2) : new TicTacToe(player2, player1);
        game.printBoard();
        
        while (scanner.hasNext()) {
            if (scanner.hasNext("exit")) break;
            
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            
            if (!game.isGameOver()) {`
                game.makeMove(row, col);
            }
        }
        
        scanner.close();
    }
}