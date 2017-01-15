/*
Task: given a row of parking spaces where one is empty, find the minimum number of moves needed to sort the cars.
Eg: In: 3, 4, 1, 5, _, 2
   Out: 1, 2, 3, 4, 5, _
*/

import java.util.*;

public class Cars {

	public static final int EMPTY = -1;

	public static void printArray(int[] spaces) {
		System.out.println(Arrays.toString(spaces).replace(new Integer(EMPTY).toString(), "_"));
	}

	public static void solve(int[] spaces) {
		Set<Integer> unsortedCars = new HashSet<>();
		Map<Integer, Integer> positions = new HashMap<>();

		int endPosition = spaces.length - 1;

		for (int i = 0; i < spaces.length; ++i) {
			positions.put(spaces[i], i);
			if (spaces[i] != EMPTY && spaces[i] != i + 1) {
				unsortedCars.add(spaces[i]);
			}
		}

		printArray(spaces);

		int moves = 0;

		while (!unsortedCars.isEmpty()) {
			System.out.println();

			int carToMove;
			if (positions.get(EMPTY) == endPosition) {
				carToMove = unsortedCars.iterator().next();
			} else {
				carToMove = positions.get(EMPTY) + 1;
			}

			int fromPosition = positions.get(carToMove);
			int toPosition = positions.get(EMPTY);

			System.out.println("Move car " + carToMove + " from " + fromPosition + " to " + toPosition);

			spaces[toPosition] = carToMove;
			spaces[fromPosition] = EMPTY;

			positions.put(carToMove, toPosition);
			positions.put(EMPTY, fromPosition);

			++moves;

			if (carToMove == toPosition + 1) {
				unsortedCars.remove(carToMove);
			}

			printArray(spaces);
		}

		System.out.println(moves + " moves");
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Arguments: <n> <position 1> <position 2> ... <position n>");
			System.err.println("Cars must be in sequence, 1 to n-1. Use -1 for empty spot.");
			System.exit(1);
		}

		int spaces = Integer.parseInt(args[0]);
		int[] original = new int[spaces];
		for (int i = 1; i <= spaces; ++i) {
			original[i - 1] = Integer.parseInt(args[i]);
		}

		solve(original);
	}

}