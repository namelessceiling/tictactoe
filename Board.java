public class Board {

    private char[][] board;

    public Board() {
        this.board = new char[3][3];
    }

    public void createBoard() {
        System.out.print("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print(" |");
            for(int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                System.out.print("  ");
            }
            System.out.print("|");
        }
        System.out.println("---------");
    }

    public void printState() {
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

    public boolean winCondition(char c) {
        boolean win = false;
        for(int i = 0; i < 3; i++) {
            if(board[i][0] == c && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                win = true;
            }
            if(board[0][i] == c && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                win = true;
            }
        }
        return win;
    }

    public boolean diagonalWin(char c) {
        boolean win = false;
        if(board[1][1] == c) {
            if(board[0][0] == c && board[2][2] == c || board[0][2] == c && board[2][0] == c) {
                win = true;
            }
        }
        return win;
    }

    public boolean filled() {
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

    public boolean notFilled(int x, int y) {
        boolean filled = false;
        if(board[3 - y][x - 1] == ' ') {
            filled = true;
        }
        return filled;
    }

    public void fillSpot(int x, int y, char c) {
        board[x - 1][y - 1] = c;
    }
}
