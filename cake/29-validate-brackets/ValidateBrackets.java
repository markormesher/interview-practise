/*
Task: find the position of the closing bracket that matches every opening bracket.
*/

import java.util.*;

public class ValidateBrackets {

	public static boolean matches(char open, char close) {
		if (open == '(' && close == ')') return true;
		if (open == '[' && close == ']') return true;
		if (open == '{' && close == '}') return true;
		return false;
	}

	public static boolean isOpenBracket(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	public static boolean isCloseBracket(char c) {
		return c == ')' || c == ']' || c == '}';
	}

	public static boolean validateBrackets(String str) {
		Stack<Character> openings = new Stack<>();

		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			if (isOpenBracket(c)) {
				openings.push(c);
			} else if (isCloseBracket(c)) {
				if (openings.isEmpty()) {
					return false;
				} else if (!matches(openings.peek(), c)) {
					return false;
				} else {
					openings.pop();
				}
			}
		}
		
		return openings.isEmpty();
	}

	public static void main(String[] args) {
		String[] strs = new String[]{
			"this (is) a string (with (a lot) of (brackets))",
			"{[()[{}]]}",
			"{",
			"()()()[](",
			"()()()[])"
		};
		boolean[] answers = {true, true, false, false, false};

		for (int i = 0; i < strs.length; ++i) {
			boolean answer = validateBrackets(strs[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
	}
}