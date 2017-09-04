
import java.util.ArrayList;
import java.util.List;

public class Tree {

	private Tree[][] branches = new Tree[3][3];
	private int[][] heights = new int[3][3];
	private int[][] board = new int[3][3];

	private int height;

	public Tree(int[][] board) {
		this.board = board;
		this.height = 0;

		for (int row = 0; row < heights.length; row++) {
			for (int col = 0; col < heights[0].length; col++) {
				heights[row][col] = 0;
			}
		}
	}

	public static String intToRowCol(int row, int col) {
		String ROW = "";
		if (row == 0) {
			ROW = "t";
		} else if (row == 1) {
			ROW = "m";
		} else {
			ROW = "b";
		}

		String COL = "";
		if (col == 0) {
			COL = "l";
		} else if (col == 1) {
			COL = "m";
		} else {
			COL = "r";
		}

		return ROW + "" + COL;
	}

	// Still need to make sure that the final board results in a victory for
	// player 2
	public String compute(int[][] table, int currentplayer) {

		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table.length; col++) {
				int[][] copy = table;
				if (copy[row][col] == 0) {
					copy[row][col] = currentplayer;
					branches[row][col] = new Tree(copy);
					heights[row][col]++;
					if (currentplayer == 2) {
						currentplayer = 1;
					} else {
						currentplayer = 2;
					}
					compute(copy, currentplayer);
				} else {
					heights[row][col] = 0;
				}
			}
		}

		int minheight = 1;

		for (int row = 0; row < heights.length; row++) {
			for (int col = 0; col < heights[row].length; col++) {
				if (heights[row][col] != 0) {
					minheight = Math.min(minheight, heights[row][col]);
				}
			}
		}

		String output = "";

		for (int row = 0; row < heights.length; row++) {
			for (int col = 0; col < heights[row].length; col++) {
				if (minheight == heights[row][col]) {
					output = intToRowCol(row, col);
				}
			}
		}

		return output;
	}
}
