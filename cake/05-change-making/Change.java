import java.util.*;

public class Change {

	public static ArrayList<ArrayList<Integer>> makeChange(int target, ArrayList<Integer> coins) {
		return makeChange(0, target, coins, new ArrayList<>(), 0);
	}

	public static ArrayList<ArrayList<Integer>> makeChange(int sum, int target, ArrayList<Integer> coins, ArrayList<Integer> selected, int lastSelected) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (sum == target) {
			ArrayList<Integer> clone = new ArrayList<>();
			clone.addAll(selected);
			result.add(clone);
			return result;
		} else if (sum > target) {
			return result;
		}

		for (Integer i : coins) {
			if (i < lastSelected) continue;
			if (i <= target) {
				selected.add(i);
				result.addAll(makeChange(sum + i, target, coins, selected, i));
				selected.remove((Integer) i);
			}
		}

		return result;
	}

	public static void main(String... args) {
		int[] targets = new int[]{4};
		ArrayList<ArrayList<Integer>> coins = new ArrayList<ArrayList<Integer>>(){{
			add(new ArrayList<Integer>(){{
				add(1);
				add(2);
				add(3);
			}});
		}};

		for (int i = 0; i < targets.length; ++i) {
			System.out.println("#" + i + " In: " + targets[i] + " from " + coins.get(i));
			System.out.println("#" + i + " Out: " + makeChange(targets[i], coins.get(i)));
			System.out.println();
		}
	}

}