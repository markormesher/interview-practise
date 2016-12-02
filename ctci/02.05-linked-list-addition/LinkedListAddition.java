import java.util.*;

public class LinkedListAddition {

	private static void printLinkedList(LinkedList<Integer> list) {
		if (list.size() == 0) {
			System.out.println("--empty--");
		} else {
			int sum = 0;

			for (int i = 0; i < list.size(); ++i) {
				System.out.print(list.get(i));
				if (i != list.size() - 1) System.out.print(" -> ");
				sum += list.get(i) * Math.pow(10, i);
			}
			
			System.out.println("   (" + sum + ")");
		}
	}

	private static LinkedList<Integer> addLinkedLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		LinkedList<Integer> result = new LinkedList<>();

		int carry = 0;
		while (carry != 0 || !list1.isEmpty() || !list2.isEmpty()) {
			int list1Digit = list1.isEmpty() ? 0 : list1.removeFirst();
			int list2Digit = list2.isEmpty() ? 0 : list2.removeFirst();

			int toAddToSum = list1Digit + list2Digit + carry;
			int nextDigit = toAddToSum % 10;
			carry = (toAddToSum - nextDigit) / 10;

			result.addLast(nextDigit);
		}

		return result;
	}

	public static void main(String... args) {
		if (args.length != 2) {
			System.err.println("You must provide two numeric arugments");
			return;
		}

		// check input is numeric
		try {
			int int1 = Integer.parseInt(args[0]);
			int int2 = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("You must provide two numeric arugments");
		}

		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = args[0].length() - 1; i >= 0; --i) {
			list1.addLast(Integer.parseInt(args[0].substring(i, i + 1)));
		}
		LinkedList<Integer> list2 = new LinkedList<>();
		for (int i = args[1].length() - 1; i >= 0; --i) {
			list2.addLast(Integer.parseInt(args[1].substring(i, i + 1)));
		}

		System.out.println("Generated lists are:");
		printLinkedList(list1);
		printLinkedList(list2);

		LinkedList<Integer> result = addLinkedLists(list1, list2);

		System.out.println("Resulting list is:");
		printLinkedList(result);
	}

}