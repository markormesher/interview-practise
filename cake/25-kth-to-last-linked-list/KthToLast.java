import java.util.*;

public class KthToLast {

	public static class Node<E> {
		E value;
		Node<E> next;

		public Node(E value) {
			this.value = value;
		}
	}

	public static void printList(Node head) {
		while (head != null) {
			System.out.print(head.value + " -> ");
			head = head.next;
		}
		System.out.println("END");
	}

	public static Node kthToLast(Node head, int k) {
		Node headCopy = head;
		int length = 0;
		while (headCopy != null) {
			headCopy = headCopy.next;
			++length;
		}

		int toSkip = length - k;
		if (toSkip < 0) return null;

		for (int i = 0; i < toSkip; ++i) {
			head = head.next;
		}

		return head;
	}

	public static void main(String... args) {
		Node<Integer> a1 = new Node<>(1);
		Node<Integer> a2 = new Node<>(2);
		Node<Integer> a3 = new Node<>(3);
		Node<Integer> a4 = new Node<>(4);
		Node<Integer> a5 = new Node<>(5);
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = a5;
		printList(a1);
		Node answer = kthToLast(a1, 3);
		System.out.println("3rd to last: " + (answer == null ? "null" : answer.value));

		System.out.println();

		Node<Integer> b1 = new Node<>(1);
		Node<Integer> b2 = new Node<>(2);
		b1.next = b2;
		printList(b1);
		answer = kthToLast(b1, 1);
		System.out.println("1st to last: " + (answer == null ? "null" : answer.value));

		System.out.println();

		Node<Integer> c1 = new Node<>(1);
		printList(c1);
		answer = kthToLast(c1, 2);
		System.out.println("2nd to last: " + (answer == null ? "null" : answer.value));
	}

}