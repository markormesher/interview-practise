/*
Task: determine the maximum possible profit that can be made with one buy and one sell action, given
a list of stock prices. The buy and sell must be on different days, and must be in order.
*/

import java.util.*;

public class Stocks {

	private static int maxProfit(int[] prices) {
		int n = prices.length;
		if (n < 2) throw new IllegalArgumentException("");

		int maxProfitSoFar = prices[1] - prices[0];
		int lowestSoFar = prices[0];

		for (int i = 1; i < n; ++i) {
			maxProfitSoFar = Math.max(maxProfitSoFar, prices[i] - lowestSoFar);
			lowestSoFar = Math.min(lowestSoFar, prices[i]);
		}

		return maxProfitSoFar;
	}

	public static void main(String... args) {
		int[][] nums = new int[][]{
			{ 2, 1, 3, 4 },
			{ 1, 2, 3, 4, 5 },
			{ 1, 0 },
			{ 0, 1 },
			{ 2, 2, 2, 2, 2 }
		};

		for (int i = 0; i < nums.length; ++i) {
			System.out.println("#" + i + " In: " + Arrays.toString(nums[i]));
			System.out.println("#" + i + " Out: " + maxProfit(nums[i]));
			System.out.println();
		}
	}

}
