import java.util.ArrayList;

public class FractureLogic {

	public static String numeratorResult;
	public static String denominatorResult;

	public static ArrayList<String> gcdWithoutDivision(int num1, int num2) {
		ArrayList<String> results = new ArrayList<>();
		int a = num1;
		int b = num2;
		results.add(
				"Wir verwenden den Euklidischen Algorithmus(ohne Division) zum ermitteln des größten gemeinsamen Teilers wie folgt:");
		while (num2 != 0) {
			if (num1 > num2) {
				num1 = num1 - num2;
				results.add(num1 + " = " + (num1 + num2) + " - " + num2);
			} else {
				num2 = num2 - num1;
				results.add(num2 + " = " + (num2 + num1) + " - " + num1);
			}

		}
		results.add("Der ggT von " + a + " und " + b + " ist: " + num1);
		results.add(
				"Jetzt teilen wir nur noch den Dividend und den Divisor durch den ermittelten ggT und erhalten den gekürzten Bruch: "
						+ a / num1 + "/" + b / num1);
		return results;
	}

	public static ArrayList<String> gcd(int num1, int num2) {
		ArrayList<String> results = new ArrayList<>();
		int temp;
		int a = num1;
		int b = num2;
		results.add(
				"Wir verwenden den Euklidischen Algorithmus zum ermitteln des größten gemeinsamen Teilers wie folgt:");
		if (num1 < num2) {
			temp = num1;
			num1 = num2;
			num2 = temp;
		}

		while (num1 % num2 != 0) {
			temp = num1 % num2;
			results.add(num1 + " : " + num2 + " = " + num1 / num2 + " R " + temp);
			num1 = num2;
			num2 = temp;
		}

		temp = num1 % num2;
		results.add(num1 + " : " + num2 + " = " + num1 / num2 + " R " + temp);
		results.add("Der ggT von " + a + " und " + b + " ist: " + num2);
		results.add(
				"Jetzt teilen wir nur noch den Dividend und den Divisor durch den ermittelten ggT und erhalten den gekürzten Bruch: "
						+ a / num2 + "/" + b / num2);
		return results;
	}

	public static void verifyFraction(int num1, int num2) {
		int a = num1;
		int b = num2;
		while (num2 != 0) {
			if (num1 > num2) {
				num1 = num1 - num2;
			} else {
				num2 = num2 - num1;
			}
		}
		numeratorResult = Integer.toString(a / num1);
		denominatorResult = Integer.toString(b / num1);
	}

	public static String getNumeratorResult() {
		return numeratorResult;
	}

	public static String getDenominatorResult() {
		return denominatorResult;
	}

}