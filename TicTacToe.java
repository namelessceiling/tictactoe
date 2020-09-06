import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
  
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char[][] board = new char[3][3];
        boolean player = true;
        while(true) {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            if(command.equals("exit")) {
                break;
            } else {
                String[] parts = command.split(" ");
                if(parts.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
            	 }
                createBoard(board);
                if(parts[0].equals("start") && parts[1].equals("easy") && parts[2].equals("easy")) {
                    AIVsAI(board, scanner, player, random);
                } else if(parts[0].equals("start") && parts[1].equals("user") && parts[2].equals("user")) {
                    userVsUser(board, scanner, player, random);
                } else if(parts[0].equals("start") && parts[1].equals("user") && parts[2].equals("easy")) {
                    playerVsAI(board, scanner, player, random, 'X');
                } else if(parts[0].equals("start") && parts[1].equals("easy") && parts[2].equals("user")) {
                    player = false;
                    playerVsAI(board, scanner, player, random, 'O');
                } else {
                    System.out.println("Bad parameters!");
                    continue;
                }
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
    
    public static void playerVsAI(char[][] board, Scanner scanner, boolean player, Random random, char c) {
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
            if (xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (board[3 - yCoord][xCoord - 1] == ' ') {
                if(player) {
                    board[3 - yCoord][xCoord - 1] = c;
                    player = false;
                } else if(!player && c == 'X'){
                    board[3 - yCoord][xCoord - 1] = 'O';
                    player = true;
                } else {
                    board[3 - yCoord][xCoord - 1] = 'X';
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
    
    public static void userVsUser(char[][] board, Scanner scanner, boolean player, Random random) {
        int xCoord;
        int yCoord;
        while(true) {
            System.out.print("Enter the coordinates: ");
            while(!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
             }
            xCoord = scanner.nextInt();
            yCoord = scanner.nextInt();
            if(xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (board[3 - yCoord][xCoord - 1] == ' ') {
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
    
    public static void AIVsAI(char[][] board, Scanner scanner, boolean player, Random random) {
        int xCoord;
        int yCoord;
        while(true) {
            System.out.println("Making move level \"easy\"");
            xCoord = random.nextInt(3) + 1;
            yCoord = random.nextInt(3) + 1;
            if(board[3 - yCoord][xCoord - 1] == ' ') {
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
}
