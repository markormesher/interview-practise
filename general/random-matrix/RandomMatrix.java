/*
Task: create a random NxM matrix containing only the elements 1 to p.
Contiguous runs of 3 or more elements (horizontally or vertically) are not allowed.
Essentially, a Candy Crush game board.
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMatrix {

	public static int getValidNumber(int[][] matrix, int r, int c, int p) {
		int invalid1 = -1;
		if (r > 1) {
			if (matrix[r - 1][c] == matrix[r - 2][c]) {
				invalid1 = matrix[r - 1][c];
			}
		}

		int invalid2 = -1;
		if (c > 1) {
			if (matrix[r][c - 1] == matrix[r][c - 2]) {
				invalid2 = matrix[r][c - 1];
			}
		}

		int candidate = invalid1;
		while (candidate == invalid1 || candidate == invalid2) {
			candidate = ThreadLocalRandom.current().nextInt(1, p + 1);
		}

		return candidate;
	}

	public static int[][] generate(int n, int m, int p) {
		int[][] matrix = new int[m][n];

		int[] counts = new int[p];

		for (int r = 0; r < m; ++r) {
			for (int c = 0; c < n; ++c) {
				int number = getValidNumber(matrix, r, c, p);
				matrix[r][c] = number;
				++counts[number - 1];
			}
		}

		System.out.println(Arrays.toString(counts));

		return matrix;
	}

	public static void main(String... args) {
		if (args.length != 3) {
			System.err.println("Call format: n m p");
		}

		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		int p = Integer.parseInt(args[2]);

		if (n <= 0) {
			System.err.println("n must be greater than 0");
			System.exit(1);
		}
		if (m <= 0) {
			System.err.println("m must be greater than 0");
			System.exit(1);
		}
		if (p <= 2) {
			System.err.println("p must be greater than 2");
			System.exit(1);
		}

		int[][] matrix = generate(n, m, p);

		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}