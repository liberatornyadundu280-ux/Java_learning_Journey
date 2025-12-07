1import java.util.Scanner;   
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
        if (board[i*3].equals(player) && board[i*3+1].equals(player) && board[i*3+2].equals(player)) {
            return true;
        }
        if (board[i].equals(player) && board[i+3].equals(player) && board[i+6].equals(player)) {
            return true;
        }
    }
    if (board[0].equals(player) && board[4].equals(player) && board[8].equals(player)) {
        return true;
    }
    if (board[2].equals(player) && board[4].equals(player) && board[6].equals(player)) {
        return true;
    }
    return false;
}  

public boolean taken_move(String[] board, int move){
    if(board[move-1].equals("X") || board[move-1].equals("O")){
        System.out.println("This position is already taken. Please choose another position.");
        return false;
    }
    if(move < 1 || move > 9){
        System.out.println("Invalid move. Please enter a number between 1 and 9.");
        return false;
    }   
    return true;
}

public int getValidMove(Scanner sc, String[] board) {
    // This loop will not stop until it gets a valid, usable move.
    while (true) {
        System.out.println("Enter your move (1-9) or -1 to quit:");
        int move;

        // --- Check 1: Is it an integer? ---
        try {
            move = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a *number*.");
            sc.next(); // CRITICAL: Clear the bad input
            continue;  // Re-start this validation loop
        }

        // --- Check 2: Is it the quit command? ---
        if (move == -1) {
            return -1; // It's a valid "quit" command
        }
        // --- Check 3: Is the move valid and not taken? ---
        if (taken_move(board, move)) {
            return move;
        }
    }
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
            System.out.println((turns % 2 == 0 ? "Player 1's turn (X)" : "Player 2's turn (O)"));       
        int move = game.getValidMove(sc, board);
            if(move == -1){
                System.out.println("Game ended by user.");
                break;
            }
            board[move-1]= (turns % 2 == 0 ? player1 : player2);
            game.printBoard(board);
            if(game.check(board, (turns % 2 == 0 ? player1 : player2))){
                System.out.println((turns % 2 == 0 ? "Player 1 wins!" : "Player 2 wins!"));
                break;
            }
            turns++;
            if(turns == 9){
                System.out.println("It's a draw!");
                break;
            }
        }
         sc.close();
    }
}