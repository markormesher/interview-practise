/*
Task: merge two already sorted arrays.
*/

import java.util.*;

public class Merge {

	public static int[] doMerge(int[] a, int[] b) {
		int[] out = new int[a.length + b.length];

		int i = 0;
		int aPointer = 0;
		int bPointer = 0;

		while (aPointer < a.length && bPointer < b.length) {
			if (a[aPointer] < b[bPointer]) {
				out[i] = a[aPointer];
				++aPointer;
			} else {
				out[i] = b[bPointer];
				++bPointer;
			}
			++i;
		}

		while (aPointer < a.length) {
			out[i] = a[aPointer];
			++i;
			++aPointer;
		}

		while (bPointer < b.length) {
			out[i] = b[bPointer];
			++i;
			++bPointer;
		}

		return out;
	}

	public static void main(String... args) {
		int[][][] arrays = {
			{ {3, 4, 6, 10, 11, 15}, {1, 5, 8, 12, 14, 19} },
			{ {3, 4, 6, 10, 11, 15}, {1} },
			{ {3}, {1, 5, 8, 12, 14, 19} },
			{ {}, {1, 5, 8, 12, 14, 19} },
			{ {3, 4, 6, 10, 11, 15}, {} }
		};

		for (int i = 0; i < arrays.length; ++i) {
			int[] result = doMerge(arrays[i][0], arrays[i][1]);
			System.out.println("A: " + Arrays.toString(arrays[i][0]));
			System.out.println("B: " + Arrays.toString(arrays[i][1]));
			System.out.println("C: " + Arrays.toString(result));
			System.out.println("");
		}
	}

}