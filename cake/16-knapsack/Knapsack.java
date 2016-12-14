/*
Task: given a set of cake weights and values, determine the maximum value that a weight-limited bag can hold.
*/

import java.util.*;

public class Knapsack {

	public static int maxBagValue(int[][] cakes, int capacity) {
		int[] maxValueAtWeight = new int[capacity + 1];
		maxValueAtWeight[0] = 0;
		for (int cap = 1; cap <= capacity; ++cap) {
			for (int c = 0; c < cakes.length; ++c) {
				int cWeight = cakes[c][0];
				int cValue = cakes[c][1];

				if (cWeight == 0 && cValue > 0) {
					return -1; // value would be infinity; normally we'd throw an exception here
				}

				if (cWeight <= cap) {
					int maxWithCake = cValue + maxValueAtWeight[cap - cWeight];
					maxValueAtWeight[cap] = Math.max(maxValueAtWeight[cap], maxWithCake);
				}
			}
		}

		return maxValueAtWeight[capacity];
	}

	public static void main(String... args) {
		int[][][] cakes = { // cake = [weight, value]
			{ {7, 160}, {3, 90}, {2, 15} },
			{ {1, 10} },
			{ {5, 10} },
			{ {1, 10}, {0, 10}, {10, 0}, {0, 0} }
		};
		int[] capacities = {
			20,
			0,
			8,
			10
		};
		int[] answers = {
			555,
			0,
			10,
			-1
		};

		for (int i = 0; i < cakes.length; ++i) {
			int answer = maxBagValue(cakes[i], capacities[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + answers[i] + ", got " + answer + ")");
			}
		}
	}

}