package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String s = scanner.nextLine();
        char[][] arr = new char[3][3];
        createBoard(arr);
        boolean player = false;
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
            if (xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (arr[3 - yCoord][xCoord - 1] == ' ') {
                if(!player) {
                    arr[3 - yCoord][xCoord - 1] = 'X';
                    player = true;
                } else {
                    arr[3 - yCoord][xCoord - 1] = 'O';
                    player = false;
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
            boolean oWin = winCondition(arr, 'O') || diagonalWin(arr, 'O');
            boolean xWin = winCondition(arr, 'X') || diagonalWin(arr, 'X');
            boolean filled = filled(arr);
            printState(arr);
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
    
    public static boolean diagonalWin(char[][] board, char n) {
        boolean win = false;
        if(board[1][1] == n) {
            if(board[0][0] == n && board[2][2] == n || board[0][2] == n && board[2][0] == n) {
                win = true;
            }
        }
        return win;
    }
    
    public static void createBoard(char[][] board) {
        int counter = 0;
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                System.out.print("  ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
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
}