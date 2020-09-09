import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Board board = new Board();
        Game game = new Game(board, scanner, random);
        game.start();
    }
}
