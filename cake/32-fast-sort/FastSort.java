/*
Task: sort some non-negative, upper-bounded numbers as fast as possible.
*/

import java.util.*;

public class FastSort {

	public static int[] fastSort(int upperBound, int[] data) {
		int[] buckets = new int[upperBound + 1];
		for (int i = 0; i < data.length; ++i) {
			++buckets[data[i]];
		}

		int pos = 0;
		for (int i = 0; i <= upperBound; ++i) {
			for (int k = 0; k < buckets[i]; ++k) {
				data[pos++] = i;
			}
		}

		return data;
	}

	public static void main(String... args) {
		int[][] inputs = {
			{45, 21, 46, 5, 59, 24, 96, 90, 65},
			{9, 8, 7, 6, 5, 4, 3, 2, 1}
		};
		for (int i = 0; i < inputs.length; ++i) {
			System.out.println("In:  " + Arrays.toString(inputs[i]));
			System.out.println("Out: " + Arrays.toString(fastSort(100, inputs[i])));
		}
	}

}