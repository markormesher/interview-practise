import java.util.*;

public class LinkedListPalindromes {

	private static void printLinkedList(LinkedList<Character> list) {
		if (list.size() == 0) {
			System.out.println("--empty--");
		} else {
			for (int i = 0; i < list.size(); ++i) {
				System.out.print(list.get(i));
				if (i != list.size() - 1) System.out.print(" -> ");
			}
			
			System.out.println();
		}
	}

	private static LinkedList<Character> cloneAndReverse(LinkedList<Character> list) {
		LinkedList<Character> result = new LinkedList<>();
		ListIterator<Character> it = list.listIterator();
		while (it.hasNext()) {
			result.addFirst(it.next());
		}
		return result;
	}

	private static boolean isPalindrome(LinkedList<Character> list) {
		LinkedList<Character> listReversed = cloneAndReverse(list);

		while (!list.isEmpty() && !listReversed.isEmpty()) {
			if (list.removeFirst() != listReversed.removeFirst()) return false;
		}

		return list.isEmpty() && listReversed.isEmpty();
	}

	public static void main(String... args) {
		if (args.length != 1) {
			System.err.println("You must provide one string argument");
			return;
		}

		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < args[0].length(); ++i) {
			list.addLast(args[0].charAt(i));
		}

		System.out.println("Generated list is:");
		printLinkedList(list);

		if (isPalindrome(list)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}