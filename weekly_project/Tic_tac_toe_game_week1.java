import java.util.Scanner;   
public class Tic_tac_toe_game_week1 {
    void printBoard(String[] board) {
    System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
    System.out.println("---+---+---");
    System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
    System.out.println("---+---+---");
    System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
}
public boolean check(String[] board, String player) {
    // Check rows, columns, and diagonals for a win
    for (int i = 0; i < 3; i++) {
        if (board[i*3].equals(board[i*3+1]) && board[i*3+1].equals(board[i*3+2])) {
            return true;
        }
        if (board[i].equals(board[i+3]) && board[i+3].equals(board[i+6])) {
            return true;
        }
    }
    if (board[0].equals(board[4]) && board[4].equals(board[8])) {
        return true;
    }
    if (board[2].equals(board[4]) && board[4].equals(board[6])) {
        return true;
    }
    for(String cell : board){
        if(!cell.equals("X") && !cell.equals("O")){
            return false;
        }
    }
    System.out.println("It's a draw!");
    return false;
}   
public boolean taken_move(String[] board, int move){
    if(board[move-1].equals("X") || board[move-1].equals("O")){
        System.out.println("This position is already taken. Please choose another position.");
        return false;
    }
    return true;
}
        public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1: X");
        System.out.println("Player 2: O");
        System.out.println("To make a move, enter the position you want to play at and make sure it not already taken ie not \"X\" or \"O\".");
        System.out.println("For example, to place your mark in the position 1, you would enter 1.");
        System.out.println("Let's start the game!");
        String[] board = {"1","2","3","4","5","6","7","8","9"};
        Tic_tac_toe_game_week1 game = new Tic_tac_toe_game_week1();
        game.printBoard(board);
        String player1 = "X";
        String player2 = "O";
        int turns = 0;
        Scanner sc = new Scanner(System.in); 
        while(true){
            // Game logic to be implemented
            System.out.println((turns % 2 == 0 ? "Player 1's turn" : "Player 2's turn"));
            System.out.println("to quit the game, press -1");
            turns++;
            int move=sc.nextInt();
            if(move == -1){
                System.out.println("Game ended by user.");
                break;
            }
            while(!game.taken_move(board, move)){
                move=sc.nextInt();
            }
            board[move-1]= (turns % 2 == 0 ? player2 : player1);
            game.printBoard(board);
            if(game.check(board, (turns % 2 == 0 ? player2 : player1))){
                System.out.println((turns % 2 == 0 ? "Player 2 wins!" : "Player 1 wins!"));
                break;
            }
            if(turns == 9){
                System.out.println("It's a draw!");
                break;
            }
        }
        sc.close();
}
}
