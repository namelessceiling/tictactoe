import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
  
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char[][] board = new char[3][3];
        createBoard(board);
        boolean player = true;
        int xCoord;
        int yCoord;
        while(true) {
            if(player) {
                System.out.print("Enter the coordinates: ");
                while(!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                }
                xCoord = scanner.nextInt();
                yCoord = scanner.nextInt();
            } else {
                System.out.println("Making move level \"easy\"");
                xCoord = random.nextInt(3) + 1;
                yCoord = random.nextInt(3) + 1;
            }
            if(xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if(board[3 - yCoord][xCoord - 1] == ' ') {
                if(player) {
                    board[3 - yCoord][xCoord - 1] = 'X';
                    player = false;
                } else {
                    board[3 - yCoord][xCoord - 1] = 'O';
                    player = true;
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
            boolean oWin = winCondition(board, 'O') || diagonalWin(board, 'O');
            boolean xWin = winCondition(board, 'X') || diagonalWin(board, 'X');
            boolean filled = filled(board);
            printState(board);
            if(xWin) {
            	System.out.println("X wins");
            	break;
            }
            if(oWin) {
            	System.out.println("O wins");
            	break;
            }
            if(!oWin && !xWin && filled) {
            	System.out.println("Draw");
            	break;
            }
        }
    }
    
    public static void createBoard(char[][] board) {
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
            	board[i][j] = ' ';
            	System.out.print("  ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    
    public static void printState(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    
    public static boolean winCondition(char[][] board, char n) {
        boolean win = false;
        for(int i = 0; i < 3; i++) {
            if((board[i][0] == board[i][1] && board[i][0] == board[i][2])) {
                if(board[i][0] == n) {
                    win = true;
                } 
            }
            if((board[0][i] == board[1][i] && board[0][i] == board[2][i])) {
                if(board[0][i] == n) {
                    win = true;
                }
            }
        }
        return win;
    }
    
    public static boolean diagonalWin(char[][] board, char c) {
        boolean win = false;
        if(board[1][1] == c) {
            if(board[0][0] == c && board[2][2] == c || board[0][2] == c && board[2][0] == c) {
                win = true;
            }
        }
        return win;
    }
    
    public static boolean filled(char[][] board) {
        boolean filled = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    filled = false;
                }
            }
        }
        return filled;
    }
}
