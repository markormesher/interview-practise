/*
Task: perform a uniform, in-place shuffle on an array.
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Shuffle {

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int randomBetween(int inclLower, int exclUpper) {
		return ThreadLocalRandom.current().nextInt(inclLower, exclUpper);
	}

	public static void shuffle(int[] arr) {
		int n = arr.length;
		if (n <= 1) return;

		for (int i = 0; i < n; ++i) {
			swap(arr, i, randomBetween(i, n));
		}
	}

	public static void main(String... args) {
		int[][] arrs = {
			{1},
			{},
			{1, 2, 3},
			{1, 2, 3, 4, 5, 6, 7, 9, 0}
		};

		for (int i = 0; i < arrs.length; ++i) {
			System.out.println(Arrays.toString(arrs[i]));
			shuffle(arrs[i]);
			System.out.println(Arrays.toString(arrs[i]));
			System.out.println();
		}
	}
}