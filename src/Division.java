import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Division {

	private GUI division = new GUI("Division");

	private final String BTNOK_START = "OK";
	private final String BTNOK_CHECK = "Prüfen";
	private final String BTNOK_RETRY = "Ändern";
	private final int TASK = 0;
	private final int RESULT = 1;

	private RNG rand = new RNG();

	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel southPanelN = new JPanel();
	private JPanel southPanelS = new JPanel();
	private JPanel centerPanel = new JPanel();

	private NumField divisor = new NumField(this, "Divisor", false, false, NumField.RIGHT);
	private NumField dividend = new NumField(this, "Dividend", false, false, NumField.LEFT);
	private NumField result = new NumField(this, "Ergebnis", true, false, NumField.RIGHT);
	private NumField remainder = new NumField(this, "Rest", true, true, NumField.LEFT);
	private static ArrayList<NumField> fieldList = new ArrayList<>();

	private JLabel lblDiv = new JLabel(" : ");
	private JLabel lblEquals = new JLabel("=");
	private JLabel lblRemainder = new JLabel(",");
	private JLabel lblRange = new JLabel("Wertebereich");

	private DescriptionField descriptionField = new DescriptionField();
	private ScrollPane sp = new ScrollPane(descriptionField);

	private JButton btnOk = new JButton(BTNOK_START);
	private JButton btnReset = new JButton("Reset");
	private JButton btnRNG = new JButton("Zufall");
	private JButton btnResult = new JButton("Lösung");

	private ButtonGroup rbtnGroup = new ButtonGroup();

	private JRadioButton rbtnWRemainder = new JRadioButton("mit Rest");
	private JRadioButton rbtnWoRemainder = new JRadioButton("ohne Rest (max. auf 2 Nachkommastellen gerundet)");

	private JComboBox<String> rangeBox = new JComboBox<>(GUI.getRange());

	public Division() {
		// Befüllung der NumField liste
		fieldList.add(divisor);
		fieldList.add(dividend);
		fieldList.add(result);
		fieldList.add(remainder);

		// Setzen der Hintergrundfarbe
		northPanel.setBackground(GUI.getCOLOR());
		southPanel.setBackground(GUI.getCOLOR());
		southPanelN.setBackground(GUI.getCOLOR());
		southPanelS.setBackground(GUI.getCOLOR());
		centerPanel.setBackground(GUI.getCOLOR());
		rbtnWRemainder.setBackground(GUI.getCOLOR());
		rbtnWoRemainder.setBackground(GUI.getCOLOR());

		// Layouts der Panels festlegen
		northPanel.setLayout(new FlowLayout());
		southPanel.setLayout(new GridLayout(2, 1));
		southPanelN.setLayout(new FlowLayout());
		southPanelS.setLayout(new FlowLayout());

		// Erzeugen einer ButtonGroup für die RadioButtons
		rbtnGroup.add(rbtnWRemainder);
		rbtnGroup.add(rbtnWoRemainder);
		rbtnWoRemainder.setSelected(true);

		// Bestückung der Panels
		northPanel.add(divisor);
		northPanel.add(lblDiv);
		northPanel.add(dividend);
		northPanel.add(lblEquals);
		northPanel.add(result);
		northPanel.add(lblRemainder);
		northPanel.add(remainder);
		northPanel.add(btnOk);
		northPanel.add(btnReset);

		centerPanel.add(sp, BorderLayout.CENTER);

		southPanelN.add(rbtnWRemainder);
		southPanelN.add(rbtnWoRemainder);

		southPanelS.add(lblRange);
		southPanelS.add(rangeBox);
		southPanelS.add(btnRNG);
		southPanelS.add(btnResult);

		southPanel.add(southPanelN);
		southPanel.add(southPanelS);

		division.add(northPanel, BorderLayout.NORTH);
		division.add(centerPanel, BorderLayout.CENTER);
		division.add(southPanel, BorderLayout.SOUTH);

		// Einstellungen
		rangeBox.setSelectedIndex(4);
		rangeBox.addItemListener(new ComboBoxListener(this));
		settingToStart();

		division.setVisible(true);
		descriptionField.requestFocusInWindow();

		// Button Listener
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnOk.getText().equals(BTNOK_START)) {
					settingToCheck();
				} else if (btnOk.getText().equals(BTNOK_CHECK)) {
					settingToResult();
				} else if (btnOk.getText().equals(BTNOK_RETRY)) {
					settingToCheck();
				}
			}
		});

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				settingToStart();
			}
		});

		btnResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbtnWRemainder.isSelected()) {
					TextGenerator.printDivisionResult(this, descriptionField, DivisionLogic.divisionWithRemainder(
							Integer.parseInt(divisor.getText()), Integer.parseInt(dividend.getText())));
				} else if (rbtnWoRemainder.isSelected()) {
					TextGenerator.printDivisionResult(this, descriptionField, DivisionLogic.divisionWithoutRemainder(
							Integer.parseInt(divisor.getText()), Integer.parseInt(dividend.getText())));
				}
			}
		});

		btnRNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dividend.randomUpdate();
				divisor.randomUpdate();
				divisor.setText("" + rand.nextInt());
				dividend.setText("" + rand.nextInt());
				settingToCheck();
			}
		});

		// RadioButton Listener
		rbtnWRemainder.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblRemainder.setText("R");
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					lblRemainder.setText(",");
				}
			}
		});

	}

	private void settingToStart() {
		refresh();
		rbtnWoRemainder.setEnabled(true);
		rbtnWRemainder.setEnabled(true);
		rangeBox.setEnabled(true);
		btnResult.setEnabled(false);
		btnRNG.setEnabled(true);
		btnOk.setText(BTNOK_START);
		btnOk.setEnabled(true);
		btnReset.setEnabled(false);
		dividend.setEditable(true);
		divisor.setEditable(true);
		result.setEditable(false);
		remainder.setEditable(false);
		result.setEnabled(false);
		remainder.setEnabled(false);
		descriptionField.setText(TextGenerator.getDivisionTXT(0));
	}

	private void settingToCheck() {
		result.refresh();
		remainder.refresh();
		if (inputIsValid(dividend, divisor)) {
			rbtnWoRemainder.setEnabled(true);
			rbtnWRemainder.setEnabled(true);
			rangeBox.setEnabled(false);
			btnResult.setEnabled(false);
			btnRNG.setEnabled(false);
			btnOk.setText(BTNOK_CHECK);
			btnReset.setEnabled(true);
			dividend.setEditable(false);
			divisor.setEditable(false);
			result.setEditable(true);
			remainder.setEditable(true);
			result.setEnabled(true);
			remainder.setEnabled(true);
			descriptionField.setText(TextGenerator.getDivisionTXT(1));
		} else {
			new ErrorDialog(this, TASK, getErrorSource(divisor, dividend), divisor.getHintText(),
					dividend.getHintText());
		}

	}

	private void settingToResult() {
		if (inputIsValid(result, remainder)) {
			String[] divisionResult = null;

			if (rbtnWRemainder.isSelected()) {
				divisionResult = DivisionLogic.getDivisionWithRemainder(Integer.parseInt(divisor.getText()),
						Integer.parseInt(dividend.getText()));
			} else if (rbtnWoRemainder.isSelected()) {
				divisionResult = DivisionLogic.getDivisionWithoutRemainder(Integer.parseInt(divisor.getText()),
						Integer.parseInt(dividend.getText()));
			}

			if ((divisionResult[0].equals(result.getText())) && (divisionResult[1].equals(remainder.getText()))) {
				descriptionField.setText(TextGenerator.getDivisionTXT(3));
			} else {
				descriptionField.setText(TextGenerator.getDivisionTXT(2));
			}

			rbtnWoRemainder.setEnabled(false);
			rbtnWRemainder.setEnabled(false);
			btnResult.setEnabled(true);
			btnOk.setText(BTNOK_RETRY);
			btnReset.setEnabled(true);
			result.setEditable(false);
			remainder.setEditable(false);
		} else {
			new ErrorDialog(this, RESULT, getErrorSource(result, remainder), result.getHintText(),
					remainder.getHintText());
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
