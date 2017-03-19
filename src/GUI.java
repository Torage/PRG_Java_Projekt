import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	// Universal Parameter
	private final static Color COLOR = new Color(225, 225, 225);
	private final static String[] RANGE = { "Einstellig", "Zweistellig", "Dreistellig", "Vierstellig", "Fünfstellig" };
	private final static String[] CALCULATE_PERCENTAGE = { "normaler Anteil", "Zunahme", "Abnahme" };

	public GUI(String title) {

		super(title);
		setLayout(new BorderLayout());
		setSize(650, 400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {
				NumField.setMaxRange(5);
				new MainGUI();
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});

	}

	public static Color getCOLOR() {
		return COLOR;
	}

	public static String[] getRange() {
		return RANGE;
	}

	public static String[] getCalculatePercentage() {
		return CALCULATE_PERCENTAGE;
	}

}
