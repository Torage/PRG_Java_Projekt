import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListener implements ItemListener {

	private final String TOPIC;
	private String range;
	private String caseVal;
	private int rangeValue;
	private static int caseValue;

	private final String DIVISION = "division";
	private final String FRACTURE = "fracture";
	private final String PERCENTAGE = "percentage";

	public ComboBoxListener(Division division) {
		TOPIC = DIVISION;
	}

	public ComboBoxListener(Fracture fracture) {
		TOPIC = FRACTURE;
	}

	public ComboBoxListener(Percentage percentage) {
		TOPIC = PERCENTAGE;
	}

	@Override
	public void itemStateChanged(ItemEvent evt) {
		if ((TOPIC.equals(DIVISION) || TOPIC.equals(FRACTURE))) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				range = evt.getItem().toString();

				switch (range) {
				case "Einstellig":
					rangeValue = 1;
					break;
				case "Zweistellig":
					rangeValue = 2;
					break;
				case "Dreistellig":
					rangeValue = 3;
					break;
				case "Vierstellig":
					rangeValue = 4;
					break;
				case "Fünfstellig":
					rangeValue = 5;
					break;
				}

				NumField.setMaxRange(rangeValue);

				if (TOPIC.equals(DIVISION)) {
					Division.refresh();
				} else if (TOPIC.equals(FRACTURE)) {
					Fracture.refresh();
				}
			}
		}

		if (TOPIC.equals(PERCENTAGE)) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				caseVal = evt.getItem().toString();

				switch (caseVal) {
				case "normaler Anteil":
					caseValue = 0;
					break;
				case "Zunahme":
					caseValue = 1;
					break;
				case "Abnahme":
					caseValue = 2;
					break;
				}
			}
		}

	}

	public static int getCaseValue() {
		return caseValue;
	}

}
