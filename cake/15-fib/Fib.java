/*
Task: compute the nth Fibonacci number.
*/

public class Fib {

	public static int fib(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		int lastMinus2 = 0, lastMinus1 = 0, curr = 1;
		for (int i = 1; i < n; ++i) {
			lastMinus2 = lastMinus1;
			lastMinus1 = curr;
			curr = lastMinus1 + lastMinus2;
		}
		return curr;
	}

	public static void main(String... args) {
		int[][] testCases = { // [n, answer]
			{0, 0},
			{1, 1},
			{2, 1},
			{3, 2},
			{4, 3},
			{5, 5}
		};

		for (int i = 0; i < testCases.length; ++i) {
			int answer = fib(testCases[i][0]);
			if (answer == testCases[i][1]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + testCases[i][1] + ", got " + answer + ")");
			}
		}
	}
}