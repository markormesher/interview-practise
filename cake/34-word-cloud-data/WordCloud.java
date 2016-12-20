/*
Task: create a word => count hash map for some strings.
*/

import java.util.*;

public class WordCloud {

	public static boolean isWordChar(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '-' || c == '\'';
	}

	public static void addWord(Map<String, Integer> scores, String word) {
		word = word.toLowerCase();
		int prevScore = 0;
		if (scores.containsKey(word)) prevScore = scores.get(word);
		scores.put(word, prevScore + 1);
	}

	public static Map<String, Integer>makeWordCloud(String[] strs) {
		Map<String, Integer> scores = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; ++i) {
			char[] strChars = strs[i].toCharArray();
			if (strChars.length == 0) {
				continue;

			} else if (strChars.length == 1) {
				if (isWordChar(strChars[0])) {
					addWord(scores, strs[i]);
				}

			} else {
				int c = 0;

				while (c < strChars.length) {
					// skip leading non-words chars
					while (c < strChars.length && !isWordChar(strChars[c])) ++c;

					// add word chars
					while (c < strChars.length && isWordChar(strChars[c])) {
						sb.append(strChars[c]);
						++c;
					}

					if (sb.length() > 0) {
						addWord(scores, sb.toString());
						sb.setLength(0);
					}
				}
			}
		}
		return scores;
	}

	public static void main(String... args) {
		String[] strs = {
			"After beating the eggs, Dana read the next step:",
			"Add milk and eggs, then add flour and sugar after.",
			"A judge gave the cake a score:",
			"A",
			"",
			"x",
			"test-word",
			"mark's test"
		};
		for (String s : strs) System.out.println(s);
		System.out.println(makeWordCloud(strs));
	}

}