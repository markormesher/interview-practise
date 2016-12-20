/*
Task: in an array with the numbers 1..n, one appears twice; find it.
*/

import java.util.*;

public class FindRepeat {

	public static int findRepeat(int[] arr) {
		int n = arr.length - 1;

		int sumFromOneToN = 0;
		for (int i = 1; i <= n; ++i) sumFromOneToN += i;

		int sumOfArray = 0;
		for (int i = 0; i < arr.length; ++i) sumOfArray += arr[i];

		return sumOfArray - sumFromOneToN;
	}

	public static void main(String... args) {
		int[][] cases = {
			{1, 2, 3, 3, 4, 5},
			{1, 1},
			{1, 2, 2, 3, 5, 4, 7, 6, 8}
		};
		int[] answers = {3, 1, 2};
		for (int i = 0; i < cases.length; ++i) {
			int answer = findRepeat(cases[i]);
			if (answer == answers[i]) {
				System.out.println("Correct");
			} else {
				System.out.println("Incorrect; expected " + answers[i] + " but got " + answer);
			}
		}
	}

}