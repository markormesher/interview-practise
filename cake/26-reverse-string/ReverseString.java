/*
Task: reverse a string in place.
*/

import java.util.*;

public class ReverseString {

	public static String reverse(String str) {
		char[] charArr = str.toCharArray();
		int start = 0, end = charArr.length - 1;
		while (start < end) {
			char tmp = charArr[start];
			charArr[start] = charArr[end];
			charArr[end] = tmp;
			++start;
			--end;
		}
		return new String(charArr);
	}

	public static void main(String[] args) {
		String[] strs = new String[]{
			"if into the security recordings you go only pain you will find",
			"hello world"
		};

		for (int i = 0; i < strs.length; ++i) {
			System.out.println("In: " + strs[i]);
			System.out.println("Out: " + reverse(strs[i]));
			System.out.println();
		}
	}

}