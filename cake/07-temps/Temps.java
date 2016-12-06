/*
Task: implement a temp recorder to quickly return min, max, mean and mode.
Optimise for getters; insert can be slower.
*/

import java.util.*;

public class Temps {

	public static class TempRecorder {
		
		private boolean empty = true;
		private int min = -1;
		private int max = -1;
		private double mean = 0.0;
		private int mode = -1;

		private int count = 0;
		private HashMap<Integer, Integer> counts = new HashMap<>();
		private int highestCountSoFar = 0;

		void insert(int t) {
			// min and max
			if (empty) {
				min = t;
				max = t;
				empty = false;
			} else {
				min = Math.min(min, t);
				max = Math.max(max, t);
			}

			// mean
			mean = ((mean * count) + t) / (count + 1);
			++count;

			// mode counts
			int tCount;
			if (counts.containsKey(t)) {
				tCount = counts.get(t) + 1;
			} else {
				tCount = 1;
			}
			counts.put(t, tCount);
			if (tCount > highestCountSoFar) {
				highestCountSoFar = tCount;
				mode = t;
			}
		}

		int getMin() {
			return min;
		}

		int getMax() {
			return max;
		}

		double getMean() {
			return mean;
		}

		int getMode() {
			return mode;
		}

	}

	public static void main(String... args) {
		int[] temps = new int[]{
			1, 2, 3, 3, 4, 5, 6
		};

		TempRecorder tr = new TempRecorder();

		for (int i = 0; i < temps.length; ++i) {
			System.out.println();
			System.out.println("INSERT " + temps[i]);
			tr.insert(temps[i]);
			System.out.println("  min  = " + tr.getMin());
			System.out.println("  max  = " + tr.getMax());
			System.out.println("  mean = " + tr.getMean());
			System.out.println("  mode = " + tr.getMode());
		}
	}

}