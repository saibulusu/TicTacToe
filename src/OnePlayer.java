import java.util.Scanner;

public class OnePlayer {

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

	// Convert the given position to String form
	public static String convertPosition(int row, int col) {
		String res = "";

		if (row == 0) {
			res += "t";
		} else if (row == 1) {
			res += "m";
		} else {
			res += "b";
		}

		if (col == 0) {
			res += "l";
		} else if (col == 1) {
			res += "m";
		} else {
			res += "r";
		}

		return res;
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

	// Check if the board results in a win
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

	// Check if there is a fork in the board
	public static boolean checkFork(int[][] table) {
		int count = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					table[r][c] = 2;
					if (checkWinningCombos(table)) {
						count++;
					}
					table[r][c] = 0;
				}
			}
		}

		return count > 1;
	}

	// Compute the best possible move at this point in the game
	public static String computeBestMove(int[][] table) {
		//If the whole board has only zeroes, then go to the top left corner
		int zeroCount = 0;

		//check if there is a one move win
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					table[r][c] = 2;
					if (checkWinningCombos(table)) {
						table[r][c] = 0;
						return convertPosition(r, c);
					}
					table[r][c] = 0;
					zeroCount++;
				}
			}
		}

		if (zeroCount == 9) {//if the board had 9 zeroes, then go to the top left corner
			return convertPosition(0, 0);
		}

		//check if there is a prevention from a one move loss
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					table[r][c] = 1;
					if (checkWinningCombos(table)) {
						table[r][c] = 0;
						return convertPosition(r, c);
					}
					table[r][c] = 0;
				}
			}
		}

		//check if a potential fork is possible
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					table[r][c] = 2;
					if (checkFork(table)) {
						table[r][c] = 0;
						return convertPosition(r, c);
					}
					table[r][c] = 0;
				}
			}
		}

		//check if it is possible to prevent a fork in one move
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					table[r][c] = 1;
					if (checkFork(table)) {
						table[r][c] = 0;
						return convertPosition(r, c);
					}
					table[r][c] = 0;
				}
			}
		}

		//go to the middle if possible
		if (table[1][1] == 0) {
			return convertPosition(1, 1);
		}

		//go to the opposite corner if possible
		if (table[0][0] == 1 && table[2][2] == 0) {
			return convertPosition(2, 2);
		}

		if (table[0][2] == 1 && table[2][0] == 0) {
			return convertPosition(2, 0);
		}

		if (table[2][2] == 1 && table[0][0] == 0) {
			return convertPosition(0, 0);
		}

		if (table[2][0] == 1 && table[0][2] == 0) {
			return convertPosition(0, 2);
		}

		//go to an empty corner if possible
		if (table[0][0] == 0) {
			return convertPosition(0, 0);
		}

		if (table[0][2] == 0) {
			return convertPosition(0, 2);
		}

		if (table[2][0] == 0) {
			return convertPosition(2, 0);
		}

		if (table[2][2] == 0) {
			return convertPosition(2, 2);
		}
		
		//go to an empty side if possible
		if (table[0][1] == 0) {
			return convertPosition(0, 1);
		}

		if (table[1][0] == 0) {
			return convertPosition(1, 0);
		}

		if (table[1][2] == 0) {
			return convertPosition(1, 2);
		}

		if (table[2][1] == 0) {
			return convertPosition(2, 1);
		}

		//this loop should never be reached
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (table[r][c] == 0) {
					//return any available move
					return convertPosition(r, c);
				}
			}
		}

		//this code should never be reached
		return convertPosition(0, 0);
	}

	// Play the game
	public static void play() {
		// Create a 3 x 3 board
		int[][] table = new int[3][3];

		// Initially set everything to 0
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = 0;
			}
		}

		// 1 for player 1
		// 2 for computer

		// In order to allow the computer to go first if necessary,
		// currentPlayer can start at 2
		System.out.println("Do you want to go first (yes/no)?");

		Scanner scan = new Scanner(System.in);

		String answer = scan.nextLine();

		int currentPlayer = 1;

		if (answer.equals("yes")) {
			currentPlayer = 1;
		} else {
			currentPlayer = 2;
		}

		displayOptions();

		System.out.println("Player " + currentPlayer + "'s turn");

		String input = "";

		// player 1 refers to a human in any case
		if (currentPlayer == 1) {
			displayBoard(table);
			input = scan.nextLine().toLowerCase();
		} else {
			input = computeBestMove(table);
		}

		boolean gameOver = false;

		//while the game is not over, the players can continue to make moves
		while (!gameOver) {
			//convert the given move to coordinate form
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

			if (table[row][col] == 0) {//if the desired position is 0, occupy that position
				table[row][col] = currentPlayer;

				if (checkWinningCombos(table)) {//if there is a winner
					//end the game
					gameOver = true;
					displayBoard(table);
					System.out.println("Game is over. Player " + currentPlayer + " wins");
					//break out of the while loop
					break;
				}

				//swap the current player
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

			//get the move of the new current player
			System.out.println("Player " + currentPlayer + "'s turn\n");
			if (currentPlayer == 1) {
				input = scan.nextLine().toLowerCase();
			} else {
				input = computeBestMove(table);
			}
		}
	}

	public static void main(String[] args) {
		play();
	}
}
