/*
Task: find the position of the closing bracket that matches every opening bracket.
*/

import java.util.*;

public class MatchBrackets {

	public static Map<Integer, Integer> getBracketPositions(String str) {
		Map<Integer, Integer> bracketPairs = new HashMap<>();
		Stack<Integer> openingPositions = new Stack<>();
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			if (c == '(') {
				openingPositions.push(i);
			} else if (c == ')') {
				if (openingPositions.isEmpty()) {
					throw new IllegalArgumentException("Unmatched closing bracket at " + i);
				} else {
					bracketPairs.put(openingPositions.pop(), i);
				}
			}
		}
		if (!openingPositions.isEmpty()) throw new IllegalArgumentException("Unmatched opening bracket at " + openingPositions.peek());
		return bracketPairs;
	}

	public static void main(String[] args) {
		String[] strs = new String[]{
			"this (is) a string (with (a lot) of (brackets))"
		};

		for (int i = 0; i < strs.length; ++i) {
			System.out.println("In: " + strs[i]);
			System.out.println("Out: " + getBracketPositions(strs[i]));
			System.out.println();
		}
	}
}