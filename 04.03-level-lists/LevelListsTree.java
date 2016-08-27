import java.util.*;

public class LevelListsTree {

	public static class Pair<X, Y> {
		X first;
		Y second;

		public Pair(X first, Y second) {
			this.first = first;
			this.second = second;
		}
	}

	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int v) {
			value = v;
			left = null;
			right = null;
		}
	}

	private Node root;

	public void insert(int i) {
		// find node or would-be parent for i
		Node candidate = findNodeOrParent(i, root, null);

		if (candidate == null) {
			// new root
			root = new Node(i);
		} else if (candidate.value == i) {
			// already in the tree
			return;
		} else if (candidate.value < i) {
			// append as right child
			candidate.right = new Node(i);
		} else if (candidate.value > i) {
			// append as left child
			candidate.left = new Node(i);
		}
	}

	private Node findNodeOrParent(int i, Node n, Node prev) {
		if (n == null) return prev;
		if (n.value == i) return n;
		if (n.value < i) {
			return findNodeOrParent(i, n.right, n);
		} else {
			return findNodeOrParent(i, n.left, n);
		}
	}

	public Node find(int i) {
		Node candidate = findNodeOrParent(i, root, null);
		if (candidate == null || candidate.value != i) return null;
		return candidate;
	}

	/*
	Borrowed from 04.02
	*/
	public static LevelListsTree createOptimalTree(int[] numbers) {
		LevelListsTree llt = new LevelListsTree();
		llt.root = createOptimalTreeHelper(numbers, 0, numbers.length - 1);
		return llt;
	}

	private static Node createOptimalTreeHelper(int[] numbers, int start, int end) {
		if (start == end) {
			return new Node(numbers[start]);
		} else {
			int mid = (start + end) / 2;
			Node node = new Node(numbers[mid]);
			node.left = createOptimalTreeHelper(numbers, start, mid - 1);
			node.right = createOptimalTreeHelper(numbers, mid + 1, end);
			return node;
		}
	}

	public ArrayList<ArrayList<Integer>> getLevelLists() {
		ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

		LinkedList<Pair<Node, Integer>> queue = new LinkedList<>();
		if (root != null) queue.addLast(new Pair<>(root, 0));
		while (!queue.isEmpty()) {
			Pair<Node, Integer> next = queue.removeFirst();
			if (next.second == lists.size()) {
				lists.add(new ArrayList<Integer>());
			}

			lists.get(next.second).add(next.first.value);
			if (next.first.left != null) queue.addLast(new Pair<>(next.first.left, next.second + 1));
			if (next.first.right != null) queue.addLast(new Pair<>(next.first.right, next.second + 1));
		}

		return lists;
	}

	public static void main(String... args) {
		System.out.println("Input: [n] number of numbers, followed by n numbers in order");

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; ++i) {
			nums[i] = in.nextInt();
		}

		LevelListsTree llt = LevelListsTree.createOptimalTree(nums);

		System.out.println();
		ArrayList<ArrayList<Integer>> levelLists = llt.getLevelLists();
		for (int i = 0; i < levelLists.size(); ++i) {
			System.out.print("Level " + i + ":");
			for (int j : levelLists.get(i)) {
				System.out.print(" " + j);
			}
			System.out.println();
		}
	}

}