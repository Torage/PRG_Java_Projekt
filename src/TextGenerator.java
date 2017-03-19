import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class TextGenerator {
	// Universal Text
	private final static String TXT_RANGE = "Im Pulldown Menü \"Wertebereich\" können Sie die Zahlen beschränken, mit denen Sie üben wollen.";
	private final static String TXT_RESET = "Oder drücken Sie auf \"Reset\", um eine neue Aufgabe auswählen zu können.";
	private final static String TXT_RESULT_RESET = "Drücken Sie auf \"Reset\", um eine neue Aufgabe auswählen zu können.";
	private final static String TXT_RNG = "Oder drücken Sie auf \"Zufall\", um Sich einen zufällige Aufgabe generieren zu lassen.";
	private final static String TXT_RESULT = "Drücken Sie auf \"Lösung\", um Sich den Lösungsweg anzeigen zu lassen.";
	private final static String TXT_REMAINDER = "Wählen Sie aus, ob Sie eine Division mit oder ohne Rest durchführen wollen.";
	private final static String TXT_SEARCHED = "Bei \"Gesucht\" können Sie den Wert einstellen, nach dem gesucht wird.";
	private final static String TXT_CASE = "Im Pulldown Menü \"Fall\", können Sie den Fall einstellen, der zutrifft.";

	// Text for Division Class
	private final static String TXT_DIVISION_START = TXT_RANGE + "\n\n\n" + TXT_REMAINDER + "\n\n\n"
			+ "Erstellen Sie Sich entweder selber eine Aufgabe, "
			+ "indem Sie irgendeinen divisor und dividenden eingeben und diese mit \"OK\" bestätigen." + "\n\n"
			+ TXT_RNG;
	private final static String TXT_DIVISION_CHECK = "Geben Sie nun den ganzzahligen Anteil des Ergebnisses, in das Feld \"Ergebnis\" ein "
			+ "und den Rest bzw. den ganzzahligen Anteil der Nachkommastelle in das Feld \"Rest\" ein. " + "\n"
			+ "Bestätigen Sie anschliessend Ihr Ergebnis, indem Sie auf \"Prüfen\" klicken." + "\n\n\n" + TXT_RESET;
	private final static String TXT_DIVISION_RETRY = "Ihre Lösung ist Falsch!" + "\n\n\n"
			+ "Klicken Sie auf \"Ändern\", um Ihre Eingabe zu korrigieren." + "\n\n" + TXT_RESULT + "\n\n" + TXT_RESET;
	private final static String TXT_DIVISION_CORRECT = "Ihre Lösung ist Richtig!" + "\n\n\n" + TXT_RESULT + "\n\n"
			+ TXT_RESET;
	private final static String[] TXT_DIVISION = new String[] { TXT_DIVISION_START, TXT_DIVISION_CHECK,
			TXT_DIVISION_RETRY, TXT_DIVISION_CORRECT };

	public static String getDivisionTXT(int txtIndex) {
		return TXT_DIVISION[txtIndex];
	}

	// Text for Fraction Class
	private final static String TXT_FRACTURE_START = TXT_RANGE + "\n\n\n"
			+ "Erstellen Sie Sich entweder selber eine Aufgabe, "
			+ "indem Sie irgendeinen Bruch eingeben und diesen mit \"OK\" bestätigen." + "\n\n" + TXT_RNG;
	private final static String TXT_FRACTURE_CHECK = "Geben Sie nun den gekürzten Bruch ein und bestätigen Sie diesen mit \"=\"."
			+ "\n\n\n" + TXT_RESET;
	private final static String TXT_FRACTURE_RETRY = "Klicken Sie auf \"<<\", um Ihre Eingabe zu korrigieren." + "\n\n"
			+ TXT_RESULT + "\n\n" + TXT_RESET;
	private final static String TXT_FRACTURE_CORRECT = TXT_RESULT + "\n\n" + TXT_RESET;
	private final static String[] TXT_FRACTURE = new String[] { TXT_FRACTURE_START, TXT_FRACTURE_CHECK,
			TXT_FRACTURE_RETRY, TXT_FRACTURE_CORRECT };

	public static String getFractionTXT(int txtIndex) {
		return TXT_FRACTURE[txtIndex];
	}

	// Text for Percentage Class
	private final static String TXT_PERCENTAGE_START = TXT_SEARCHED + "\n\n" + TXT_CASE + "\n\n\n"
			+ "Füllen Sie die Felder mit irgendwelchen gegebenen Werten (max. auf 2 Nachkommastellen gerundet und das Komma mit einem \".\") "
			+ "und bestätigen Sie diese mit \"OK\".";
	private final static String TXT_PERCENTAGE_CHECK = "Geben Sie nun den gesuchten Wert "
			+ "(max. auf 2 Nachkommastellen gerundet und das Komma mit einem \".\") ein "
			+ "und bestätigen Sie diesen mit \"Prüfen\"." + "\nGanzzahlen in der Form (100.0) eingeben." + "\n\n\n" + TXT_RESET;
	private final static String TXT_PERCENTAGE_RETRY = "Ihre Lösung ist Falsch!" + "\n\n\n"
			+ "Klicken sie auf \"Ändern\", um Ihre Eingabe zu korrigieren." + "\n\n" + TXT_RESET;
	private final static String TXT_PERCENTAGE_CORRECT = "Ihre Lösung ist Richtig!" + "\n\n\n" + TXT_RESULT + "\n\n"
			+ TXT_RESET;
	private final static String[] TXT_PERCENTAGE = new String[] { TXT_PERCENTAGE_START, TXT_PERCENTAGE_CHECK,
			TXT_PERCENTAGE_RETRY, TXT_PERCENTAGE_CORRECT };

	public static String getPercentageTXT(int txtIndex) {
		return TXT_PERCENTAGE[txtIndex];
	}

	// ResultPrinter
	public static void printDivisionResult(ActionListener actionListener, DescriptionField descriptionField,
			ArrayList<String> result) {
		descriptionField.setText("");
		int spaceSize = 1;
		String space = "";
		for (int i = 0; i < result.size(); i++) {
			if (i <= 1) {
				descriptionField.append(result.get(i));
				descriptionField.append("\n");
			} else if (i < result.size()) {
				space = "";
				for (int s = 0; s < spaceSize; s++) {
					space += " ";
				}
				
				if ((i % 2) == 0) {
					int breakingDashSize = result.get(i - 1).substring(1).length();
					String breakingDash = "";
					for (int b = 0; b < breakingDashSize; b++) {
						breakingDash += "_";
					}
					descriptionField.append(space + breakingDash);
					descriptionField.append("\n");
					if (i < (result.size() - 1)) {
						spaceSize += (breakingDashSize - (result.get(i).length() - 1));
					} else {
						if (result.get(i).length() == 1) {
							spaceSize += breakingDashSize + 1;
						} else {
							spaceSize += (breakingDashSize - result.get(i).length());
						}
					}
					space = "";
					for (int s = 0; s < spaceSize; s++) {
						space += " ";
					}
					descriptionField.append(space + result.get(i));
					descriptionField.append("\n");
				} else {
					descriptionField.append(space.substring(1) + result.get(i));
					descriptionField.append("\n");
				}
			}
		}
		descriptionField.append("\n");
		descriptionField.append(TXT_RESULT_RESET);
	}
	
	public static void printFractureResult(ActionListener actionListener, DescriptionField descriptionField,
			ArrayList<String> result) {
		descriptionField.setText("");
		int lastInt = 0;
		int spaceSize = 0;
		Scanner scanner;
		for (int i = 0; i < result.size(); i++) {
			if ((i > 1) && (i < (result.size() - 2))) {
				scanner = new Scanner(result.get(i));
				int currentInt = scanner.nextInt();
				String space = "";
				spaceSize += Integer.toString(lastInt).length() - Integer.toString(currentInt).length();
				for (int s = 0; s < spaceSize; s++) {
					space += " ";
				}
				descriptionField.append(space + result.get(i));
				descriptionField.append("\n");
				lastInt = currentInt;
				scanner.close();
			} else if (i == 1) {
				scanner = new Scanner(result.get(i));
				lastInt = scanner.nextInt();
				descriptionField.append(result.get(i));
				descriptionField.append("\n");
				scanner.close();
			} else {
				descriptionField.append(result.get(i));
				descriptionField.append("\n");
			}
		}
		descriptionField.append("\n");
		descriptionField.append(TXT_RESULT_RESET);
	}
	
	public static void printPercentageResult(ActionListener actionListener, DescriptionField descriptionField,
			ArrayList<String> result) {
		descriptionField.setText("");
		for (int i = 0; i < result.size(); i++) {
			descriptionField.append(result.get(i));
			descriptionField.append("\n");
		}
		descriptionField.append("\n");
		descriptionField.append(TXT_RESULT_RESET);
	}

}
