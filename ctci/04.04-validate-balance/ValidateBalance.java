import java.util.*;

public class ValidateBalance {

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

	public boolean isBalanced() {
		return isBalancedHelper(root) != Integer.MIN_VALUE;
	}

	public int isBalancedHelper(Node n) {
		if (n == null) return 0;

		int leftHeight = isBalancedHelper(n.left);
		if (leftHeight == Integer.MIN_VALUE) return leftHeight;

		int rightHeight = isBalancedHelper(n.right);
		if (rightHeight == Integer.MIN_VALUE) return rightHeight;

		if (Math.abs(rightHeight - leftHeight) > 1) {
			return Integer.MIN_VALUE;
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	public static void main(String... args) {
		System.out.println("Input: [n] number of numbers, followed by n numbers in order");

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		ValidateBalance bst = new ValidateBalance();
		for (int i = 0; i < n; ++i) {
			bst.insert(in.nextInt());
		}

		System.out.println("Tree is" + (bst.isBalanced() ? "" : " not") + " balanced");
	}

}