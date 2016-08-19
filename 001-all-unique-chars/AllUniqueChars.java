import java.util.*;

public class AllUniqueChars {

	/*
	O(n) time
	O(n) space, or O(c) if the character range is known
	could also be implemented with an array of booleans, if the character range is known
	*/
	private static boolean allUniqueCharsWithHashSet(String str) {
		HashSet<Character> set = new HashSet<>();

		for (int i = 0; i < str.length(); ++i) {
			if (set.contains(str.charAt(i))) return false;
			set.add(str.charAt(i));
		}

		return true;
	}

	/*
	O(n) time
	O(1) extra space
	lowercase a-z only
	*/
	private static boolean allUniqueCharsWithBitVector(String str) {
		int bitVector = 0;

		for (int i = 0; i < str.length(); ++i) {
			int charValue = str.charAt(i) - 'a';
			if (charValue < 0 || charValue >= 26) {
				System.err.println("Error: bit vector method requires lowercase a-z only");
				return false;
			}

			// check if bit is set
			if ((bitVector & (1 << charValue)) != 0) return false;

			// set bit
			bitVector |= (1 << charValue);
		}

		return true;
	}

	/*
	O(n^2) time
	O(1) extra space
	*/
	private static boolean allUniqueCharsWithTraversal(String str) {
		for (int i = 0; i < str.length() - 1; ++i) {
			char compareTo = str.charAt(i);
			for (int j = i + 1; j < str.length(); ++j) {
				if (str.charAt(j) == compareTo) return false;
			}
		}

		return true;
	}

	public static void main(String... args) {
		if (args.length == 1) {
			System.out.println("Using a HashSet..");
			System.out.println(allUniqueCharsWithHashSet(args[0]) ? "All unique!" : "Contains duplicates");

			System.out.println("\nUsing a bit vector...");
			System.out.println(allUniqueCharsWithBitVector(args[0]) ? "All unique!" : "Contains duplicates");

			System.out.println("\nUsing traversal...");
			System.out.println(allUniqueCharsWithTraversal(args[0]) ? "All unique!" : "Contains duplicates");
		} else {
			System.err.println("You must provide a single string arugment");
		}
	}

}
