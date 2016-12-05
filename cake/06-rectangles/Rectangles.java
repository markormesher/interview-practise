/*
Task: find the overlap of two rectanges.
Assumption: both rectangles are at least 11.
*/

import java.util.*;

public class Rectangles {

	public static class Rect {
		int left, top, right, bottom;
		public Rect(int left, int top, int right, int bottom) {
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
		}

		public String toString() {
			return String.format("(%d, %d -- %d, %d)", left, top, right, bottom);
		}
	}

	public static Rect findOverlap(Rect r1, Rect r2) {
		// totally outside?
		if (r1.left >= r2.right || r2.left >= r1.right) return null;
		if (r1.top >= r2.bottom || r2.top >= r1.bottom) return null;

		// find bounds
		int overlapLeft = Math.max(r1.left, r2.left);
		int overlapTop = Math.max(r1.top, r2.top);
		int overlapRight = Math.min(r1.right, r2.right);
		int overlapBottom = Math.min(r1.bottom, r2.bottom);

		return new Rect(overlapLeft, overlapTop, overlapRight, overlapBottom);
	}

	public static void main(String... args) {
		ArrayList<Rect> rects = new ArrayList<Rect>(){{
			add(new Rect(0, 0, 10, 10)); add(new Rect(5, 5, 15, 15)); // over lap
			add(new Rect(0, 0, 5, 5)); add(new Rect(6, 6, 10, 10)); // no over lap
			add(new Rect(0, 0, 5, 5)); add(new Rect(5, 5, 10, 10)); // shared corner
			add(new Rect(0, 0, 5, 5)); add(new Rect(5, 0, 10, 10)); // shared edge
			add(new Rect(0, 0, 10, 10)); add(new Rect(3, 3, 6, 8)); // contained
		}};

		for (int i = 0; i < rects.size(); i+= 2) {
			System.out.println("#" + i + " In: " + rects.get(i) + " and " + rects.get(i + 1));
			Rect result = findOverlap(rects.get(i), rects.get(i + 1));
			System.out.println("#" + i + " Out: " + (result == null ? "no overlap" : result));
			System.out.println();
		}
	}

}