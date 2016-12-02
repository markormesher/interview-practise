/*
Task: map an array to an array of the same size, where each index holds the position of the product of all numbers
in that array, apart from the value in that index.

Eg:
In: [1, 7, 3, 4]
Out: [7*3*4, 1*3*4, 1*7*4, 1*7*3] = [84, 12, 28, 21]
*/

import java.util.*;

public class Product {

	private static int[] products(int[] nums) {
		int n = nums.length;
		if (n < 2) throw new IllegalArgumentException("");

		int[] output = new int[n];

		// build up list of products of numbers before each index
		int runningProduct = 1;
		for (int i = 0; i < n; ++i) {
			output[i] = runningProduct;
			runningProduct *= nums[i];
		}

		// work back down the array, multiplying by the product of values after each index
		runningProduct = 1;
		for (int i = n - 1; i >= 0; --i) {
			output[i] *= runningProduct;
			runningProduct *= nums[i];
		}

		return output;
	}

	public static void main(String... args) {
		int[][] nums = new int[][]{
			{ 1, 7, 3, 4 },
			{ 1, 2, 3, 4, 5 },
			{ 1, 0 },
			{ 0, 1 },
			{ 2, 2, 2, 2, 2 }
		};

		for (int i = 0; i < nums.length; ++i) {
			System.out.println("#" + i + " In: " + Arrays.toString(nums[i]));
			System.out.println("#" + i + " Out: " + Arrays.toString(products(nums[i])));
			System.out.println();
		}
	}

}
