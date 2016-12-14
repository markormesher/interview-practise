/*
Task: reverse a singly linked list.
*/

public class ReverseSLL {

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

	public static <E> Node<E> reverse(Node<E> head) {
		Node<E> newHead = null;
		Node<E> curr = head;
		Node<E> next = head.next;
		while (curr != null) {
			curr.next = newHead;
			newHead = curr;
			curr = next;
			if (next != null) next = next.next;
		}
		return newHead;
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
		a1 = reverse(a1);
		printList(a1);

		System.out.println();

		Node<Integer> b1 = new Node<>(1);
		Node<Integer> b2 = new Node<>(2);
		b1.next = b2;
		printList(b1);
		b1 = reverse(b1);
		printList(b1);

		System.out.println();

		Node<Integer> c1 = new Node<>(1);
		printList(c1);
		c1 = reverse(c1);
		printList(c1);
	}

}