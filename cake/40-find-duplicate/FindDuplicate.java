/*
Task: find duplicated number in an array of length n+1, containing values between 1 and n.
*/

import java.util.*;

public class FindDuplicate {

	// time: O(n)
	// space: O(1)
	public static int findDuplicate_pointers(int[] arr) {
		int n = arr.length;
		if (n <= 1) throw new IllegalArgumentException();

		// get inside a cycle
		int positionInCycle = n;
		for (int i = 0; i < n; ++i) {
			positionInCycle = arr[positionInCycle - 1];
		}

		// find the length of the cycle
		int anchor = positionInCycle;
		int cycleLength = 0;
		do {
			++cycleLength;
			positionInCycle = arr[positionInCycle - 1];
		} while (positionInCycle != anchor);

		// set up two pointers at the root
		int ptr1 = n;
		int ptr2 = n;

		// move ptr 2 ahead by the cycle length
		for (int i = 0; i < cycleLength; ++i) {
			ptr2 = arr[ptr2 - 1];
		}

		// keep moving pointers until they meet again
		while (ptr1 != ptr2) {
			ptr1 = arr[ptr1 - 1];
			ptr2 = arr[ptr2 - 1];
		}

		return ptr1;
	}

	// time: O(n log n)
	// space: O(1)
	public static int findDuplicate_binary_search(int[] arr) {
		int n = arr.length;
		if (n <= 1) throw new IllegalArgumentException();

		int min = 1;
		int max = n - 1;

		while (min < max) {
			// split our range into lower and higher ranges
			int mid = min + (max - min) / 2;

			int lowMin = min;
			int lowMax = mid;

			int highMin = mid + 1;
			int highMax = max;

			// count the number of array items that are in the lower value range
			int itemsInLowerRange = 0;
			for (int i : arr) {
				if (i >= lowMin && i <= lowMax) ++itemsInLowerRange;
			}

			// work out how many distinct values should be possible in the lower range
			int distinctPossibilitiesInLowerRange = lowMax - lowMin + 1;

			// if the lower range has too many, it must contain repeats
			if (distinctPossibilitiesInLowerRange < itemsInLowerRange) {
				min = lowMin;
				max = lowMax;
			} else {
				min = highMin;
				max = highMax;
			}
		}

		// we worked down to one value
		return min;
	}

	public static void main(String... args) {
		int[][] arrs = {
			{1, 2, 3, 4, 5, 5, 6, 7, 8, 9},
			{5, 8, 8, 4, 9, 2, 3, 7, 1, 6},
			{1, 2, 2},
			{1, 2, 3, 3},
			{3, 2, 1, 3},
			{2, 2, 1, 3},
			{2, 2, 1, 2}
		};
		int[] answers = {5, 8, 2, 3, 3, 2, 2};
		for (int i = 0; i < arrs.length; ++i) {
			int answer1 = findDuplicate_binary_search(arrs[i]);
			int answer2 = findDuplicate_pointers(arrs[i]);

			if (answer1 == answers[i]) {
				System.out.println("Correct");
			} else {
				System.out.println("Incorrect; was " + answer1 + " but expected " + answers[i]);
			}

			if (answer2 == answers[i]) {
				System.out.println("Correct");
			} else {
				System.out.println("Incorrect; was " + answer2 + " but expected " + answers[i]);
			}

			System.out.println("--");
		}
	}
}