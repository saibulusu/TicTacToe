import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MessageListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "You clicked the button");
	}
}

public class TicTacToe {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1000, 1000));
		frame.setTitle("A Frame");

		frame.setLayout(new GridLayout(3, 3));

		for (int i = 1; i <= 9; i++) {
			frame.add(new JButton("Button " + i));
		}

		frame.pack();
		frame.setVisible(true);
	}
}
