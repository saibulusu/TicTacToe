import java.util.Scanner;

public class TwoPlayer {

	// The various options that are available
	public static void displayOptions() {
		System.out.println("These are the available commands :");
		System.out.println("\nquit");
		System.out.println("\ntl (top left)   tm (top middle)   tr (top right)");
		System.out.println("\nml (middle left)   mm (center)   mr (middle right)");
		System.out.println("\nbl (bottom left)   bm (bottom middle)   br (bottom right)");
	}

	// The current board configuration
	public static void displayBoard(int[][] table) {

		System.out.println(" " + table[0][0] + " | " + table[0][1] + " | " + table[0][2]);
		System.out.println("\n " + table[1][0] + " | " + table[1][1] + " | " + table[1][2]);
		System.out.println("\n " + table[2][0] + " | " + table[2][1] + " | " + table[2][2]);

		System.out.println("");
	}

	// If the current board is a draw
	public static boolean checkDraw(int[][] table) {
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				if (table[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// Check all of the winning combinations
	public static boolean checkWinningCombos(int[][] table) {
		if (table[0][0] != 0 && table[0][0] == table[0][1] && table[0][1] == table[0][2]) {
			return true;
		}
		if (table[1][0] != 0 && table[1][0] == table[1][1] && table[1][1] == table[1][2]) {
			return true;
		}
		if (table[2][0] != 0 && table[2][0] == table[2][1] && table[2][1] == table[2][2]) {
			return true;
		}

		if (table[0][0] != 0 && table[0][0] == table[1][0] && table[1][0] == table[2][0]) {
			return true;
		}
		if (table[0][1] != 0 && table[0][1] == table[1][1] && table[1][1] == table[2][1]) {
			return true;
		}
		if (table[0][2] != 0 && table[0][2] == table[1][2] && table[1][2] == table[2][2]) {
			return true;
		}

		if (table[0][0] != 0 && table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
			return true;
		}
		if (table[0][2] != 0 && table[0][2] == table[1][1] && table[1][1] == table[2][0]) {
			return true;
		}

		return false;
	}

	// Play the game
	public static void play() {
		// the table should be initialized to a 3 x 3 board
		int[][] table = new int[3][3];

		// Initially set everything to 0
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = 0;
			}
		}

		// 1 for player 1
		// 2 for player 2

		// the current player is 1, whoever wants to go first
		int currentPlayer = 1;

		displayOptions();

		Scanner scan = new Scanner(System.in);

		System.out.println("Player 1's turn");
		displayBoard(table);

		// player 1's move
		String input = scan.nextLine().toLowerCase();
		boolean gameOver = false;

		// while the game is not over, the players will continue to take turns
		// moving
		while (!gameOver) {

			//convert the given move to coordinate form, so that the board can take input
			char ROW = input.charAt(0);
			char COL = input.charAt(1);
			int row;
			int col;
			switch (ROW) {

			case 't':
				row = 0;
				break;

			case 'm':
				row = 1;
				break;

			case 'b':
				row = 2;
				break;

			default:
				row = 0;
				break;

			}

			switch (COL) {

			case 'l':
				col = 0;
				break;

			case 'm':
				col = 1;
				break;

			case 'r':
				col = 2;
				break;

			default:
				col = 0;
				break;
			}


			if (table[row][col] == 0) {//if the desired position is 0, then the move is valid
				//set the desired position to that current player
				table[row][col] = currentPlayer;

				//if there is a win
				if (checkWinningCombos(table)) {
					//end the game
					gameOver = true;
					displayBoard(table);
					System.out.println("Game is over. Player " + currentPlayer + " wins");
					//break out of loop
					break;
				}

				//swap current player
				if (currentPlayer == 1) {
					currentPlayer++;
				} else {
					currentPlayer--;
				}
			} else {//else make a different move
				System.out.println("Please make a different move");
			}
			System.out.println("");
			displayBoard(table);

			if (checkDraw(table)) {//if there is a draw, the game is over
				System.out.println("Draw");
				break;
			}

			//let the next player move
			System.out.println("Player " + currentPlayer + "'s turn\n");
			input = scan.nextLine().toLowerCase();
		}
	}

	public static void main(String[] args) {
		play();
	}
}
