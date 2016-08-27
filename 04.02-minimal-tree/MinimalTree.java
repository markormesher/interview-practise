import java.util.*;

public class MinimalTree {

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

	public int maxHeight() {
		return root == null ? 0 : maxHeightHelper(root, 1);
	}

	private int maxHeightHelper(Node n, int height) {
		int leftHeight = n.left == null ? 0 : maxHeightHelper(n.left, height + 1);
		int rightHeight = n.right == null ? 0 : maxHeightHelper(n.right, height + 1);
		return Math.max(height, Math.max(leftHeight, rightHeight));
	}

	/* Less efficient, because insert() takes O(log n) */
	public void insertOptimally(int[] numbers) {
		insertOptimallyHelper(numbers, 0, numbers.length - 1);
	}

	private void insertOptimallyHelper(int[] numbers, int start, int end) {
		if (start == end) {
			insert(numbers[start]);
		} else {
			int mid = (start + end) / 2;
			insert(numbers[mid]);
			insertOptimallyHelper(numbers, start, mid - 1);
			insertOptimallyHelper(numbers, mid + 1, end);
		}
	}

	/* More efficient! */
	public static MinimalTree createOptimalTree(int[] numbers) {
		MinimalTree mt = new MinimalTree();
		mt.root = createOptimalTreeHelper(numbers, 0, numbers.length - 1);
		return mt;
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

	public static void main(String... args) {
		System.out.println("Input: [n] number of numbers, followed by n numbers in order");

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; ++i) {
			nums[i] = in.nextInt();
		}

		MinimalTree mt1 = new MinimalTree();
		mt1.insertOptimally(nums);
		System.out.println("\nOutput with repeated insert:");
		System.out.println("Maximum height is " + mt1.maxHeight());

		MinimalTree mt2 = MinimalTree.createOptimalTree(nums);
		System.out.println("\nOutput with recursive building:");
		System.out.println("Maximum height is " + mt2.maxHeight());
	}

}