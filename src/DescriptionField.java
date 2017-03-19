import java.awt.Font;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DescriptionField extends JTextArea {

	public DescriptionField() {

		setColumns(50);
		setRows(15);
		setLineWrap(true);
		setWrapStyleWord(true);
		setFont(getFont().deriveFont(Font.BOLD));
		setEditable(false);

	}

}
