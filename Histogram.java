import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


public class Histogram {
	private static Scanner _scanner;
	private static Map<String, Integer> _distribution;
	private static int _max = 100;

	static String getCmd() {
		if (!_scanner.hasNextShort()) {
			String cmd = _scanner.nextLine();
			return cmd;
		} else {
			return null;
		}
	}

	static int getShort() {
		if (_scanner.hasNextShort()) {
			int number = _scanner.nextShort();

			if (number > 0 && number <= _max) {
				return number;
			} else {
				System.out.println("Enter a Valid Integer between 1 and " + _max + ": ");
				return Histogram.getShort();
			}
		} else {
			System.out.println("Enter a Valid Integer between 1 and " + _max + ": ");
			_scanner.next();
			return Histogram.getShort();
		}
	}

	private static void addNumber(int number) {
		String string = Integer.toString(number);
		int first = Character.getNumericValue(string.charAt(string.length() - 1));
		int second = 0;

		if (number > 10) {
			second = Character.getNumericValue(string.charAt(string.length() - 2));
		}

		String min = second == 0 ? "01" : second + "1";
		String max = second == 0 ? "10" : (second + 1) + "0";
		String range = min + "-" + max;

		if (_distribution.containsKey(range)) {
			_distribution.put(range, _distribution.get(range) + 1);
		} else {
			_distribution.put(range, 1);
		}
	}

	private static String printAsterisk(int count) {
		String asterisk = "";

		while (count-- > 0) {
			asterisk += '*';
		}

		return asterisk;
	}

	private static void printTable() {
		for (Map.Entry<String, Integer> entry : _distribution.entrySet()) {
            System.out.println(entry.getKey() + " | " + Histogram.printAsterisk(entry.getValue()));
		}
	}

	public static void main(String[] args) {
		_scanner = new Scanner(System.in);
		_distribution = new HashMap<String, Integer>();

		System.out.println("\nHistogram:\n  Enter numbers, print the histogram by entering p or print, q or quit exits the program\n");
		System.out.println("Enter Integers between 1 and " + _max + ": ");

		while (true) {
			String cmd = Histogram.getCmd();

			if (cmd != null) {
				if (cmd.equals("q") || cmd.equals("Q") || cmd.equals("quit") || cmd.equals("Quit")) {
					break;
				}
				if (cmd.equals("p") || cmd.equals("P") || cmd.equals("print") || cmd.equals("Print")) {
					Histogram.printTable();
				}
			} else {
				int number = Histogram.getShort();
				Histogram.addNumber(number);
			}
		}
	}
}
