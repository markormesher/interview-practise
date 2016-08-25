import java.util.*;

public class KthToLastElement {

	private static void printLinkedList(Node head) {
		if (head == null) {
			System.out.println("--empty--");
		} else {
			while (head != null) {
				System.out.print(head.value);
				if (head.next != null) {
					System.out.print(" -> ");
				}
				head = head.next;
			}
			System.out.println();
		}
	}

	/*
	O(n) time
	O(1) extra space
	*/
	private static Node kthToLastElement(Node head, int k) {
		int size = 0;

		Node countingHead = head;
		while (countingHead != null) {
			++size;
			countingHead = countingHead.next;
		}

		if (k >= size) return null;

		int steps = size - k - 1;
		while (steps > 0) {
			--steps;
			head = head.next;
		}

		return head;
	}

	public static void main(String... args) {
		System.out.println("Input: [list length] [list items] [k]");

		Scanner in = new Scanner(System.in);
		int length = in.nextInt();

		if (length < 1) {
			System.err.println("Length must be at least 1");
			return;
		}

		Node head = new Node(in.nextInt());
		Node workingHead = head;
		for (int i = 1; i < length; ++i) {
			workingHead.next = new Node(in.nextInt());
			workingHead = workingHead.next;
		}

		int k = in.nextInt();

		System.out.println("Input:");
		printLinkedList(head);

		System.out.println("\nOutput:");
		Node output = kthToLastElement(head, k);
		System.out.println(output == null ? "--none--" : output.value);
	}

	public static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
		}
	}

}