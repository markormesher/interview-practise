import java.util.*;

public class PartitionLinkedList {

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

	private static Node partitionLinkedList(Node head, int pValue) {
		if (head == null) return null;

		Node newFrontHead = null;
		Node workingNewFrontHead = null;

		Node newBackHead = null;
		Node workingNewBackHead = null;

		while (head != null) {
			if (head.value < pValue) {
				// add to new front head
				if (newFrontHead == null) {
					newFrontHead = head;
					workingNewFrontHead = newFrontHead;
				} else {
					workingNewFrontHead.next = head;
					workingNewFrontHead = workingNewFrontHead.next;
				}
			} else {
				// add to new front head
				if (newBackHead == null) {
					newBackHead = head;
					workingNewBackHead = newBackHead;
				} else {
					workingNewBackHead.next = head;
					workingNewBackHead = workingNewBackHead.next;
				}
			}

			// advance
			head = head.next;
		}

		// clear links at the tail of each list
		if (workingNewFrontHead != null) workingNewFrontHead.next = null;
		if (workingNewBackHead != null) workingNewBackHead.next = null;

		// join lists
		if (newFrontHead == null) {
			newFrontHead = newBackHead;
		} else {
			workingNewFrontHead.next = newBackHead;
		}

		return newFrontHead;
	}

	public static void main(String... args) {
		System.out.println("Input: [list length] [list items] [partition element]");

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

		int partitionValue = in.nextInt();

		System.out.println("Input:");
		printLinkedList(head);

		System.out.println("\nOutput:");
		printLinkedList(partitionLinkedList(head, partitionValue));
	}

	public static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
		}
	}

}