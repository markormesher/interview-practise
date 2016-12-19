/*
Task: write a recursive function to generate all permutations of a string.
*/

import java.util.*;

public class RecursivePermutations {

	public static ArrayList<String> getPermutations(String str) {
		ArrayList<String> results = new ArrayList<>();
		char[] strChars = str.toCharArray();
		getPermutations_inner(strChars, new char[str.length()], 0, new boolean[str.length()], results);
		return results;
	}

	public static void getPermutations_inner(char[] chars, char[] current, int depth, boolean[] used, List<String> results) {
		if (depth == chars.length) {
			results.add(new String(current));
			return;
		}

		for (int i = 0; i < chars.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				current[depth] = chars[i];
				getPermutations_inner(chars, current, depth + 1, used, results);
				used[i] = false;
			}
		}
	}

	public static void main(String... args) {
		ArrayList<String> permutations = getPermutations("abc");
		for (String s : permutations) {
			System.out.println(s);
		}
	}

}