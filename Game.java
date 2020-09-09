import java.util.Random;
import java.util.Scanner;

public class Game {

    private Board board;
    private Scanner scanner;
    private Random random;

    public Game(Board board, Scanner scanner, Random random) {
        this.board = board;
        this.scanner = scanner;
        this.random = random;
    }

    public void playerVSAI(Player p, AI easy) {
        if(p.getSymbol() == 'X') {
            p.toggleTurn();
        } else {
            easy.toggleTurn();
        }
        int x;
        int y;
        while(true) {
            if(p.yourTurn()) {
                p.makeMove();
                x = p.getXCoord();
                y = p.getYCoord();
            } else {
                easy.makeMove();
                x = easy.getXCoord();
                y = easy.getYCoord();
            }
            if(x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if(board.notFilled(x, y)) {
                if(p.yourTurn()) {
                    board.fillSpot(x, y, p.getSymbol());
                    p.toggleTurn();
                    easy.toggleTurn();
                } else {
                    board.fillSpot(x, y, easy.getSymbol());
                    p.toggleTurn();
                    easy.toggleTurn();
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
            boolean oWin = board.winCondition('O') || board.diagonalWin('O');
            boolean xWin = board.winCondition('X') || board.diagonalWin('X');
            board.printState();
            if(oWin) {
                System.out.println("O wins");
                break;
            }
            if(xWin) {
                System.out.println("X wins");
                break;
            }
            if(!oWin && !xWin && board.filled()) {
                System.out.println("Draw");
                break;
            }
        }
    }
    public void AIVsAI(AI first, AI second) {
        first.toggleTurn();
        int x;
        int y;
        while(true) {
            if(first.yourTurn()) {
                first.makeMove();
                x = first.getXCoord();
                y = first.getYCoord();
            } else {
                second.makeMove();
                x = second.getXCoord();
                y = second.getYCoord();
            }
            if(board.notFilled(x, y)) {
                if(first.yourTurn()) {
                    board.fillSpot(x, y, first.getSymbol());
                    first.toggleTurn();
                    second.toggleTurn();
                } else {
                    board.fillSpot(x, y, second.getSymbol());
                    first.toggleTurn();
                    second.toggleTurn();
                }
            } else {
                System.out.println("This cell is occupied, choose another one");
                continue;
            }
            boolean oWin = board.winCondition('O');
            boolean xWin = board.winCondition('X');
            board.printState();
            if(oWin) {
                System.out.println("O wins");
                break;
            }
            if(xWin) {
                System.out.println("X wins");
                break;
            }
            if(!oWin && !xWin && board.filled()) {
                System.out.println("Draw");
                break;
            }
        }
    }

    public void playerVsPlayer(Player first, Player second) {
        first.toggleTurn();
        int x;
        int y;
        while(true) {
            if(first.yourTurn()) {
                first.makeMove();
                x = first.getXCoord();
                y = first.getYCoord();
            } else {
                second.makeMove();
                x = second.getXCoord();
                y = second.getYCoord();
            }
            if(x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if(board.notFilled(x, y)) {
                if(first.yourTurn()) {
                    board.fillSpot(x, y, first.getSymbol());
                    first.toggleTurn();
                    second.toggleTurn();
                } else {
                    board.fillSpot(x, y, second.getSymbol());
                    first.toggleTurn();
                    second.toggleTurn();
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
            boolean oWin = board.winCondition('O') || board.diagonalWin('O');
            boolean xWin = board.winCondition('X') || board.diagonalWin('X');
            board.printState();
            if(oWin) {
                System.out.println("O wins");
                break;
            }
            if(xWin) {
                System.out.println("X wins");
                break;
            }
            if(!oWin && !xWin && board.filled()) {
                System.out.println("Draw");
                break;
            }
        }
    }

    public void start() {
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
                board.createBoard();
                if(parts[0].equals("start") && parts[1].equals("easy") && parts[2].equals("easy")) {
                    AI first = new AI(random, 'X');
                    AI second = new AI(random, 'O');
                    this.AIVsAI(first, second);
                } else if(parts[0].equals("start") && parts[1].equals("user") && parts[2].equals("user")) {
                    Player first = new Player(scanner, 'X');
                    Player second = new Player(scanner, 'O');
                    this.playerVsPlayer(first, second);
                } else if(parts[0].equals("start") && (parts[1].equals("user") || parts[1].equals("easy")) &&
                        (parts[2].equals("easy") || parts[2].equals("user"))) {
                    if(parts[1].equals("user")) {
                        Player player = new Player(scanner, 'X');
                        AI ai = new AI(random, 'O');
                        this.playerVSAI(player, ai);
                    } else {
                        AI mach = new AI(random, 'X');
                        Player user = new Player(scanner, 'O');
                        this.playerVSAI(user, mach);
                    }
                } else {
                    System.out.println("Bad parameters!");
                    continue;
                }
            }
        }
    }
}
