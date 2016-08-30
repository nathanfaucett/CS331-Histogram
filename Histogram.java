import java.util.Scanner;
import java.util.ArrayList;


public class Histogram {
	private static Scanner _scanner;
	private static ArrayList<Integer> _distribution;
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
		int index = 0;

		if (number > 10) {
			if (number >= _max) {
				index = (_max / 10) - 1;
			} else {
				String string = Integer.toString(number);
				int first = new Integer(Character.getNumericValue(string.charAt(string.length() - 1)));
				index = new Integer(Character.getNumericValue(string.charAt(string.length() - 2)));

				if (first == 0) {
					index -= 1;
				}
			}
		}

		_distribution.set(index, _distribution.get(index) + 1);
	}

	private static void addRandomNumbers(int count) {
		while (count-- > 0) {
			addNumber(1 + (int) (Math.random() * _max));
		}
	}

	private static String getAsterisk(int count) {
		String asterisk = "";

		while (count-- > 0) {
			asterisk += '*';
		}

		return asterisk;
	}

	private static String getRange(int index) {
		String range = ((index * 10) + 1) + "-" + ((index * 10) + 10);

		if (range.length() < 5) {
			return "0" + range + " ";
		} else if (range.length() < 6) {
			return range + " ";
		} else {
			return range;
		}
	}

	private static void printTable() {
		int index = 0;

		for (Integer count : _distribution) {
            System.out.println(getRange(index) + " | " + getAsterisk(count));
			index++;
		}
	}

	public static void main(String[] args) {
		_scanner = new Scanner(System.in);

		_distribution = new ArrayList<Integer>();
		for (int i = 0, il = _max / 10; i < il; i++) {
			_distribution.add(0);
		}

		System.out.println("\nHistogram:\n\tprint the histogram by entering p or print\n\tr or random will add "+ 10 +" random numbers\n\tq or quit exits the program\n");
		System.out.println("Enter Integers between 1 and " + _max + ": ");

		while (true) {
			String cmd = Histogram.getCmd();

			if (cmd != null) {
				if (cmd.equals("q") || cmd.equals("Q") || cmd.equals("quit") || cmd.equals("Quit")) {
					break;
				}
				if (cmd.equals("r") || cmd.equals("R") || cmd.equals("random") || cmd.equals("Random")) {
					addRandomNumbers(10);
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
