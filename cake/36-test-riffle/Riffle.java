/*
Task: test if a deck of cards (array) has been riffled once.
Riffle: when the deck is cut in half, then a random amount of cards from each half is alternately added to the shuffled deck until we're done.
*/

import java.util.*;

public class Riffle {

	public static boolean isRiffled(int[] deck) {
		int n = deck.length;
		if (n % 2 != 0) throw new IllegalArgumentException("Odd number of cards!");
		if (n == 0) throw new IllegalArgumentException("Empty deck!");

		int nextInHandA = 0;
		int nextInHandB = n / 2;
		for (int i = 0; i < n; ++i) {
			if (deck[i] == nextInHandA) {
				++nextInHandA;
			} else if (deck[i] == nextInHandB) {
				++nextInHandB;
			} else {
				return false;
			}
		}

		return true;
	}

	public static void main(String... args) {
		int[][] decks = {
			{0, 1, 2, 10, 3, 11, 12, 13, 14, 4, 5, 6, 15, 16, 7, 17, 18, 8, 19, 9},
			{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
			{0, 1, 3, 4, 7, 6, 5, 2}
		};
		boolean[] answers = {true, true, false};

		for (int i = 0; i < decks.length; ++i) {
			boolean answer = isRiffled(decks[i]);
			if (answer == answers[i]) {
				System.out.println("Correct");
			} else {
				System.out.println("Incorrect");
			}
		}
	}
}