/*
Task: given a set of cake weights and values, determine the maximum value that a weight-limited bag can hold.
Note: one of each cake exists (0-1 property).
*/

import java.util.*;

public class ZeroOneKnapsack {

	public static class Cake {
		int weight, value;

		public Cake(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void findMaxValue(int capacity, ArrayList<Cake> cakes) {
		int cakeCount = cakes.size();

		// infinity?
		for (Cake c : cakes) {
			if (c.weight == 0 && c.value > 0) {
				System.out.println("Best value: infinity");
				return;
			}
		}

		// zero?
		if (capacity == 0) {
			System.out.println("Best value: 0");
		}

		// store the best possible value for each capacity from 0 to our goal
		long[][] bestSoFar = new long[capacity + 1][cakeCount + 1];

		// store solutions
		Map<Integer, Map<Cake, Integer>> solutionsSoFar = new HashMap<>();
		solutionsSoFar.put(0, new HashMap<>()); 

		// work out the best value for each "cap" from 1 to our goal, using the first "i" cakes
		for (int cap = 1; cap <= capacity; ++cap) {
			for (int i = 1; i <= cakeCount; ++i) {
				Cake cakeI = cakes.get(i - 1);

				if (cakeI.weight > cap) {
					// cake wouldn't fit anyway, so copy the best value of the first "i-1" cakes
					bestSoFar[cap][i] = bestSoFar[cap][i - 1];
				} else {
					long valueWithCake = cakeI.value + bestSoFar[cap - cakeI.weight][i - 1];
					long valueWithoutCake = bestSoFar[cap][i - 1];

					bestSoFar[cap][i] = valueWithCake > valueWithoutCake ? valueWithCake : valueWithoutCake;
				}
			}
		}
		

		System.out.println("Best value: " + bestSoFar[capacity][cakeCount]);

		//System.out.println("Made up of:");
		//Map<Cake, Integer> solution = solutionsSoFar.get(capacity);
		//for (Map.Entry<Cake, Integer> e : solution.entrySet()) {
		//	Cake cake = e.getKey();
		//	int qty = e.getValue();
		//	System.out.println("  " + qty + "  x  W: " + cake.weight + ", V: " + cake.value);
		//}
	}

	public static void main(String... args) {
		if (args.length < 4) {
			System.err.println("Input: [capacity] [no. cakes] [c0 weight] [c0 value] [c1 weight] [c1 value] ...");
			return;
		}

		int capacity = Integer.parseInt(args[0]);
		if (capacity < 0) {
			System.err.println("Cannot have negative capacity!");
			return;
		}

		int cakeCount = Integer.parseInt(args[1]);
		if (cakeCount != (args.length - 2) / 2) {
			System.err.println("Input: [capacity] [no. cakes] [c0 weight] [c0 value] [c1 weight] [c1 value] ...");
			return;	
		}

		ArrayList<Cake> cakes = new ArrayList<>(cakeCount);
		for (int i = 0; i < cakeCount; ++i) {
			int weight = Integer.parseInt(args[(i * 2) + 2]);
			int value = Integer.parseInt(args[(i * 2) + 3]);
			if (weight < 0) {
				System.err.println("Cannot have negative-weight cake!");
				return;
			}
			if (value < 0) {
				System.err.println("Cannot have negative-value cake!");
				return;
			}
			cakes.add(new Cake(weight, value));
		}

		findMaxValue(capacity, cakes);
	}

}