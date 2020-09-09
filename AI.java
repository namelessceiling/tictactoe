import java.util.Random;

public class AI {

    private Random random;
    private int xCoord;
    private int yCoord;
    private char symbol;
    private boolean turn;

    public AI(Random random, char symbol) {
        this.random = random;
        this.symbol = symbol;
        turn = false;
    }

    public void makeMove() {
        System.out.println("Making move level \"easy\"");
        xCoord = random.nextInt(3) + 1;
        yCoord = random.nextInt(3) + 1;
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
