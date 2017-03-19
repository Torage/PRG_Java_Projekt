import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Percentage {

	private GUI percentage = new GUI("Prozentrechnung");

	private final String BTNOK_START = "OK";
	private final String BTNOK_CHECK = "Prüfen";
	private final String BTNOK_RETRY = "Ändern";
	private final String LBL_FIELD_BASIC_VALUE = "Grundwert";
	private final String LBL_FIELD_PERCENTAGE = "Prozentsatz (%)";
	private final String LBL_FIELD_PERCENTAGE_VALUE = "Prozentwert";

	// TODO private RNG rand = new RNG();

	private JPanel northPanel = new JPanel();
	private JPanel northPanelL = new JPanel();
	private JPanel northPanelC = new JPanel();
	private JPanel northPanelR = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel southPanelN = new JPanel();
	private JPanel southPanelC = new JPanel();
	private JPanel southPanelS = new JPanel();
	private JPanel centerPanel = new JPanel();

	private NumField firstVal = new NumField(this, NumField.CENTER, true);
	private NumField secondVal = new NumField(this, NumField.CENTER, true);
	private NumField thirdVal = new NumField(this, NumField.CENTER, true);
	private static ArrayList<NumField> fieldList = new ArrayList<>();

	private JLabel lblFirstVal = new JLabel(LBL_FIELD_BASIC_VALUE);
	private JLabel lblSecondVal = new JLabel(LBL_FIELD_PERCENTAGE_VALUE);
	private JLabel lblResult = new JLabel(LBL_FIELD_PERCENTAGE);
	private JLabel lblSearched = new JLabel("Gesucht: ");
	private JLabel lblCase = new JLabel("Fall");

	private Font searchedFont = new Font(lblSearched.getFont().getFontName(), lblSearched.getFont().getStyle(),
			lblSearched.getFont().getSize() + 3);

	private DescriptionField descriptionField = new DescriptionField();
	private ScrollPane sp = new ScrollPane(descriptionField);

	private JButton btnOk = new JButton(BTNOK_START);
	private JButton btnReset = new JButton("Reset");
	private JButton btnRNG = new JButton("Zufall");
	private JButton btnResult = new JButton("Lösung");

	private ButtonGroup rbtnGroupSearched = new ButtonGroup();

	private JRadioButton rbtnBasicValue = new JRadioButton(LBL_FIELD_BASIC_VALUE);
	private JRadioButton rbtnPercentage = new JRadioButton(LBL_FIELD_PERCENTAGE);
	private JRadioButton rbtnPercentageValue = new JRadioButton(LBL_FIELD_PERCENTAGE_VALUE);

	private JComboBox<String> caseBox = new JComboBox<>(GUI.getCalculatePercentage());

	public Percentage() {
		// Befüllung der NumField liste
		fieldList.add(firstVal);
		fieldList.add(secondVal);
		fieldList.add(thirdVal);

		firstVal.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Setzen der Hintergrundfarbe
		northPanel.setBackground(GUI.getCOLOR());
		northPanelL.setBackground(GUI.getCOLOR());
		northPanelC.setBackground(GUI.getCOLOR());
		northPanelR.setBackground(GUI.getCOLOR());
		southPanel.setBackground(GUI.getCOLOR());
		southPanelN.setBackground(GUI.getCOLOR());
		southPanelC.setBackground(GUI.getCOLOR());
		southPanelS.setBackground(GUI.getCOLOR());
		centerPanel.setBackground(GUI.getCOLOR());
		rbtnBasicValue.setBackground(GUI.getCOLOR());
		rbtnPercentage.setBackground(GUI.getCOLOR());
		rbtnPercentageValue.setBackground(GUI.getCOLOR());

		// Layouts der Panels festlegen
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		northPanelL.setLayout(new BoxLayout(northPanelL, BoxLayout.Y_AXIS));
		northPanelC.setLayout(new BoxLayout(northPanelC, BoxLayout.Y_AXIS));
		northPanelR.setLayout(new BoxLayout(northPanelR, BoxLayout.Y_AXIS));
		southPanel.setLayout(new GridLayout(2, 1));
		southPanelN.setLayout(new FlowLayout());
		southPanelS.setLayout(new FlowLayout());

		// Erzeugen einer ButtonGroup für die RadioButtons
		rbtnGroupSearched.add(rbtnBasicValue);
		rbtnGroupSearched.add(rbtnPercentage);
		rbtnGroupSearched.add(rbtnPercentageValue);
		rbtnPercentage.setSelected(true);

		// Bestückung der Panels
		northPanelL.add(lblFirstVal);
		lblFirstVal.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanelL.add(firstVal);
		firstVal.setAlignmentX(Component.CENTER_ALIGNMENT);

		northPanelC.add(lblSecondVal);
		lblSecondVal.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanelC.add(secondVal);
		secondVal.setAlignmentX(Component.CENTER_ALIGNMENT);

		northPanelR.add(lblResult);
		lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		northPanelR.add(thirdVal);
		thirdVal.setAlignmentX(Component.CENTER_ALIGNMENT);

		northPanel.add(Box.createHorizontalStrut(50));
		northPanel.add(northPanelL);
		northPanel.add(Box.createHorizontalStrut(100));
		northPanel.add(northPanelC);
		northPanel.add(Box.createHorizontalStrut(100));
		northPanel.add(northPanelR);
		northPanel.add(Box.createHorizontalStrut(50));

		centerPanel.add(sp, BorderLayout.CENTER);

		lblSearched.setFont(searchedFont);
		;
		southPanelN.add(lblSearched);
		southPanelN.add(rbtnBasicValue);
		southPanelN.add(rbtnPercentageValue);
		southPanelN.add(rbtnPercentage);

		southPanelS.add(lblCase);
		southPanelS.add(caseBox);
		southPanelS.add(btnRNG);
		southPanelS.add(btnOk);
		southPanelS.add(btnReset);
		southPanelS.add(btnResult);

		southPanel.add(southPanelN);
		southPanel.add(southPanelS);

		percentage.add(northPanel, BorderLayout.NORTH);
		percentage.add(centerPanel, BorderLayout.CENTER);
		percentage.add(southPanel, BorderLayout.SOUTH);

		// Einstellungen
		caseBox.setSelectedIndex(0);
		caseBox.addItemListener(new ComboBoxListener(this));
		settingToStart();

		percentage.setVisible(true);
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
				runPercentageResultPrinter(this);
			}
		});

		btnRNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		// RadioButton Listener
		rbtnBasicValue.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					settingToBasicValue();
				}
			}
		});

		rbtnPercentageValue.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					settingToPercentageValue();
				}
			}
		});

		rbtnPercentage.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					settingToPercentage();
				}
			}
		});

		caseBox.addItemListener(new ComboBoxListener(this));

	}

	private void settingToStart() {
		refresh();
		rbtnBasicValue.setEnabled(true);
		rbtnPercentage.setEnabled(true);
		rbtnPercentageValue.setEnabled(true);
		caseBox.setEnabled(true);
		btnResult.setEnabled(false);
		btnRNG.setEnabled(true);
		btnOk.setText(BTNOK_START);
		btnOk.setEnabled(true);
		btnReset.setEnabled(false);
		firstVal.setEditable(true);
		secondVal.setEditable(true);
		thirdVal.setEditable(false);
		descriptionField.setText(TextGenerator.getPercentageTXT(0));
	}

	private void settingToCheck() {
		if (inputIsValid(firstVal, secondVal)) {
			rbtnBasicValue.setEnabled(false);
			rbtnPercentage.setEnabled(false);
			rbtnPercentageValue.setEnabled(false);
			caseBox.setEnabled(false);
			btnResult.setEnabled(false);
			btnRNG.setEnabled(false);
			btnOk.setText(BTNOK_CHECK);
			btnReset.setEnabled(true);
			firstVal.setEditable(false);
			secondVal.setEditable(false);
			thirdVal.setEditable(true);
			descriptionField.setText(TextGenerator.getPercentageTXT(1));
		} else {
			new ErrorDialog(this, getErrorSource(firstVal, secondVal), lblFirstVal.getText(), lblSecondVal.getText());
		}

	}

	private void settingToResult() {
		if (inputIsValid(thirdVal)) {
			String percentageResult = runPercentageResult();
			if (thirdVal.getText().equals(percentageResult)) {
				btnOk.setEnabled(false);
				descriptionField.setText(TextGenerator.getPercentageTXT(3));
			} else {
				btnOk.setText(BTNOK_RETRY);
				descriptionField.setText(TextGenerator.getPercentageTXT(2));
			}
			btnResult.setEnabled(true);
			btnReset.setEnabled(true);
			thirdVal.setEditable(false);
		} else {
			new ErrorDialog(this, getErrorSource(thirdVal), lblResult.getText());
		}

	}

	private void settingToPercentage() {
		lblFirstVal.setText(LBL_FIELD_BASIC_VALUE);
		lblSecondVal.setText(LBL_FIELD_PERCENTAGE_VALUE);
		lblResult.setText(LBL_FIELD_PERCENTAGE);
	}

	private void settingToPercentageValue() {
		lblFirstVal.setText(LBL_FIELD_BASIC_VALUE);
		lblSecondVal.setText(LBL_FIELD_PERCENTAGE);
		lblResult.setText(LBL_FIELD_PERCENTAGE_VALUE);
	}

	private void settingToBasicValue() {
		lblFirstVal.setText(LBL_FIELD_PERCENTAGE_VALUE);
		lblSecondVal.setText(LBL_FIELD_PERCENTAGE);
		lblResult.setText(LBL_FIELD_BASIC_VALUE);
	}

	private boolean inputIsValid(NumField first, NumField second) {
		if ((first.getText().trim().isEmpty()) || (second.getText().trim().isEmpty())) {
			return false;
		} else {
			return true;
		}
	}

	private boolean inputIsValid(NumField first) {
		if (first.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private int getErrorSource(NumField first, NumField second) {
		if ((first.getText().trim().isEmpty()) && (second.getText().trim().isEmpty())) {
			return 1;
		} else if (first.getText().trim().isEmpty()) {
			return 2;
		} else if ((second.getText().trim().isEmpty())) {
			return 3;
		} else {
			return 0;
		}
	}

	private int getErrorSource(NumField first) {
		if (first.getText().trim().isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void refresh() {
		for (int i = 0; i < fieldList.size(); i++) {
			fieldList.get(i).setText("");
		}
	}

	private void runPercentageResultPrinter(ActionListener actionListener) {
		int caseValue = ComboBoxListener.getCaseValue();

		if (rbtnPercentage.isSelected()) {
			if (caseValue == 0) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentsatzNormal(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 1) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentsatzZunahme(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 2) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentsatzAbnahme(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			}
		} else if (rbtnBasicValue.isSelected()) {
			if (caseValue == 0) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic
						.grundwertNormal(Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 1) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic
						.grundwertZunahme(Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 2) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic
						.grundwertAbnahme(Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			}
		} else if (rbtnPercentageValue.isSelected()) {
			if (caseValue == 0) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentwertNormal(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 1) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentwertZunahme(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			} else if (caseValue == 2) {
				TextGenerator.printPercentageResult(actionListener, descriptionField, PercentageLogic.prozentwertAbnahme(
						Float.parseFloat(firstVal.getText()), Float.parseFloat(secondVal.getText())));
			}
		}
	}

	private String runPercentageResult() {
		int caseValue = ComboBoxListener.getCaseValue();
		String result = "";
		if (rbtnPercentage.isSelected()) {
			if (caseValue == 0) {
				result = PercentageLogic.verifyProzentsatzNormal(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 1) {
				result = PercentageLogic.verifyProzentsatzZunahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 2) {
				result = PercentageLogic.verifyProzentsatzAbnahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			}
		} else if (rbtnBasicValue.isSelected()) {
			if (caseValue == 0) {
				result = PercentageLogic.verifyGrundwertNormal(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 1) {
				result = PercentageLogic.verifyGrundwertZunahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 2) {
				result = PercentageLogic.verifyGrundwertAbnahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			}
		} else if (rbtnPercentageValue.isSelected()) {
			if (caseValue == 0) {
				result = PercentageLogic.verifyProzentwertNormal(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 1) {
				result = PercentageLogic.verifyProzentwertZunahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			} else if (caseValue == 2) {
				result = PercentageLogic.verifyProzentwertAbnahme(Float.parseFloat(firstVal.getText()),
						Float.parseFloat(secondVal.getText()));
			}
		}
		return result;
	}
}
