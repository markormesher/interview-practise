/*
Task: find all subsets of size k.
*/

import java.util.*;

public class AllKSubsets {

	public static List<List<Integer>> subsetsOfSize(List<Integer> arr, int size) {
		List<List<Integer>> solutions = new ArrayList<>();
		List<Integer> current = new ArrayList<>(size);
		innerSubsetsOfSize(arr, size, 0, current, solutions);
		return solutions;
	}
	
	public static void innerSubsetsOfSize(List<Integer> arr, int size, int index, List<Integer> current, List<List<Integer>> solutions) {
		if (current.size() == size) {
			List<Integer> currentClone = new ArrayList<>(current.size());
			currentClone.addAll(current);
			solutions.add(currentClone);
			return;
		}
		
		if (index == arr.size()) return;
		
		// for the item at 'index', we either add it...
		current.add(arr.get(index));
		innerSubsetsOfSize(arr, size, index + 1, current, solutions);
		
		// ...or we dont
		current.remove(arr.get(index));
		innerSubsetsOfSize(arr, size, index + 1, current, solutions);
	}
	
	public static void main(String... args) {
		List<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		arr.add(6);
		System.out.println(subsetsOfSize(arr, 1));
		System.out.println(subsetsOfSize(arr, 2));
		System.out.println(subsetsOfSize(arr, 3));
		System.out.println(subsetsOfSize(arr, 4));
		System.out.println(subsetsOfSize(arr, 5));
	}
	
}