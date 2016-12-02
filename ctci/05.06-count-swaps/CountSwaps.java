import java.util.*;

public class CountSwaps {

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

	public static int countSwaps(int n1, int n2) {
		int bitsToSwap = n1 ^ n2;
		int bitCount = 0;
		while (bitsToSwap > 0) {
			if ((bitsToSwap & 1) == 1) ++bitCount;
			bitsToSwap >>>= 1;
		}
		return bitCount;
	}

	public static void main(String... args) {
		System.out.println("Input: number1 number2");

		Scanner in = new Scanner(System.in);
		int n1 = in.nextInt();
		int n2 = in.nextInt();

		System.out.println("\nNumber 1: " + asBinary(n1));
		System.out.println("Number 2: " + asBinary(n2));
		System.out.println("   Swaps: " + countSwaps(n1, n2));
	}

}
