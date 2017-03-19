import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Fracture {

	private GUI fracture = new GUI("Kürzen von Brüchen");

	private final int WIDTH_OUTER = fracture.getWidth() / 4;
	private final int WIDTH_INNER = WIDTH_OUTER / 3;
	private final String LBLRESULT_WRONG = "Falsch gekürzt!";
	private final String LBLRESULT_RIGHT = "Richtig gekürzt!";
	private final String LBLRESULT_START = "";
	private final String BTNEQUALS_START = "OK";
	private final String BTNEQUALS_RESULT = "=";
	private final String BTNEQUALS_RETRY = "<<";

	private final int TASK = 0;
	private final int RESULT = 1;

	private JSeparator breakingDash = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator breakingDashResult = new JSeparator(SwingConstants.HORIZONTAL);

	private JPanel northPanel = new JPanel();
	private JPanel leftBoxPanel = new JPanel();
	private JPanel rightBoxPanel = new JPanel();
	private JPanel centerBoxPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel centerPanelN = new JPanel();
	private JPanel centerPanelS = new JPanel();

	private DescriptionField descriptionField = new DescriptionField();
	private ScrollPane sp = new ScrollPane(descriptionField);

	private NumField numerator = new NumField(this, "Zähler", NumField.CENTER);
	private NumField denominator = new NumField(this, "Nenner", NumField.CENTER);
	private NumField numeratorResult = new NumField(this, "Zähler", NumField.CENTER);
	private NumField denominatorResult = new NumField(this, "Nenner", NumField.CENTER);
	private static ArrayList<NumField> fieldList = new ArrayList<>();

	private JButton btnEquals = new JButton(BTNEQUALS_START);
	private JButton btnRNG = new JButton("Zufall");
	private JButton btnReset = new JButton("Reset");
	private JButton btnResult = new JButton("Lösung");

	private JLabel lblResult = new JLabel(LBLRESULT_START);
	private JLabel lblRange = new JLabel("Wertebereich");

	private RNG rand = new RNG();

	private final String[] strRange = { "Einstellig", "Zweistellig", "Dreistellig", "Vierstellig", "Fünfstellig" };

	private JComboBox<String> rangeBox = new JComboBox<>(strRange);

	public Fracture() {
		// Befüllung der NumField liste
		fieldList.add(numerator);
		fieldList.add(denominator);
		fieldList.add(numeratorResult);
		fieldList.add(denominatorResult);

		// Farbe der Seperator ändern um einem Bruchstrich zu ähneln
		breakingDash.setBackground(Color.BLACK);
		breakingDashResult.setBackground(Color.BLACK);

		// Panels bestücken und definieren
		leftBoxPanel.setBackground(GUI.getCOLOR());
		leftBoxPanel.setLayout(new BoxLayout(leftBoxPanel, BoxLayout.Y_AXIS));
		leftBoxPanel.add(Box.createVerticalStrut(10));
		leftBoxPanel.add(numerator);
		leftBoxPanel.add(Box.createVerticalStrut(3));
		leftBoxPanel.add(breakingDash);
		leftBoxPanel.add(Box.createVerticalStrut(4));
		leftBoxPanel.add(denominator);
		leftBoxPanel.add(Box.createVerticalStrut(10));

		rightBoxPanel.setBackground(GUI.getCOLOR());
		rightBoxPanel.setLayout(new BoxLayout(rightBoxPanel, BoxLayout.Y_AXIS));
		rightBoxPanel.add(Box.createVerticalStrut(10));
		rightBoxPanel.add(numeratorResult);
		rightBoxPanel.add(Box.createVerticalStrut(3));
		rightBoxPanel.add(breakingDashResult);
		rightBoxPanel.add(Box.createVerticalStrut(4));
		rightBoxPanel.add(denominatorResult);
		rightBoxPanel.add(Box.createVerticalStrut(10));

		centerBoxPanel.setBackground(GUI.getCOLOR());
		centerBoxPanel.setLayout(new BoxLayout(centerBoxPanel, BoxLayout.Y_AXIS));
		centerBoxPanel.add(btnEquals);

		northPanel.setBackground(GUI.getCOLOR());
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		northPanel.add(Box.createHorizontalStrut(WIDTH_OUTER));
		northPanel.add(leftBoxPanel);
		northPanel.add(Box.createHorizontalStrut(WIDTH_INNER));
		northPanel.add(centerBoxPanel);
		northPanel.add(Box.createHorizontalStrut(WIDTH_INNER));
		northPanel.add(rightBoxPanel);
		northPanel.add(Box.createHorizontalStrut(WIDTH_OUTER));

		centerPanelN.setBackground(GUI.getCOLOR());
		centerPanelN.setLayout(new FlowLayout());
		centerPanelN.add(lblResult);

		centerPanelS.setBackground(GUI.getCOLOR());
		centerPanelS.setLayout(new BoxLayout(centerPanelS, BoxLayout.X_AXIS));
		centerPanelS.add(Box.createHorizontalStrut(45));
		centerPanelS.add(sp, BorderLayout.CENTER);
		centerPanelS.add(Box.createHorizontalStrut(45));

		centerPanel.setBackground(GUI.getCOLOR());
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(centerPanelN);
		centerPanel.add(centerPanelS);

		southPanel.setBackground(GUI.getCOLOR());
		southPanel.setLayout(new FlowLayout());
		southPanel.add(lblRange);
		southPanel.add(rangeBox);
		southPanel.add(btnRNG);
		southPanel.add(btnReset);
		southPanel.add(btnResult);

		// Frame mit den Panels bestücken
		fracture.add(northPanel, BorderLayout.NORTH);
		fracture.add(centerPanel, BorderLayout.CENTER);
		fracture.add(southPanel, BorderLayout.SOUTH);

		// Einstellungen
		rangeBox.setSelectedIndex(4);
		rangeBox.addItemListener(new ComboBoxListener(this));
		settingToStart();

		fracture.setVisible(true);
		descriptionField.requestFocusInWindow();

		// Button Listener
		btnEquals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnEquals.getText().equals(BTNEQUALS_START)) {
					settingToCheck();
				} else if (btnEquals.getText().equals(BTNEQUALS_RESULT)) {
					settingToResult();
				} else if (btnEquals.getText().equals(BTNEQUALS_RETRY)) {
					settingToCheck();
				}
			}
		});

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
				settingToStart();
			}
		});

		btnRNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numerator.randomUpdate();
				denominator.randomUpdate();
				numerator.setText("" + rand.nextInt());
				denominator.setText("" + rand.nextInt());
				settingToCheck();
			}
		});

		btnResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextGenerator.printFractureResult(this, descriptionField, FractureLogic
						.gcd(Integer.parseInt(numerator.getText()), Integer.parseInt(denominator.getText())));
				// TODO TextGenerator.printResult(this, descriptionField,
				// FractureLogic.gcdWithoutDivision(Integer.parseInt(numerator.getText()),
				// Integer.parseInt(denominator.getText())));
			}
		});

	}

	private void settingToStart() {
		btnEquals.setText(BTNEQUALS_START);
		lblResult.setText(LBLRESULT_START);
		numerator.setEditable(true);
		denominator.setEditable(true);
		numeratorResult.setEditable(false);
		denominatorResult.setEditable(false);
		numeratorResult.setEnabled(false);
		denominatorResult.setEnabled(false);
		btnReset.setEnabled(false);
		btnEquals.setEnabled(true);
		rangeBox.setEnabled(true);
		btnRNG.setEnabled(true);
		btnResult.setEnabled(false);
		descriptionField.setText(TextGenerator.getFractionTXT(0));
	}

	private void settingToCheck() {
		numeratorResult.refresh();
		denominatorResult.refresh();
		if (inputIsValid(numerator, denominator)) {
			btnEquals.setText(BTNEQUALS_RESULT);
			numeratorResult.setEditable(true);
			denominatorResult.setEditable(true);
			numeratorResult.setEnabled(true);
			denominatorResult.setEnabled(true);
			numerator.setEditable(false);
			denominator.setEditable(false);
			btnReset.setEnabled(true);
			rangeBox.setEnabled(false);
			btnRNG.setEnabled(false);
			btnResult.setEnabled(false);
			descriptionField.setText(TextGenerator.getFractionTXT(1));
		} else {
			new ErrorDialog(this, TASK, getErrorSource(numerator, denominator), numerator.getHintText(),
					denominator.getHintText());
		}

	}

	private void settingToResult() {
		if (inputIsValid(numeratorResult, denominatorResult)) {
			FractureLogic.verifyFraction(Integer.parseInt(numerator.getText()),
					Integer.parseInt(denominator.getText()));
			if (numeratorResult.getText().equals(FractureLogic.getNumeratorResult())
					&& (denominatorResult.getText().equals(FractureLogic.getDenominatorResult()))) {
				lblResult.setText(LBLRESULT_RIGHT);
				btnEquals.setEnabled(false);
				descriptionField.setText(TextGenerator.getFractionTXT(3));
			} else {
				lblResult.setText(LBLRESULT_WRONG);
				descriptionField.setText(TextGenerator.getFractionTXT(2));
			}
			btnEquals.setText(BTNEQUALS_RETRY);
			numeratorResult.setEditable(false);
			denominatorResult.setEditable(false);
			btnResult.setEnabled(true);
		} else {
			new ErrorDialog(this, RESULT, getErrorSource(numeratorResult, denominatorResult),
					numeratorResult.getHintText(), denominatorResult.getHintText());
		}
	}

	private boolean inputIsValid(NumField first, NumField second) {
		if (first.getText().equals(first.getHintText()) || second.getText().equals(second.getHintText())) {
			return false;
		} else {
			return true;
		}
	}

	private int getErrorSource(NumField first, NumField second) {
		if (first.getText().equals(first.getHintText()) && second.getText().equals(second.getHintText())) {
			return 1;
		} else if (first.getText().equals(first.getHintText())) {
			return 2;
		} else if (second.getText().equals(second.getHintText())) {
			return 3;
		} else {
			return 0;
		}
	}

	public static void refresh() {
		for (int i = 0; i < fieldList.size(); i++) {
			fieldList.get(i).refresh();
		}
	}

}
