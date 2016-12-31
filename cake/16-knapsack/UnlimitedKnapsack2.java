/*
Task: given a set of cake weights and values, determine the maximum value that a weight-limited bag can hold.
Note: there is an unlimited supply of all cakes.
*/

import java.util.*;

public class UnlimitedKnapsack2 {

	public static class Cake {
		int weight, value;

		public Cake(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void findMaxValue(int capacity, ArrayList<Cake> cakes) {
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

		// filter cakes - only the most valuable cake in each weight is useful
		Map<Integer, Cake> bestCakes = new HashMap<>();
		for (Cake c : cakes) {
			if (bestCakes.containsKey(c.weight)) {
				if (bestCakes.get(c.weight).value < c.value) {
					bestCakes.put(c.weight, c);
				}
			} else {
				bestCakes.put(c.weight, c);
			}
		}
		int countBeforeFilter = cakes.size();
		cakes.clear();
		for (Map.Entry<Integer, Cake> e : bestCakes.entrySet()) {
			cakes.add(e.getValue());
		}
		if (cakes.size() < countBeforeFilter) {
			System.out.println("Filtered out " + (countBeforeFilter - cakes.size()) + " useless cake(s).");
		}

		// store the best possible value for each capacity from 0 to our goal
		long[] bestSoFar = new long[capacity + 1];
		Map<Integer, Map<Cake, Integer>> solutionsSoFar = new HashMap<>();
		solutionsSoFar.put(0, new HashMap<>()); 

		// work out the best value for each capacity from 1 to our goal
		// (implicit bestSoFar[0] = 0)
		for (int cap = 1; cap <= capacity; ++cap) {
			solutionsSoFar.put(cap, solutionsSoFar.get(cap - 1)); 

			for (Cake c : cakes) {
				if (c.weight <= cap) {
					long valueUsingC = bestSoFar[cap - c.weight] + c.value;
					if (valueUsingC > bestSoFar[cap]) {
						bestSoFar[cap] = valueUsingC;
						
						Map<Cake, Integer> solution = new HashMap<>(solutionsSoFar.get(cap - c.weight));
						int prevCount = 0;
						if (solution.containsKey(c)) prevCount = solution.get(c);
						solution.put(c, prevCount + 1);

						solutionsSoFar.put(cap, solution);
					}
				}
			}
		}

		System.out.println("Best value: " + bestSoFar[capacity]);

		System.out.println("Made up of:");
		Map<Cake, Integer> solution = solutionsSoFar.get(capacity);
		for (Map.Entry<Cake, Integer> e : solution.entrySet()) {
			Cake cake = e.getKey();
			int qty = e.getValue();
			System.out.println("  " + qty + "  x  W: " + cake.weight + ", V: " + cake.value);
		}
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