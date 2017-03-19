import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ErrorDialog extends JOptionPane {

	private final String UNKNOWN = "Unbekannter Fehler";

	public ErrorDialog(Division division, int source, int error, String firstValTitle, String secondValTitle) {
		String[][] err = new String[2][4];
		err[0][0] = UNKNOWN;
		err[0][1] = "Die Felder \"" + firstValTitle + "\" & \"" + secondValTitle + "\" dürfen nicht leer bleiben!";
		err[0][2] = "Das Feld \"" + firstValTitle + "\" darf nicht leer bleiben!";
		err[0][3] = "Das Feld \"" + secondValTitle + "\" darf nicht leer bleiben!";
		err[1][0] = UNKNOWN;
		err[1][1] = "Die Felder \"" + firstValTitle + "\" & \"" + secondValTitle + "\" dürfen nicht leer bleiben!";
		err[1][2] = "Das Feld \"" + firstValTitle + "\" darf nicht leer bleiben!";
		err[1][3] = "Das Feld \"" + secondValTitle + "\" darf nicht leer bleiben!";

		showMessageDialog(this, err[source][error], "Fehlerhafte Eingabe", ERROR_MESSAGE);
	}

	public ErrorDialog(Fracture fracture, int source, int error, String firstValTitle, String secondValTitle) {
		String[][] err = new String[2][4];
		err[0][0] = UNKNOWN;
		err[0][1] = "Die Felder \"" + firstValTitle + "\" & \"" + secondValTitle + "\" dürfen nicht leer bleiben!";
		err[0][2] = "Das Feld \"" + firstValTitle + "\" darf nicht leer bleiben!";
		err[0][3] = "Das Feld \"" + secondValTitle + "\" darf nicht leer bleiben!";
		err[1][0] = UNKNOWN;
		err[1][1] = "Die Felder \"" + firstValTitle + "\" & \"" + secondValTitle + "\" dürfen nicht leer bleiben!";
		err[1][2] = "Das Feld \"" + firstValTitle + "\" darf nicht leer bleiben!";
		err[1][3] = "Das Feld \"" + secondValTitle + "\" darf nicht leer bleiben!";

		showMessageDialog(this, err[source][error], "Fehlerhafte Eingabe", ERROR_MESSAGE);
	}

	public ErrorDialog(Percentage percentage, int error, String firstValTitle, String secondValTitle) {
		String[] err = new String[4];
		err[0] = UNKNOWN;
		err[1] = "Die Felder \"" + firstValTitle + "\" & \"" + secondValTitle + "\" dürfen nicht leer bleiben!";
		err[2] = "Das Feld \"" + firstValTitle + "\" darf nicht leer bleiben!";
		err[3] = "Das Feld \"" + secondValTitle + "\" darf nicht leer bleiben!";

		showMessageDialog(this, err[error], "Fehlerhafte Eingabe", ERROR_MESSAGE);
	}

	public ErrorDialog(Percentage percentage, int error, String resultTitle) {
		String[] err = new String[2];
		err[0] = UNKNOWN;
		err[1] = "Das Feld \"" + resultTitle + "\" darf nicht leer bleiben!";

		showMessageDialog(this, err[error], "Fehlerhafte Eingabe", ERROR_MESSAGE);
	}

}
