/*
Task: check if a binary tree is super-balanced (the difference between the depth of any two lead nodes is at most 1).
*/

import java.util.*;

public class SuperBalanced {

	public static class BTNode {
		int value;
		BTNode left, right;

		public BTNode(int value) {
			this.value = value;
		}
	}

	public static class BTNodeAndDepth {
		BTNode node;
		int depth;

		public BTNodeAndDepth(BTNode node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}

	public static boolean isSuperBalanced(BTNode root) {

		int minDepth = -1;
		int maxDepth = -1;

		// set up for depth-first walks
		Stack<BTNodeAndDepth> openList = new Stack<>();
		openList.push(new BTNodeAndDepth(root, 0));
		while (!openList.isEmpty()) {
			BTNodeAndDepth curr = openList.pop();
			BTNode node = curr.node;
			int depth = curr.depth;

			// at a leaf?
			if (node.left == null && node.right == null) {
				// update known bounds
				if (minDepth < 0 || depth < minDepth) minDepth = depth;
				if (maxDepth < 0 || depth > maxDepth) maxDepth = depth;

				// check
				if (maxDepth - minDepth > 1) return false;
			}

			if (node.left != null) openList.push(new BTNodeAndDepth(node.left, depth + 1));
			if (node.right != null) openList.push(new BTNodeAndDepth(node.right, depth + 1));
		}

		return true;
	}

	public static void main(String... args) {
		BTNode[] testCases = new BTNode[6];
		boolean[] answers = new boolean[6];

		// single node
		answers[0] = true;
		testCases[0] = new BTNode(1);

		// complete 2-level tree
		answers[1] = true;
		testCases[1] = new BTNode(2);
		testCases[1].left = new BTNode(1);
		testCases[1].right = new BTNode(3);

		// root, 1 child on the left
		answers[2] = true;
		testCases[2] = new BTNode(2);
		testCases[2].left = new BTNode(1);

		// complete 3-level tree
		answers[3] = true;
		testCases[3] = new BTNode(4);
		testCases[3].left = new BTNode(2);
		testCases[3].left.left = new BTNode(1);
		testCases[3].left.right = new BTNode(3);
		testCases[3].right = new BTNode(6);
		testCases[3].right.left = new BTNode(5);
		testCases[3].right.right = new BTNode(7);

		// root, with length-1 path on the left and length-2 path on the right
		answers[4] = true;
		testCases[4] = new BTNode(2);
		testCases[4].left = new BTNode(1);
		testCases[4].right = new BTNode(3);
		testCases[4].right.right = new BTNode(4);

		// root, with length-1 path on the left and length-3 path on the right
		answers[5] = false;
		testCases[5] = new BTNode(2);
		testCases[5].left = new BTNode(1);
		testCases[5].right = new BTNode(3);
		testCases[5].right.right = new BTNode(4);
		testCases[5].right.right.right = new BTNode(5);

		for (int i = 0; i < testCases.length; ++i) {
			System.out.println("Is tree #" + i + " super balanced?  " + (isSuperBalanced(testCases[i]) ? "YES" : "NO"));
			System.out.println("   " + (isSuperBalanced(testCases[i]) == answers[i] ? "CORRECT" : "WRONG"));
			System.out.println();
		}
	}

}