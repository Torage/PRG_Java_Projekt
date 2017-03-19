import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	// Erzeugen der JButtons
	private JButton division = new JButton("Division");
	private JButton fracture = new JButton("Kürzen von Brüchen");
	private JButton percentage = new JButton("Prozentrechnung");

	public MainGUI() {
		// Frame Settings
		setTitle("Mathetrainer");
		setLayout(new GridLayout(3, 1));

		// Hinzufügen der Buttons in das frame
		add(division);
		add(fracture);
		add(percentage);

		// Frame anzeigen lassen
		setSize(275, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ButtonListener
		division.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Division();
				dispose();
			}
		});

		fracture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Fracture();
				dispose();
			}
		});

		percentage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Percentage();
				dispose();
			}
		});

	}

	public static void main(String[] args) {
		new MainGUI();
	}

}
