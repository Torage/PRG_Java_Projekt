import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class ScrollPane extends JScrollPane {

	public ScrollPane(DescriptionField field) {
		super(field);
		setVerticalScrollBar(new JScrollBar());
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.DARK_GRAY));
	}

}
