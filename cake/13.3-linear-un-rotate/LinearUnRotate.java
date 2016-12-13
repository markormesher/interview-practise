/*
Task: "un rotate" an array in linear time.
*/

import java.util.*;

public class LinearUnRotate {

	public static void partialRotate(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			++start;
			--end;
		}
	}

	public static int[] unRotate(int[] arr, int offset) {
		int n = arr.length;
		offset %= n;
		if (offset == 0) return arr;
		partialRotate(arr, offset, n - 1);
		partialRotate(arr, 0, n - 1);
		partialRotate(arr, n - offset, n - 1);
		return arr;
	}

	public static void main(String... args) {
		int[][] arrays = {
			{7, 8, 9, 1, 2, 3, 4, 5, 6},
			{0},
			{1, 2, 3, 4},
			{2, 3, 4, 5, 6, 7, 8, 9, 1}
		};
		int[] offsets = {
			3,
			0,
			0,
			8
		};

		for (int i = 0; i < arrays.length; ++i) {
			System.out.println("Input:  " + Arrays.toString(arrays[i]));
			System.out.println("Offset: " + offsets[i]);
			System.out.println("Output: " + Arrays.toString(unRotate(arrays[i], offsets[i])));
			System.out.println();
		}
	}

}