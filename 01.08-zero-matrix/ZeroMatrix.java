import java.util.*;

public class ZeroMatrix {

	private static void printMatrix(int[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				System.out.print(matrix[col][row] + "  ");
			}
			System.out.println();
		}
	}

	private static int[][] zeroMatrix(int[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (matrix[row][col] == 0) {
					matrix[row][0] = 0;
					matrix[0][col] = 0;
				}
			}
		}

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				if (matrix[row][0] == 0 || matrix[0][col] == 0) {
					matrix[row][col] = 0;
				}
			}
		}

		return matrix;
	}

	public static void main(String... args) {
		Scanner in = new Scanner(System.in);
		int width = in.nextInt();
		int height = in.nextInt();

		int[][] matrix = new int[height][width];

		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				matrix[col][row] = in.nextInt();
			}
		}

		System.out.println("\nInput matrix:");
		printMatrix(matrix);

		System.out.println("\nOutput matrix:");
		printMatrix(zeroMatrix(matrix));		
	}

}