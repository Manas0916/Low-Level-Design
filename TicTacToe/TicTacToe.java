package TicTacToe;

import java.util.*;
class TicTacToe {

    private char[][] board;
    private String playerX, playerO;
    private char currentPlayer;
    private boolean gameWon;
    private int movesCount;

    public TicTacToe(String playerX, String playerO) {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], '-');
        }
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = 'X';
        this.gameWon = false;
        this.movesCount = 0;
    }
    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 1 || row > 3 || col < 1 || col > 3 || board[row - 1][col - 1] != '-') {
            System.out.println("Invalid Move");
            return false;
        }
        
        board[row - 1][col - 1] = currentPlayer;
        movesCount++;
        printBoard();
        
        if (checkWin(row - 1, col - 1)) {
            System.out.println(getCurrentPlayerName() + " won the game");
            gameWon = true;
        } else if (movesCount == 9) {
            System.out.println("Game Over");
            gameWon = true;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;
    }

    private boolean checkWin(int row, int col) {
        char symbol = board[row][col];
        return (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) ||
               (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) ||
               (row == col && board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (row + col == 2 && board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private String getCurrentPlayerName() {
        return (currentPlayer == 'X') ? playerX : playerO;
    }

    public boolean isGameOver() {
        return gameWon;
    }
}