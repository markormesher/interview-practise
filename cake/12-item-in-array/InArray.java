/*
Task: check if an item is in the array.
*/

import java.util.*;

public class InArray {

	public static boolean inArray(int[] arr, int item) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] == item) {
				return true;
			} else if (arr[mid] < item) {
				start = mid + 1;
			} else { // arr[mid] > item
				end = mid - 1;
			}
		}
		return false;
	}

	public static void main(String... args) {
		int[][] testArrays = {
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
			{1},
			{1},
			{0, 0, 0, 0},
			{1, 2, 3, 4, 5}
		};
		int[] testItems = {
			4, // present, mid-array
			1, // present, start of array
			5, // present, end of array
			1, // present, 1-item array
			0, // absent, 1-item array
			42, // absent
			42 // absent
		};
		boolean[] answers = {
			true,
			true,
			true,
			true,
			false,
			false,
			false
		};

		for (int i = 0; i < testArrays.length; ++i) {
			if (inArray(testArrays[i], testItems[i]) == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
	}

}