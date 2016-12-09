import java.util.*;

public class SecondLargestInBst {

	public static class BstNode {
		int value;
		BstNode left, right, parent;

		public BstNode(int value) {
			this.value = value;
		}
	}

	public static BstNode secondLargest(BstNode root) {
		// must have at least 2 nodes
		if (root == null) return null;
		if (root.left == null && root.right == null) return null;

		BstNode rightMost = rightMostChild(root);
		if (rightMost.left == null) {
			return rightMost.parent;
		} else {
			return rightMostChild(rightMost.left);
		}
	}

	public static BstNode rightMostChild(BstNode node) {
		while (node.right != null) node = node.right;
		return node;
	}

	public static void main(String... args) {
		BstNode[] testCases = new BstNode[6];
		int[] answers = new int[6];

		// single node
		answers[0] = -1;
		testCases[0] = new BstNode(1);

		// complete 2-level tree
		answers[1] = 2;
		testCases[1] = new BstNode(2);
		testCases[1].left = new BstNode(1);
		testCases[1].left.parent = testCases[1];
		testCases[1].right = new BstNode(3);
		testCases[1].right.parent = testCases[1];

		// root, 1 child on the left
		answers[2] = 1;
		testCases[2] = new BstNode(2);
		testCases[2].left = new BstNode(1);
		testCases[2].left.parent = testCases[2];

		// root, 1 child on the right
		answers[3] = 1;
		testCases[3] = new BstNode(1);
		testCases[3].right = new BstNode(2);
		testCases[3].right.parent = testCases[3];

		// complete 3-level tree
		answers[4] = 6;
		testCases[4] = new BstNode(4);
		testCases[4].left = new BstNode(2);
		testCases[4].left.parent = testCases[4];
		testCases[4].left.left = new BstNode(1);
		testCases[4].left.left.parent = testCases[4].left;
		testCases[4].left.right = new BstNode(3);
		testCases[4].left.right.parent = testCases[4].left;
		testCases[4].right = new BstNode(6);
		testCases[4].right.parent = testCases[4];
		testCases[4].right.left = new BstNode(5);
		testCases[4].right.left.parent = testCases[4].right;
		testCases[4].right.right = new BstNode(7);
		testCases[4].right.right.parent = testCases[4].right;

		// nothing
		answers[5] = -1;
		testCases[5] = null;

		for (int i = 0; i < testCases.length; ++i) {
			BstNode answer = secondLargest(testCases[i]);
			int intAnswer = answer == null ? -1 : answer.value;
			System.out.println("2nd largest in tree #" + i + " = " + (answer == null ? "no answer" : intAnswer));
			System.out.println("   " + (intAnswer == answers[i] ? "CORRECT" : "WRONG"));
			System.out.println();
		}
	}


}