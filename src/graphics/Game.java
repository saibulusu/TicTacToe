package graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {

	static int current = 1;
	static int[][] board;
	
	static int r;
	static int c;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 500));

		frame.setLocation(600, 300);

		frame.setLayout(new GridLayout(3, 3));

		current = 1;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "X");
		map.put(2, "O");

		board = new int[3][3];

		ArrayList<JButton> buttons = new ArrayList<JButton>();

		boolean keepPlaying = true;

		for (r = 0; r <= 2; r++) {
			for (c = 0; c <= 2; c++) {
				JButton button = new JButton();
				buttons.add(button);
				button.setFont(new Font("Arial", Font.PLAIN, 0));
				button.setText(r + " " + c);
				button.setPreferredSize(new Dimension(100, 100));
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!button.getText().equals("X") && !button.equals("O")) {
							button.setFont(new Font("Arial", Font.PLAIN, 40));
							board[Integer.parseInt(button.getText().split(" ")[0])][Integer
									.parseInt(button.getText().split(" ")[1])] = current;
							button.setText(map.get(current));
							button.setEnabled(false);
							if (checkWinningCombos(board)) {
								JOptionPane.showMessageDialog(null, "player " + map.get(current) + " wins");
								for (int j = 0; j < buttons.size(); j++) {
									buttons.get(j).setEnabled(false);
								}
								int choice = JOptionPane.showConfirmDialog(null, "Play again?");
								if (choice == JOptionPane.YES_OPTION) {
									frame.dispose();
									main(args);
								} else {
									frame.dispose();
								}
							} else if (checkDraw(board)) {
								JOptionPane.showMessageDialog(null, "draw");
								int choice = JOptionPane.showConfirmDialog(null, "Play again?");
								if (choice == JOptionPane.YES_OPTION) {
									frame.dispose();
									main(args);
								} else {
									frame.dispose();
								}
							}
							current = 3 - current;
						}
					}
				});
				frame.add(button);
			}

			frame.pack();
		}
	}

	static void printBoard(int[][] board) {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
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
}
