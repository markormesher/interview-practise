/*
Task: determine if some permutation of a string is a palindrome.
*/

import java.util.*;

public class PalindromePermutation {

	public static boolean hasPalindromePermutation(String str) {
		// count all chars
		Set<Character> oddChars = new HashSet<>();
		char[] strChars = str.toCharArray();
		for (int i = 0; i < strChars.length; ++i) {
			char c = strChars[i];
			if (oddChars.contains(c)) {
				oddChars.remove(c);
			} else {
				oddChars.add(c);
			}
		}

		return oddChars.size() <= 1;
	}

	public static void main(String... args) {
		String[] inputs = {
			"civic",
			"acc",
			"a",
			"lvicilv",
			"abcd"
		};
		boolean[] answers = {true, true, true, true, false};

		for (int i = 0; i < inputs.length; ++i) {
			boolean answer = hasPalindromePermutation(inputs[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
	}

}