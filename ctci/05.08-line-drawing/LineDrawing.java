import java.util.*;

public class LineDrawing {

	private static void printScreen(byte[] screen, int width, int height) {
		for (int i = 0; i < width; ++i) {
			System.out.print(i % 10);
		}
		System.out.println();
		for (int row = 0; row < height; ++row) {
			for (int col = 0; col < width; ++col) {
				byte byteSegment = screen[((width / 8) * row) + (col / 8)];
				int bitOffset = col % 8;
				boolean bitIsSet = (byteSegment & (1 << bitOffset)) != 0;
				if (bitIsSet) {
					System.out.print("▓");
				} else {
					System.out.print("░");
				}
			}
			System.out.println(" " + row);
		}
	}

	private static byte[] setPixel(byte[] screen, int width, int x, int y) {
		int byteSegmentIndex = ((width / 8) * y) + (x / 8);
		int bitOffset = x % 8;
		byte mask = (byte) (1 << bitOffset);
		screen[byteSegmentIndex] = (byte) (screen[byteSegmentIndex] | mask);
		return screen;
	}

	private static byte[] drawLine(byte[] screen, int width, int x1, int y1, int x2, int y2) {
		int xDiff = x2 - x1;
		int yDiff = y2 - y1;
		int n = xDiff * yDiff;
		for (double i = 0; i <= n; ++i) {
			int x = x1 + (int) Math.round(xDiff * (i / n));
			int y = y1 + (int) Math.round(yDiff * (i / n));
			screen = setPixel(screen, width, x, y);
		}
		return screen;
	}

	public static void main(String... args) {
		System.out.println("Input: width height x1 y1 x2 y2");
		Scanner in = new Scanner(System.in);

		// collect input
		int width = in.nextInt();
		int height = in.nextInt();
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int x2 = in.nextInt();
		int y2 = in.nextInt();

		// valid input
		if (width % 8 != 0) {
			System.err.println("Error: width must be a multiple of 8");
			return;
		}
		if (x1 < 0 || x1 >= width || x2 < 0 || x2 >= width) {
			System.err.println("Error: x coordinates must be between 0 and " + (width - 1));
			return;
		}
		if (y1 < 0 || y1 >= height || y2 < 0 || y2 >= height) {
			System.err.println("Error: y coordinates must be between 0 and " + (height - 1));
			return;
		}

		// create array
		byte[] blankScreen = new byte[(width / 8) * height];
		byte[] screenWithLine = drawLine(blankScreen, width, x1, y1, x2, y2);

		System.out.println("\nOutput:");
		printScreen(screenWithLine, width, height);
	}

}