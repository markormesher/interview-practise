import java.util.*;

public class PairwiseSwap {

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

	public static int pairwiseSwap(int n) {
		int evenBitMask = 0xAAAAAAAA; // ...101010
		int oddBitMask = evenBitMask >>> 1; // ...010101
		int evenBits = n & evenBitMask;
		int oddBits = n & oddBitMask;
		evenBits = evenBits >>> 1;
		oddBits = oddBits << 1;
		return evenBits | oddBits;
	}

	public static int pairwiseSwapOneLiner(int n) {
		// almost entirely unreadable - don't use
		return ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
	}

	public static void main(String... args) {
		System.out.println("Input: number");

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println("\nBefore: " + asBinary(n));
		System.out.println("After:  " + asBinary(pairwiseSwap(n)));
		System.out.println("After:  " + asBinary(pairwiseSwapOneLiner(n)));
	}

}