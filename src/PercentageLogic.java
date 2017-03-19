import java.util.ArrayList;

public class PercentageLogic {
	public static ArrayList<String> prozentwertNormal(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (prozentsatz / 100f))) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentwert zu erhalten verwenden wir die Formel:\n     Prozentwert(P) = Grundwert(G) * Prozentsatz(p) / 100\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(grundwert + " * " + prozentsatz + " / 100 = " + prozentwert);
		results.add(prozentsatz + "% von " + grundwert + " sind " + prozentwert + ".");
		return results;
	}

	public static String verifyProzentwertNormal(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (prozentsatz / 100f))) * 100f) / 100f;
		String results = Float.toString(prozentwert);
		return results;
	}

	public static ArrayList<String> prozentwertZunahme(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (1 + prozentsatz / 100f))) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentwert zu erhalten verwenden wir die Formel:\n     Prozentwert(P) = Grundwert(G) * (1+(Prozentsatz(p) / 100)\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(grundwert + " * " + prozentsatz + " / 100 = " + prozentwert);
		results.add("Wenn " + grundwert + " um " + prozentsatz + "% vermehrt wird, ergibt sich " + prozentwert + ".");
		return results;
	}

	public static String verifyProzentwertZunahme(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (1 + prozentsatz / 100f))) * 100f) / 100f;
		String results = Float.toString(prozentwert);
		return results;
	}

	public static ArrayList<String> prozentwertAbnahme(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (1 - prozentsatz / 100f))) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentwert zu erhalten verwenden wir die Formel:\n     Prozentwert(P) = Grundwert(G) * (1-(Prozentsatz(p) / 100))\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(grundwert + " * " + prozentsatz + " / 100 = " + prozentwert);
		results.add("Wenn " + grundwert + " um " + prozentsatz + "% vermindert wird, ergibt sich " + prozentwert + ".");
		return results;

	}

	public static String verifyProzentwertAbnahme(float grundwert, float prozentsatz) {
		float prozentwert = Math.round(((grundwert * (1 - prozentsatz / 100f))) * 100f) / 100f;
		String results = Float.toString(prozentwert);
		return results;
	}

	public static ArrayList<String> grundwertNormal(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / prozentsatz * 100f * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Grundwert zu erhalten verwenden wir die Formel:\n     Grundwert(G) = Prozentwert(P) / Prozentsatz(p) * 100\n");
		results.add("Prozentwert: " + prozentwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(prozentwert + " / " + prozentsatz + " / 100 = " + grundwert);
		results.add(prozentwert + " sind " + prozentsatz + "% von " + grundwert + ".");
		return results;

	}

	public static String verifyGrundwertNormal(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / prozentsatz * 100f * 100f) / 100f;
		String results = Float.toString(grundwert);
		return results;

	}

	public static ArrayList<String> grundwertZunahme(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / (1 + (prozentsatz / 100f)) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Grundwert zu erhalten verwenden wir die Formel:\n     Grundwert(G) = Prozentwert(P) / (1+Prozentsatz(p) * 100)\n");
		results.add("Prozentwert: " + prozentwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(prozentwert + " / " + prozentsatz + " / 100 = " + grundwert);
		results.add(prozentwert + " sind " + prozentsatz + "% mehr als " + grundwert + ".");
		return results;

	}

	public static String verifyGrundwertZunahme(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / (1 + (prozentsatz / 100f)) * 100f) / 100f;
		String results = Float.toString(grundwert);
		return results;

	}

	public static ArrayList<String> grundwertAbnahme(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / (1 - prozentsatz / 100) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Grundwert zu erhalten verwenden wir die Formel:\n     Grundwert(G) = Prozentwert(P) / (1-Prozentsatz(p)) * 100)\n");
		results.add("Prozentwert: " + prozentwert);
		results.add("Prozentsatz(in %): " + prozentsatz + "\n");
		results.add(prozentwert + " / " + prozentsatz + " / 100 = " + grundwert);
		results.add(prozentwert + " sind " + prozentsatz + "% weniger als " + grundwert + ".");
		return results;

	}

	public static String verifyGrundwertAbnahme(float prozentwert, float prozentsatz) {
		float grundwert = Math.round(prozentwert / (1 - prozentsatz / 100) * 100f) / 100f;
		String results = Float.toString(grundwert);
		return results;

	}

	public static ArrayList<String> prozentsatzNormal(float grundwert, float prozentwert) {
		float prozentsatz = Math.round(((prozentwert / grundwert) * 100f) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentsatz zu erhalten verwenden wir die Formel:\n     Prozentsatz(p) = Prozentwert(P) / Grundwert(G) * 100\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentwert: " + prozentwert + "\n");
		results.add(grundwert + " / " + prozentwert + " / 100 = " + prozentsatz + "%");
		results.add(prozentwert + " von " + grundwert + " sind " + prozentsatz + "%.");
		return results;

	}

	public static String verifyProzentsatzNormal(float grundwert, float prozentwert) {
		float prozentsatz = Math.round(((prozentwert / grundwert) * 100f) * 100f) / 100f;
		String results = Float.toString(prozentsatz);
		return results;

	}

	public static ArrayList<String> prozentsatzZunahme(float grundwert, float prozentwert) {
		float prozentsatz = Math.round((((prozentwert / grundwert) - 1) * 100f) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentsatz zu erhalten verwenden wir die Formel:\n     Prozentsatz(p) = (Prozentwert(P) / Grundwert(G) * 100)-1)\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentwert: " + prozentwert + "\n");
		results.add(grundwert + " / " + prozentwert + " / 100 = " + prozentsatz + "%");
		results.add(prozentwert + " sind " + prozentsatz + "% mehr als " + grundwert + ".");
		return results;
	}

	public static String verifyProzentsatzZunahme(float grundwert, float prozentwert) {
		float prozentsatz = Math.round((((prozentwert / grundwert) - 1) * 100f) * 100f) / 100f;
		String results = Float.toString(prozentsatz);
		return results;
	}

	public static ArrayList<String> prozentsatzAbnahme(float grundwert, float prozentwert) {
		float prozentsatz = Math.round(((1 - (prozentwert / grundwert)) * 100f) * 100f) / 100f;
		ArrayList<String> results = new ArrayList<>();
		results.add(
				"Um den Prozentsatz zu erhalten verwenden wir die Formel:\n     Prozentsatz(p) = 1-(Prozentwert(P) / Grundwert(G) * 100)\n");
		results.add("Grundwert: " + grundwert);
		results.add("Prozentwert: " + prozentwert + "\n");
		results.add(grundwert + " / " + prozentwert + " / 100 = " + prozentsatz + "%");
		results.add(prozentwert + " sind " + prozentsatz + "% weniger als " + grundwert + ".");
		return results;
	}

	public static String verifyProzentsatzAbnahme(float grundwert, float prozentwert) {
		float prozentsatz = Math.round(((1 - (prozentwert / grundwert)) * 100f) * 100f) / 100f;
		String results = Float.toString(prozentsatz);
		return results;
	}

}