import java.util.*;

public class PalindromePermutation {

	/*
	O(n) time
	O(n) space, or O(c) if the character range is known
	*/
	private static boolean palindromePermutationWithHashMap(String str) {
		Map<Character, Integer> charCounts = new HashMap<>();

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (charCounts.containsKey(ch)) {
				charCounts.put(ch, charCounts.get(ch) + 1);
			} else {
				charCounts.put(ch, 1);
			}
		}

		// at most one character can appear an odd number of times
		boolean foundOdd = false;
		for (Map.Entry<Character, Integer> e : charCounts.entrySet()) {
			if (e.getValue() % 2 == 1) {
				if (foundOdd) return false;
				foundOdd = true;
			}
		}

		return true;
	}

	/*
	O(n) time
	O(1) extra space
	lowercase a-z only
	*/
	private static boolean palindromePermutationWithBitVector(String str) {
		int bitVector = 0;

		for (int i = 0; i < str.length(); ++i) {
			int charValue = str.charAt(i) - 'a';
			if (charValue < 0 || charValue >= 26) {
				System.err.println("Error: bit vector method requires lowercase a-z only");
				return false;
			}

			// toggle bit
			bitVector ^= (1 << charValue);
		}

		// check that there is exactly one "set" bit
		return (bitVector & (bitVector - 1)) == 0;
	}

	public static void main(String... args) {
		if (args.length == 1) {
			System.out.println("Using a HashMap...");
			System.out.println(palindromePermutationWithHashMap(args[0]) ? "YES" : "NO");
			System.out.println("\nUsing a bit vector...");
			System.out.println(palindromePermutationWithBitVector(args[0]) ? "YES" : "NO");
		} else {
			System.err.println("You must provide a single string arugment");
		}
	}

}