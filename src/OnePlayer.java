
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnePlayer {

	public static void displayOptions() {
		System.out.println("These are the available commands :");
		System.out.println("\nquit");
		System.out.println("\ntl (top left)   tm (top middle)   tr (top right)");
		System.out.println("\nml (middle left)   mm (center)   mr (middle right)");
		System.out.println("\nbl (bottom left)   bm (bottom middle)   br (bottom right)");
	}

	public static void displayBoard(int[][] table) {
		System.out.println(" " + table[0][0] + " | " + table[0][1] + " | " + table[0][2]);
		System.out.println("\n " + table[1][0] + " | " + table[1][1] + " | " + table[1][2]);
		System.out.println("\n " + table[2][0] + " | " + table[2][1] + " | " + table[2][2]);

		System.out.println("");
	}

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

	public static int[] rowcolconvert(String input) {
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

		int[] arr = { row, col };
		return arr;
	}

	public static void play() {

		int[][] table = new int[3][3];

		// Initially set everything to 0
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = 0;
			}
		}

		// 1 for player 1
		// 2 for Engine

		int currentPlayer = 1;

		Scanner scan = new Scanner(System.in);

		System.out.println("Player 1's turn");
		displayBoard(table);

		String input = scan.nextLine().toLowerCase();
		boolean gameOver = false;

		while (!gameOver) {

			if (input.equals("quit")) {
				gameOver = true;
			} else {

				int row = rowcolconvert(input)[0];
				int col = rowcolconvert(input)[1];

				if (table[row][col] == 0) {
					table[row][col] = currentPlayer;

					if (checkWinningCombos(table)) {
						gameOver = true;
						System.out.println("Game is over. Player " + currentPlayer + " wins");
						break;
					}

					if (currentPlayer == 1) {
						currentPlayer++;
					} else {
						currentPlayer--;
					}
				} else {
					System.out.println("Please make a different move");
				}
			}
			System.out.println("");
			displayBoard(table);

			if (checkDraw(table)) {
				System.out.println("Draw");
				break;
			}

			System.out.println("Player " + currentPlayer + "'s turn\n");
			if (currentPlayer == 2) {
				Tree tree = new Tree(table);
				String computeInput = tree.compute(table, currentPlayer);
				System.out.println(computeInput);
				input = computeInput.toLowerCase();
			} else {
				input = scan.nextLine().toLowerCase();
			}
		}
	}

	public static void main(String[] args) {
		play();
	}

}
