/*
Task: find the highest possible product of three numbers in the input array.
*/

import java.util.*;

public class Product {

	private static int highestProduct(int[] nums) {
		int n = nums.length;
		if (n < 3) throw new IllegalArgumentException("");

		int maxOfThree = nums[0] * nums[1] * nums[2];
		int maxOfTwo = nums[0] * nums[1];
		int minOfTwo = nums[0] * nums[1];
		int max = Math.max(nums[0], Math.max(nums[1], nums[2]));
		int min = Math.min(nums[0], Math.min(nums[1], nums[2]));

		for (int i = 2; i < n; ++i) {
			int curr = nums[i];
			maxOfThree = Math.max(maxOfThree, Math.max(minOfTwo * curr, maxOfTwo * curr));

			maxOfTwo = Math.max(maxOfTwo, max * curr);
			minOfTwo = Math.min(minOfTwo, min * curr);

			max = Math.max(max, curr);
			min = Math.min(min, curr);
		}

		return maxOfThree;
	}

	public static void main(String... args) {
		int[][] nums = new int[][]{
			{ 0, 0, 0},
			{ 2, 3, 4 },
			{ -10, -20, 5, 10, 12 },
			{ 2, 2, 2, 2, 2 }
		};

		for (int i = 0; i < nums.length; ++i) {
			System.out.println("#" + i + " In: " + Arrays.toString(nums[i]));
			System.out.println("#" + i + " Out: " + highestProduct(nums[i]));
			System.out.println();
		}
	}

}
