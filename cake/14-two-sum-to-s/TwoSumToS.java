/*
Task: determine whether two items in the array sum to S.
*/

import java.util.*;

public class TwoSumToS {

	public static boolean twoSumToS_withoutSort(int[] arr, int s) {
		Set<Integer> values = new HashSet<>();
		for (int i = 0; i < arr.length; ++i) {
			if (values.contains(s - arr[i])) return true;
			values.add(arr[i]);
		}
		return false;
	}

	public static boolean twoSumToS_withSort(int[] arr, int s) {
		Arrays.sort(arr);
		int start = 0, end = arr.length - 1;
		while (start < end) {
			int sum = arr[start] + arr[end];
			if (sum == s) {
				return true;
			} else if (sum < s) {
				++start;
			} else { // sum > s
				--end;
			}
		}
		return false;
	}

	public static void main(String... args) {
		int[][] arrays = {
			{1, 2, 3, 4, 5},
			{},
			{1},
			{2, 4, 6, 8, 10}
		};
		int[] targetSums = {
			8,
			1,
			1,
			5
		};
		boolean[] answers = {
			true,
			false,
			false,
			false
		};

		for (int i = 0; i < arrays.length; ++i) {
			boolean answer1 = twoSumToS_withoutSort(arrays[i], targetSums[i]);
			boolean answer2 = twoSumToS_withSort(arrays[i], targetSums[i]);
			if (answer1 == answers[i]) {
				System.out.print("CORRECT, ");
			} else {
				System.out.print("INCORRECT, ");
			}
			if (answer2 == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
	}

}