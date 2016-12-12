/*
Task: find the point at which an array is "rotated".
Eg: [ 5, 6, 1, 2, 3, 4 ]
            ^
*/

import java.util.*;

public class RotationPoint {

	public static int findRotationPoint(int[] arr) {
		int start = 0;
		int end = arr.length - 1;

		if (arr[end] > arr[start]) return start;

		while (start < end) {
			int mid = start + (end - start) / 2;

			if (arr[mid] < arr[start]) {
				end = mid;
			} else {
				start = mid;
			}

			if (start == end - 1) break;
		}
		return end;
	}

	public static void main(String...  args) {
		int[][] arrays = {
			{5, 6, 1, 2, 3, 4},
			{1, 2, 3, 4, 5},
			{1, 0}
		};
		int[] answers = {
			2,
			0,
			1
		};

		for (int i = 0; i < arrays.length; ++i) {
			if (findRotationPoint(arrays[i]) == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + answers[i] + ", got " + findRotationPoint(arrays[i]) + ")");
			}
		}
	}
}