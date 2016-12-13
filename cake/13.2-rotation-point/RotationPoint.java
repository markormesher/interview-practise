/*
Task: find the point at which an array is "rotated".
Eg: [ 5, 6, 1, 2, 3, 4 ]
            ^
*/

import java.util.*;

public class RotationPoint {

	public static int findRotationPoint_n(int[] arr) {
		for (int i = 1; i < arr.length; ++i) {
			if (arr[i] < arr[i - 1]) return i;
		}
		return 0;
	}

	public static int findRotationPoint_log_n(int[] arr) {
		// too short?
		if (arr.length == 0) return -1;
		if (arr.length == 1) return 0;
		if (arr.length == 2) return arr[0] < arr[1] ? 0 : 1;
		
		// no rotation?
		if (arr[0] < arr[arr.length - 1]) return 0;

		int start = 0;
		int end = arr.length - 1;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] > arr[start]) {
				start = mid;
			} else {
				end = mid;
			}

			if (start == end - 1) break;
		}

		return end;
	}

	public static void main(String...  args) {
		int[][] arrays = {
			{5, 6, 1, 2, 3, 4},
			{1, 2, 3, 4, 5},
			{1, 0},
			{1, 2, 3, 4}
		};
		int[] answers = {
			2,
			0,
			1,
			0
		};

		for (int i = 0; i < arrays.length; ++i) {
			int answer = findRotationPoint_log_n(arrays[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + answers[i] + ", got " + answer + ")");
			}
		}
	}
}