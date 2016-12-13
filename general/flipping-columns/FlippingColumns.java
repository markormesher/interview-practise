/*
Task: given a matrix of 1s and 0s, determine the maximum number of all-1 rows that can be created by "flipping" exactly k columns.
*/

import java.util.*;

public class FlippingColumns {

	public static int bestNumberOfAllOneRows(int[][] matrix, int k) {
		// idea: performing the same set of flips to any number of rows is akin to a one-to-one function, mapping input
		// rows to output rows; the same input will create the same output. only certain rows can be made all-1, so we
		// can count the number of times that useable row patterns appear, and see which one appears the most.

		// store the number of times a specific, useable row pattern has been seen
		Map<String, Integer> rowPatternCounts = new HashMap<>();
		int bestPatternCount = 0;

		// loop through rows
		for (int row = 0; row < matrix.length; ++row) {

			// at this point we could just check if we've seen the row before (and therefore already know that it's useful) and
			// increment its score, but that's less efficient: computing the key and looking it up is at least a few O(n) operations;
			// counting zeros and checking usefulness is a single O(n) operation and some maths, so we save computation on iterations
			// for non-useful columns.

			// count the number of zeros in this row
			int zeroCount = 0;
			for (int col = 0; col < matrix[row].length; ++col) {
				if (matrix[row][col] == 0) ++zeroCount;
			}

			// this row pattern can be made all-1s if the number of zeros is equal to k (i.e. flip them all)
			// or the number of zeros is less than k and an even number of flips are left over, which can be
			// burnt up by flipping the same column (i. e. not changing its value).

			// check if the row is useable
			if (zeroCount == k || (k - zeroCount >= 0 && (k - zeroCount) % 2 == 0)) {
				// increment the counter for this row pattern, keeping the best counter seen so far
				String rowPattern = Arrays.toString(matrix[row]);
				int countForRowPattern;
				if (rowPatternCounts.containsKey(rowPattern)) {
					countForRowPattern = rowPatternCounts.get(rowPattern) + 1;
				} else {
					countForRowPattern = 1;
				}
				rowPatternCounts.put(rowPattern, countForRowPattern);
				if (countForRowPattern > bestPatternCount) bestPatternCount = countForRowPattern;
			}
		}

		return bestPatternCount;
	}

	public static void main(String... args) {
		int[][][] matrices = {
			{
				{1, 1, 0},
				{0, 1, 0},
				{0, 0, 0}
			},
			{
				{1, 1, 0, 1, 1, 1},
				{0, 1, 1, 1, 1, 1}
			},
			{
				{1, 1, 0, 1, 1, 1},
				{0, 1, 1, 1, 1, 1}
			},
			{
				{0, 1, 0, 1, 1, 1},
				{0, 1, 1, 1, 1, 1},
				{0, 1, 0, 1, 1, 1}
			},
			{
				{0, 1, 0, 1, 1, 1},
				{0, 1, 1, 1, 1, 1},
				{0, 1, 0, 1, 1, 1}
			}
		};

		int[] kValues = {
			1, 1, 2, 2, 3
		};

		int[] answers = {
			1, 1, 0, 2, 1
		};

		for (int i = 0; i < matrices.length; ++i) {
			int answer = bestNumberOfAllOneRows(matrices[i], kValues[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + answers[i] + ", got " + answer + ")");
			}
		}
	}

}