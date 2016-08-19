import java.util.*;

public class CheckIfStringIsPermutationOfAnother {

	/*
	O(a + b) time
	O(a + b) space
	*/
	private static boolean checkIfStringIsPermutationOfAnotherWithCharacterCounts(String a, String b) {
		Map<Character, Integer> charCounts = new HashMap<>();

		// count chars in string a
		for (int i = 0; i < a.length(); ++i) {
			char ch = a.charAt(i);
			if (charCounts.containsKey(ch)) {
				charCounts.put(ch, charCounts.get(ch) + 1);
			} else {
				charCounts.put(ch, 1);
			}
		}

		// compare chars in string b
		for (int i = 0; i < b.length(); ++i) {
			char ch = b.charAt(i);
			if (!charCounts.containsKey(ch)) return false;
			charCounts.put(ch, charCounts.get(ch) - 1);
			if (charCounts.get(ch) < 0) return false;
		}

		return true;
	}

	/*
	O(a * log a + b * log b) time
	O(1) space if the sort can be done in-place, otherwise O(a + b)
	*/
	private static boolean checkIfStringIsPermutationOfAnotherWithSortedComparisons(String a, String b) {
		char[] aChars = a.toCharArray();
		Arrays.sort(aChars);
		a = new String(aChars);

		char[] bChars = b.toCharArray();
		Arrays.sort(bChars);
		b = new String(bChars);

		return a.equals(b);
	}

	public static void main(String... args) {
		if (args.length == 2) {
			System.out.println("Using character counting..");
			System.out.println(checkIfStringIsPermutationOfAnotherWithCharacterCounts(args[0], args[1]) ? "Yes" : "No");

			System.out.println("\nUsing sorted comparisons...");
			System.out.println(checkIfStringIsPermutationOfAnotherWithSortedComparisons(args[0], args[1]) ? "Yes" : "No");
		} else {
			System.err.println("You must provide two string arugments");
		}
	}

}
