import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
public class The_Memory_game {
    private static final Scanner scanner = new Scanner(System.in);
    // main method
    public static void main(String[] args) {
        try
        {
        String[] board = {"A", "B", "C", "D","A", "B", "C", "D"};
        String[] initializer ={" ", " ", " ", " ", " ", " ", " ", " "};
        String player1="Player 1";
        String player2="Player 2";
        The_Memory_game game = new The_Memory_game();
        int counter=0;
        int turn=0;
        int player1score=0;
        int player2score=0;
        Collections.shuffle(Arrays.asList(board)); // shuffle the board every time the game starts
        // welcome message
        System.out.println("Welcome to the Memory Game!");
        System.out.println("Match all pairs to win. Enter -1 at any time to quit.");
        System.out.println("Board positions are numbered 1 to 8.");
        System.out.println("Let's start the game!");


        try{
            System.out.print("Enter name for Player 1: ");
            player1 = scanner.nextLine();
            System.out.print("Enter name for Player 2: ");
            player2 = scanner.nextLine();
        }
        catch(Exception e){
            System.out.println("Error reading player names. Using default names.");
        }

        while(counter<4)
        {
            if(turn%2==0){
                System.out.println(player1 + "'s turn");
            }
            else{
                System.out.println(player2 + "'s turn");
            }
            game.generateboard(initializer);
            System.out.print("Enter the first position to flip (1-8) or enter -1 to quit: ");
            int firstPos = game.validate_Input() - 1;

            if (firstPos == -2) { // since user enters -1, subtracting 1 makes it -2
                System.out.println("Game quit by:  "+ (turn%2==0 ? player1 : player2));
                return;
            }
            initializer[firstPos]=board[firstPos];
            game.generateboard(initializer);
            initializer[firstPos]=" "; // hide again for next input
            System.out.print("Enter the second position to flip (1-8) or enter -1 to quit: ");
            int secondPos = game.validate_Input() - 1;

            if (secondPos == -2) { // since user enters -1, subtracting 1 makes it -2
                System.out.println("Game quit by:  "+ (turn%2==0 ? player1 : player2));
                return;
            }

            if(initializer[firstPos].equals(board[firstPos]) || initializer[secondPos].equals(board[secondPos])){
                System.out.println("Position already matched. Choose different positions.");
                continue;
            }
            
            if(firstPos==secondPos){
                System.out.println("You selected the same position twice. Choose different positions.");
                continue;
            }

            if(board[firstPos].equals(board[secondPos])){
                System.out.println("It's a match!");
                initializer[firstPos] = board[firstPos];
                initializer[secondPos] = board[secondPos];
                if(turn%2==0){
                    player1score++;
                }
                else{
                    player2score++;
                }
                counter++;
                game.generateboard(initializer);
            }
            else{
                System.out.println("Not a match. Try again.");
            char op1=board[firstPos].charAt(0);
            char op2=board[secondPos].charAt(0);
            initializer[firstPos]=String.valueOf(op1);
            initializer[secondPos]=String.valueOf(op2);
            game.generateboard(initializer);
            initializer[firstPos]=" ";
            initializer[secondPos]=" ";
            System.out.print("Press Enter to continue...");
            scanner.nextLine(); // consume leftover newline
            scanner.nextLine(); // wait for user to press enter
            }
            turn++;
        // skipping multiple lines for better readability
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        game.decide_Winner(player1score, player2score, player1, player2); 
        System.out.println("Game over! All pairs have been found.");}

        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }


    int validate_Input(){
        while(true){
            int pos;
            // checking if input is integer
            while(!scanner.hasNextInt()){
                System.out.print("Invalid input. Please enter a position between 1 and 8: ");
                scanner.next(); // clear the invalid input
            }
            pos = scanner.nextInt();
            if(pos==-1){
                return -1; // to indicate quitting
            }
            if(pos<1 || pos>8){
                System.out.print("Invalid input. Please enter a position between 1 and 8: ");
            }
            else{
             return pos;
            }
        }
    }


    void decide_Winner(int player1score, int player2score, String player1, String player2){
        System.out.println("Scores:");
        System.out.println(player1 + ": " + player1score);
        System.out.println(player2 + ": " + player2score);
        if(player1score>player2score){
            System.out.println(player1 + " wins!");
        }
        else if(player2score>player1score){
            System.out.println(player2 + " wins!");
        }
        else{
            System.out.println("It's a tie!");
        }
    }

    void generateboard(String[] board) { 
    final int CELL_WIDTH = 3; 

    // 1. Prepare the separator line
    String separatorPart = String.format("%" + CELL_WIDTH + "s", "").replace(' ', '-');
    String separatorLine = "|";
    for (int i = 0; i < board.length; i++) {
        separatorLine += separatorPart + "|";
    }

    System.out.println(separatorLine);
    
    // 2. Print the position numbers (Left aligned numbers)
    System.out.print("|");
    for (int i = 0; i < board.length; i++) {
        // %-3d ensures the number is left-aligned in a width of 3
        System.out.printf("%-" + (CELL_WIDTH - 1) + "d |", i + 1); 
    }
    System.out.println();
    
    System.out.println(separatorLine);
    
    // 3. Print the actual board contents (Centering Logic Applied)
    System.out.print("|");
    for (String card : board) {
        // Calculate total padding needed
        int totalPadding = CELL_WIDTH - card.length();
        // Determine left and right padding for approximate centering
        int leftPad = totalPadding / 2;
        int rightPad = totalPadding - leftPad;

        // Use standard Java printing with spaces
        System.out.print(String.format("%" + leftPad + "s", "") 
                         + card 
                         + String.format("%" + rightPad + "s", "") + "|");
    }
    System.out.println();
    
    System.out.println(separatorLine);
}
}