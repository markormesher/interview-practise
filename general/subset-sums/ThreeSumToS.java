/*
Task: determine whether there is a set of three unique items in an array that sum to some value s.
*/

import java.util.*;

public class SubsetSumToZero {

	public static boolean threeSum(int[] arr, int s) {
		// get size and check
		int n = arr.length;
		if (n < 3) return false;

		// pre-sort the array
		Arrays.sort(arr);

		// look for all combos (indexes are strictly i < j < k, so no duplicates will be found)
		for (int i = 0; i < n; ++i) {
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				int sum = arr[i] + arr[j] + arr[k];
				if (sum == 0) {
					return true;
				} else if (sum > 0) {
					--k;
				} else { // sum < 0
					++j;
				}
			}
		}

		return false;
	}

	public static void main(String... args) {
		int[][] arrays = {
			{1, 2, -3},
			{0, 1, 2, 3, 4, 5, -6, 0},
			{0, 1},
			{0, 1, 2}
		};
		int[] sValues = {
			0,
			2,
			1,
			4
		};
		boolean[] answers = {
			true,
			true,
			false,
			false
		};

		for (int i = 0; i < arrays.length; ++i) {
			boolean answer = threeSum(arrays[i], sValues[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
	}

}