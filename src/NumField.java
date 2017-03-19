import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class NumField extends JTextField {

	public final static int LEFT = JTextField.LEFT;
	public final static int CENTER = JTextField.CENTER;
	public final static int RIGHT = JTextField.RIGHT;
	private final String TOPIC;
	private final String DIVISION = "division";
	private final String FRACTURE = "fracture";
	private final String PERCENTAGE = "percentage";
	private final char[] ValidCharsDivision = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private final char[] ValidCharsFracture = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private final char[] ValidCharsPercentage = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };

	private final Color ORIGINAL_COLOR = getForeground();
	private final Color HINT_COLOR = new Color(150, 150, 150);

	private String hintText;
	private boolean zeroStartingFlag;
	private boolean isRemainderFlag;
	private boolean commaFlag = false;
	private int alignment;

	private static int maxRange = 5;
	private static int maxRangeRemainder = 2;
	private int inputRange = 0;
	private final int SIZE = 5;
	private int inputRangeAfterComma = 0;

	public NumField(Division division, String hintText, boolean zeroStartingFlag, boolean isRemainderFlag,
			int alignment) {
		this.hintText = hintText;
		this.zeroStartingFlag = zeroStartingFlag;
		this.isRemainderFlag = isRemainderFlag;
		this.alignment = alignment;

		TOPIC = DIVISION;

		settings();
		addInputListener(ValidCharsDivision);
		addDivisionInputListener();
		addHintTextListener();

	}

	public NumField(Fracture fracture, String hintText, int alignment) {
		this.hintText = hintText;
		this.alignment = alignment;
		setBorder(null);

		TOPIC = FRACTURE;

		settings();
		addInputListener(ValidCharsFracture);
		addDivisionInputListener();
		addHintTextListener();

	}

	public NumField(Percentage percentage, int alignment, boolean zeroStartingFlag) {
		this.alignment = alignment;
		this.zeroStartingFlag = zeroStartingFlag;

		TOPIC = PERCENTAGE;

		settingsPercentage();
		addInputListener(ValidCharsPercentage);
		addPercentageInputListener();
	}

	private void addInputListener(char[] validChars) {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if ((getText().length() == 0) && (!zeroStartingFlag) && (e.getKeyChar() == '0')) {
					e.consume();
				}

				if (getText().startsWith("0") && zeroStartingFlag && !isRemainderFlag) {
					if (TOPIC.equals(PERCENTAGE) && e.getKeyChar() == '0' && !commaFlag) {
						e.consume();
					} else if (TOPIC.equals(PERCENTAGE)) {

					} else {
						e.consume();
					}
				}

				if (TOPIC.equals(PERCENTAGE) && commaFlag && getText().endsWith("0") && e.getKeyChar() == '0') {
					e.consume();
				}

				if (getText().startsWith("0") && zeroStartingFlag && isRemainderFlag && (e.getKeyChar() == '0')) {
					e.consume();
				}

				boolean typedCharIsValid = false;
				for (char c : validChars) {
					if (c == e.getKeyChar()) {
						typedCharIsValid = true;
						break;
					} else {
						typedCharIsValid = false;
					}
				}

				if (!typedCharIsValid) {
					e.consume();
				}

				if ((TOPIC.equals(DIVISION)) || (TOPIC.equals(FRACTURE))) {
					if ((inputRange >= maxRange) && (!isRemainderFlag)) {
						e.consume();
					}
					if ((inputRange >= maxRangeRemainder) && (isRemainderFlag)) {
						e.consume();
					}
				} else if (TOPIC.equals(PERCENTAGE)) {
					if ((e.getKeyChar() == '.') && (!commaFlag)) {
						commaFlag = true;
						inputRangeAfterComma--;
					} else if ((e.getKeyChar() == '.') && (commaFlag)) {
						e.consume();
					}

					if (inputRangeAfterComma >= maxRangeRemainder) {
						e.consume();
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

	private void addDivisionInputListener() {
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (inputRange != 0) {
					inputRange = getText().length();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if ((!isRemainderFlag) && (inputRange < maxRange)) {
					inputRange++;
				}

				if (isRemainderFlag && (inputRange < maxRangeRemainder)) {
					inputRange++;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
	}

	private void addPercentageInputListener() {
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String txt = getText();

				if (txt.contains(".")) {
					commaFlag = true;
					for (int i = 0; i < txt.length(); i++) {
						if (txt.charAt(i) == '.') {
							inputRange = txt.substring(0, i).length();
							inputRangeAfterComma = txt.substring(i + 1).length();
						}
					}
				} else if (!txt.contains(".")) {
					commaFlag = false;
					inputRange = txt.length();
					inputRangeAfterComma = 0;
				}

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String txt = getText();

				if (txt.contains(".")) {
					commaFlag = true;
					for (int i = 0; i < txt.length(); i++) {
						if (txt.charAt(i) == '.') {
							inputRange = txt.substring(0, i).length();
							inputRangeAfterComma = txt.substring(i + 1).length();
						}
					}
				} else if (!txt.contains(".")) {
					commaFlag = false;
					inputRange = txt.length();
					inputRangeAfterComma = 0;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
	}

	private void addHintTextListener() {
		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().trim().isEmpty()) {
					refresh();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hintText)) {
					setForeground(ORIGINAL_COLOR);
					setHorizontalAlignment(alignment);
					setText("");
				}
			}
		});
	}

	private void settings() {
		setColumns(SIZE);
		setTransferHandler(null);
		setForeground(HINT_COLOR);
		setHorizontalAlignment(CENTER);
		setText(hintText);
	}

	private void settingsPercentage() {
		setColumns(SIZE);
		setTransferHandler(null);
		setHorizontalAlignment(alignment);
	}

	public void refresh() {
		setHorizontalAlignment(CENTER);
		setForeground(HINT_COLOR);
		setText(hintText);
	}

	public void randomUpdate() {
		setHorizontalAlignment(alignment);
		setForeground(ORIGINAL_COLOR);
		setText("");
	}

	public static int getMaxRange() {
		return maxRange;
	}

	public static void setMaxRange(int maxRange) {
		NumField.maxRange = maxRange;
	}

	public String getHintText() {
		return hintText;
	}

}
