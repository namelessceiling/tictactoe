import java.util.Scanner;

public class Player {

    private Scanner scanner;
    private int xCoord;
    private int yCoord;
    private char symbol;
    private boolean turn;

    public Player(Scanner scanner, char symbol) {
        this.scanner = scanner;
        this.symbol = symbol;
        this.turn = false;
    }

    public void makeMove() {
        System.out.print("Enter the coordinates: ");
        while(!scanner.hasNextInt()) {
            System.out.println("You should enter numbers!");
            scanner.next();
        }
        xCoord = scanner.nextInt();
        yCoord = scanner.nextInt();
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean yourTurn() {
        return this.turn;
    }

    public void toggleTurn() {
        if(this.turn) {
            turn = false;
        } else {
            turn = true;
        }
    }
}
