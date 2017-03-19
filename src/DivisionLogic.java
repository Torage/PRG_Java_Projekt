import java.util.ArrayList;

public class DivisionLogic {

	private static int divisor;
	private static int dividend;
	private static String strDivisor;
	private static boolean firstDivide = true;
	private static String possibleDivisor = "";

	private static ArrayList<String> divisionResult;

	// Dividieren mit Rest
	public static ArrayList<String> divisionWithRemainder(int divisorValue, int dividendValue) {
		divisionResult = new ArrayList<>();
		divisor = divisorValue;
		dividend = dividendValue;
		strDivisor = Integer.toString(divisorValue);
		int result = divisor / dividend;
		int remainder = divisor % dividend;

		print(" " + strDivisor + " : " + dividend + " = " + result + " R " + remainder);

		// Der divisor wird Zeichen für Zeichen durchlaufen und die Zeichen
		// werden aneinandergehängt
		for (int i = 0; i < strDivisor.length(); i++) {
			possibleDivisor += strDivisor.charAt(i);

			int subDivisor = Integer.parseInt(possibleDivisor);
			int quotient;
			int product;
			int difference;

			// Der Fall, der viele nullen abdeckt.
			if (subDivisor == 0) {
				quotient = divide(subDivisor, dividend);
				product = multiply(quotient, dividend);
				difference = substract(subDivisor, product);
				possibleDivisor = Integer.toString(difference);
			}

			// Der normale Fall, wenn der mögliche Divisor sich durch den
			// Dividenden teilen lässt
			if (subDivisor >= dividend) {
				quotient = divide(subDivisor, dividend);
				product = multiply(quotient, dividend);
				difference = substract(subDivisor, product);
				possibleDivisor = Integer.toString(difference);
			}

			// Der Fall, wenn der ursprüngliche Divisor komplett abgearbeitet
			// wurde
			if (i == (strDivisor.length() - 1)) {
				remainder = Integer.parseInt(possibleDivisor);
				// Der Fall, wenn der ursprüngliche Divisor kleiner als der
				// Dividend war
				if (divisor == remainder) {
					subDivisor = 0;
					quotient = divide(subDivisor, dividend);
					product = multiply(quotient, dividend);
					difference = substract(subDivisor, product);
				}
				print(possibleDivisor);
				break;
			}
		}
		resetVariables();
		return divisionResult;
	}

	// Dividieren ohne Rest
	public static ArrayList<String> divisionWithoutRemainder(int divisorValue, int dividendValue) {
		divisionResult = new ArrayList<>();
		divisor = divisorValue;
		dividend = dividendValue;
		strDivisor = Integer.toString(divisorValue);
		int remainder = 0;
		boolean finished = false;
		double result = Math.round(((double) divisor / (double) dividend) * 100);
		result /= 100;

		int subDivisor;
		int quotient;
		int product;
		int difference;
		int counter = -1;
		
		print(" " + strDivisor + " : " + dividend + " = " + result);

		// Der divisor wird Zeichen für Zeichen durchlaufen und die Zeichen
		// werden aneinandergehängt
		for (int i = 0; i < strDivisor.length(); i++) {
			possibleDivisor += strDivisor.charAt(i);

			subDivisor = Integer.parseInt(possibleDivisor);

			// Der Fall, der viele nullen abdeckt.
			if (subDivisor == 0) {
				quotient = divide(subDivisor, dividend);
				product = multiply(quotient, dividend);
				difference = substract(subDivisor, product);
				possibleDivisor = Integer.toString(difference);
			}

			// Der normale Fall, wenn der mögliche Divisor sich durch den
			// Dividenden teilen lässt
			if (subDivisor >= dividend) {
				quotient = divide(subDivisor, dividend);
				product = multiply(quotient, dividend);
				difference = substract(subDivisor, product);
				possibleDivisor = Integer.toString(difference);
			}

			// Der Fall, wenn der ursprüngliche Divisor komplett abgearbeitet
			// wurde
			if (i == (strDivisor.length() - 1)) {
				remainder = Integer.parseInt(possibleDivisor);
				// Der Fall, wenn der ursprüngliche Divisor kleiner als der
				// Dividend war
				if (divisor == remainder) {
					subDivisor = 0;
					quotient = divide(subDivisor, dividend);
					product = multiply(quotient, dividend);
					difference = substract(subDivisor, product);
				}

				if (remainder == 0) {
					print(possibleDivisor);
					finished = true;
				}

				break;
			}
		}

		while (!finished) {
			counter++;
			
			if (!possibleDivisor.equals("0")) {
				possibleDivisor += "0";
			} else {
				print(possibleDivisor);
				break;
			}
			remainder = Integer.parseInt(possibleDivisor);

			subDivisor = Integer.parseInt(possibleDivisor);
			
			if (remainder == 0) {
				print(possibleDivisor);
				finished = true;
			}
			
			if (counter == 2) {
				print(possibleDivisor.substring(0, possibleDivisor.length() - 1));
				print(". . .");
				break;
			}
			
			quotient = divide(subDivisor, dividend);
			product = multiply(quotient, dividend);
			difference = substract(subDivisor, product);
			possibleDivisor = Integer.toString(difference);

		}
		resetVariables();
		return divisionResult;
	}

	// Methode, welche die benötigten Ergebnisse in die ArrayList speichert
	private static void print(String str) {
		divisionResult.add(str);
	}

	// Methode zum dividieren
	private static int divide(int divisor, int dividend) {
		int result = divisor / dividend;
		if (!firstDivide) {
			print(possibleDivisor);
		} else {
			firstDivide = false;
		}
		return result;
	}

	// Methode zum multiplizieren
	private static int multiply(int subResult, int dividend) {
		int result = subResult * dividend;
		String temp = "-" + result;
		print(temp);
		return result;
	}

	// Methode zum subtrahieren
	private static int substract(int subDivisor, int subtrahend) {
		int result = subDivisor - subtrahend;
		return result;
	}

	// Methode welche die Ergebnisse zum abgleichen in einem Array zurückgibt
	public static String[] getDivisionWithRemainder(int divisor, int dividend) {
		String[] result = new String[2];
		result[0] = Integer.toString(divisor / dividend);
		result[1] = Integer.toString(divisor % dividend);
		return result;
	}

	// Methode welche die Ergebnisse zum abgleichen in einem Array zurückgibt
	public static String[] getDivisionWithoutRemainder(double divisor, double dividend) {
		String[] result = new String[2];
		double resultValue = Math.round((divisor / dividend) * 100);
		resultValue /= 100;
		String txt = Double.toString(resultValue);
		String beforeComma = "";
		String afterComma = "";

		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == '.') {
				beforeComma = txt.substring(0, i);
				afterComma = txt.substring(i + 1);
			}
		}
		result[0] = beforeComma;
		result[1] = afterComma;
		return result;
	}

	// Methode, um die globalen statischen Variablen zurückzusetzen
	private static void resetVariables() {
		divisor = 0;
		dividend = 0;
		strDivisor = "";
		firstDivide = true;
		possibleDivisor = "";
	}

}
