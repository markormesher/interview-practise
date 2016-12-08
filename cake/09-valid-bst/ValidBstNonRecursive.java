/*
Task: check if a binary tree is a valid binary search tree.
*/

import java.util.*;

public class ValidBstNonRecursive {

	public static class BstNode {
		int value;
		BstNode left, right;

		public BstNode(int value) {
			this.value = value;
		}
	}

	public static class BstNodeAndBounds {
		BstNode node;
		int min, max;

		public BstNodeAndBounds(BstNode node, int min, int max) {
			this.node = node;
			this.min = min;
			this.max = max;
		}
	}

	public static boolean isValid(BstNode root) {
		// depth-first search
		Stack<BstNodeAndBounds> open = new Stack<>();
		open.push(new BstNodeAndBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

		while (!open.isEmpty()) {
			BstNodeAndBounds curr = open.pop();
			if (curr.node.value < curr.min || curr.node.value > curr.max) return false;
			if (curr.node.left != null) open.push(new BstNodeAndBounds(curr.node.left, curr.min, curr.node.value - 1));
			if (curr.node.right != null) open.push(new BstNodeAndBounds(curr.node.right, curr.node.value, curr.max));
		}

		return true;
	}

	public static boolean isValid(BstNode node, int min, int max) {
		if (node.value < min || node.value > max) return false;
		if (node.left != null && !isValid(node.left, min, node.value - 1)) return false;
		if (node.right != null && !isValid(node.right, node.value, max)) return false;
		return true;
	}

	public static void main(String... args) {
		BstNode[] testCases = new BstNode[6];
		boolean[] answers = new boolean[6];

		// single node (valid)
		answers[0] = true;
		testCases[0] = new BstNode(1);

		// complete 2-level tree (valid)
		answers[1] = true;
		testCases[1] = new BstNode(2);
		testCases[1].left = new BstNode(1);
		testCases[1].right = new BstNode(3);

		// root, 1 child on the left (valid)
		answers[2] = true;
		testCases[2] = new BstNode(2);
		testCases[2].left = new BstNode(1);

		// complete 3-level tree (valid)
		answers[3] = true;
		testCases[3] = new BstNode(4);
		testCases[3].left = new BstNode(2);
		testCases[3].left.left = new BstNode(1);
		testCases[3].left.right = new BstNode(3);
		testCases[3].right = new BstNode(6);
		testCases[3].right.left = new BstNode(5);
		testCases[3].right.right = new BstNode(7);

		// complete 3-level tree (valid subtrees)
		answers[4] = false;
		testCases[4] = new BstNode(50);
		testCases[4].left = new BstNode(25);
		testCases[4].left.left = new BstNode(20);
		testCases[4].left.right = new BstNode(60); // !!
		testCases[4].right = new BstNode(75);
		testCases[4].right.left = new BstNode(70);
		testCases[4].right.right = new BstNode(80);

		// complete 3-level tree (invalid)
		answers[5] = false;
		testCases[5] = new BstNode(1);
		testCases[5].left = new BstNode(2);
		testCases[5].left.left = new BstNode(3);
		testCases[5].left.right = new BstNode(4);
		testCases[5].right = new BstNode(5);
		testCases[5].right.left = new BstNode(6);
		testCases[5].right.right = new BstNode(7);

		for (int i = 0; i < testCases.length; ++i) {
			System.out.println("Is tree #" + i + " valid? " + (isValid(testCases[i]) ? "YES" : "NO"));
			System.out.println("   " + (isValid(testCases[i]) == answers[i] ? "CORRECT" : "WRONG"));
			System.out.println();
		}
	}

}