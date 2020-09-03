import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
  
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();
        char[][] board = new char[3][3];
        createState(board, cells);
        int numX = numOfChars(cells, 'X');
        int numO = numOfChars(cells, 'O');
        while(true) {
            System.out.print("Enter the coordinates: ");
            while(!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
            int xCoord = scanner.nextInt();
            int yCoord = scanner.nextInt();
            if (xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (board[3 - yCoord][xCoord - 1] == ' ') {
                if(numX > numO) {
                    board[3 - yCoord][xCoord - 1] = 'O';
                } else {
                    board[3 - yCoord][xCoord - 1] = 'X';
                }
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
        printState(board);
        boolean oWin = winCondition(board, 'O') || diagonalWin(board, 'O');
        boolean xWin = winCondition(board, 'X') || diagonalWin(board, 'X');
        boolean filled = filled(board);
        if(oWin) {
            System.out.println("O wins");
        } else if(xWin) {
            System.out.println("X wins");
        } else if(!oWin && !xWin && filled) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        } 
    }
    
    public static void createState(char[][] board, String moves) {
        int counter = 0;
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (moves.charAt(counter) == '_') {
                    board[i][j] = ' ';
                    System.out.print("  ");
                } else {
                    board[i][j] = moves.charAt(counter);
                    System.out.print(board[i][j] + " ");
                }
                counter++;
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
    
    public static boolean winCondition (char[][] board, char n) {
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    filled = false;
                }
            }
        }
        return filled;
    }
    
    public static int numOfChars(String s, char c) {
        int num = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                num++;
            }
        }
        return num;
    }
}
