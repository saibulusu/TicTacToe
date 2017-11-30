
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

	public static String computeBestMove(int[][] table) {
		int[][] board = new int[3][3];

		int zeroCount = 0;

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				board[r][c] = table[r][c];
				if (board[r][c] == 0)
					zeroCount++;
			}
		}

		int rAnswer = -1;
		int cAnswer = -1;

		if (zeroCount == 2) {

			int firstR = 0;
			int firstC = 0;

			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					if (board[r][c] == 0)
						board[r][c] = 2;
					if (checkWinningCombos(board)) {
						rAnswer = r;
						cAnswer = c;
					}
					board[r][c] = 0;
					firstR = r;
					firstC = c;
				}
			}

			if (rAnswer == -1) {
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						if (rAnswer != -1 && board[r][c] == 0)
							board[r][c] = 1;
						if (checkWinningCombos(board)) {
							rAnswer = r;
							cAnswer = c;
						}
					}
				}
			}

			if (rAnswer == -1) {
				rAnswer = firstR;
				cAnswer = firstC;
			}
		} else if (zeroCount == 8) {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					if (board[r][c] == 1) {
						if (r == 0 && c == 0) { // corners
							rAnswer = 1;
							cAnswer = 2;
						} else if (r == 0 && c == 2) {
							rAnswer = 1;
							cAnswer = 0;
						} else if (r == 2 && c == 0) {
							rAnswer = 1;
							cAnswer = 2;
						} else if (r == 2 && c == 2) {
							rAnswer = 1;
							cAnswer = 0;
						} else if (r == 0 && c == 1) { // side
							rAnswer = 1;
							cAnswer = 1;
						} else if (r == 1) {
							if (c != 1) {
								rAnswer = 1;
								cAnswer = 1;
							}
						} else if (r == 2 && c == 1) {
							rAnswer = 1;
							cAnswer = 1;
						} else if (r == 1 && c == 1) { // middle
							rAnswer = 0;
							cAnswer = 0;
						}
					}
				}
			}
		} else if (zeroCount == 4) {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					if (board[r][c] == 0)
						board[r][c] = 2;
					if (checkWinningCombos(board)) {
						rAnswer = r;
						cAnswer = c;
					}
					board[r][c] = 0;
				}
			}

			if (rAnswer == -1) {
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						if (board[r][c] == 0)
							board[r][c] = 1;
						if (checkWinningCombos(board)) {
							rAnswer = r;
							cAnswer = c;
							System.out.println("testing point");
						}
						board[r][c] = 0;
					}
				}
			}

			if (rAnswer == -1) {
				rAnswer = 1;
				cAnswer = 1;
				System.out.println("testing point");
			}
		} else if (zeroCount == 6) {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					if (board[r][c] == 0)
						board[r][c] = 2;
					if (checkWinningCombos(board)) {
						rAnswer = r;
						cAnswer = c;
					}
					board[r][c] = 0;
				}
			}

			if (rAnswer == -1) {
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						if (board[r][c] == 0)
							board[r][c] = 1;
						if (checkWinningCombos(board)) {
							rAnswer = r;
							cAnswer = c;
						}
						board[r][c] = 0;
					}
				}
			}

			if (rAnswer == -1) {
				if (board[1][1] == 0) {
					rAnswer = 1;
					cAnswer = 1;
				} else if (board[0][0] == 1 && board[1][1] == 2 && board[1][2] == 1) {
					rAnswer = 2;
					cAnswer = 0;
				} else if (board[0][2] == 1 && board[1][0] == 1 && board[1][1] == 2) {
					rAnswer = 2;
					cAnswer = 2;
				} else if (board[2][2] == 1 && board[1][0] == 1 && board[1][1] == 2) {
					rAnswer = 0;
					cAnswer = 2;
				} else if (board[1][2] == 1 && board[2][0] == 1 && board[1][1] == 2) {
					rAnswer = 0;
					cAnswer = 0;
				} else if (board[1][0] == 1 && board[1][1] == 2 && board[1][2] == 2) {
					rAnswer = 0;
					cAnswer = 0;
				} else if (board[0][1] == 1 && board[1][0] == 1 && board[1][1] == 2) {
					rAnswer = 0;
					cAnswer = 0;
				} else if (board[0][1] == 1 && board[1][2] == 1 && board[1][1] == 2) {
					rAnswer = 0;
					cAnswer = 2;
				} else if (board[1][2] == 1 && board[2][1] == 1 && board[1][1] == 2) {
					rAnswer = 2;
					cAnswer = 2;
				} else if (board[1][0] == 1 && board[2][1] == 1 && board[1][1] == 2) {
					rAnswer = 2;
					cAnswer = 0;
				}
			}
		}

		String move = "";

		switch (rAnswer) {

		case 0:
			move += "t";
			break;

		case 1:
			move += "m";
			break;

		case 2:
			move += "b";
			break;

		default:
			move += "t";
			break;
		}

		switch (cAnswer) {

		case 0:
			move += "l";
			break;

		case 1:
			move += "m";
			break;

		case 2:
			move += "r";
			break;

		default:
			move += "l";
			break;
		}

		return move;
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
		// 2 for AI

		int currentPlayer = 1;

		displayOptions();

		Scanner scan = new Scanner(System.in);

		System.out.println("Player 1's turn");
		displayBoard(table);

		String input = scan.nextLine().toLowerCase();
		boolean gameOver = false;

		while (!gameOver) {
			if (input.equals("quit")) {
				gameOver = true;
			} else {
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
				input = computeBestMove(table);
				System.out.println(input);
			} else {
				input = scan.nextLine().toLowerCase();
			}
		}

	}

	public static void main(String[] args) {
		play();
	}

}
