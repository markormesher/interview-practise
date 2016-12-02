import java.util.*;

public class InsertNumber {

	private static String asBinary(int i) {
		String str = Integer.toBinaryString(i);
		for (int j = str.length(); j < 32; ++j) {
			str = "0" + str;
		}

		return str.substring(0, 8) + " "
			+ str.substring(8, 16) + " "
			+ str.substring(16, 24) + " "
			+ str.substring(24, 32);
	}

	private static int insert(int n, int m, int i, int j) {
		// make the mask of all 1s, but with 0s in positions i through j
		int leftMask = ~0 << (j + 1);
		int rightMask = (1 << i) - 1;
		int mask = leftMask | rightMask;

		// clear i through j in n, then shift and insert m
		int nCleared = n & mask;
		int mShifted = m << i;
		return nCleared | mShifted;
	}

	public static void main(String... args) {
		System.out.println("Input: 4 numbers, n m i j");

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int i = in.nextInt();
		int j = in.nextInt();

		System.out.println("n = " + asBinary(n));
		System.out.println("m = " + asBinary(m));
		System.out.println("\nOutput:");
		System.out.println("    " + asBinary(insert(n, m, i, j)));
	}

}