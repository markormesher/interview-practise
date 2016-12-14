/*
Task: detect whether a singly linked list has a cycle.
*/

public class SLLCycle {

	public static class Node<E> {
		E value;
		Node<E> next;

		public Node(E value) {
			this.value = value;
		}
	}

	public static <E> boolean hasCycle(Node<E> head) {
		Node<E> ptr1 = head;
		Node<E> ptr2 = head;
		while (ptr1 != null && ptr2 != null && ptr2.next != null) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next.next;

			if (ptr1 == ptr2) return true;
		}

		return false;
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
		System.out.println(hasCycle(a1) ? "INCORRECT" : "CORRECT");
		
		Node<Integer> b1 = new Node<>(1);
		Node<Integer> b2 = new Node<>(2);
		Node<Integer> b3 = new Node<>(3);
		Node<Integer> b4 = new Node<>(4);
		Node<Integer> b5 = new Node<>(5);
		b1.next = b2;
		b2.next = b3;
		b3.next = b4;
		b4.next = b5;
		b5.next = b3;
		System.out.println(!hasCycle(b1) ? "INCORRECT" : "CORRECT");

		Node<Integer> c1 = new Node<>(1);
		Node<Integer> c2 = new Node<>(2);
		c1.next = c2;
		c2.next = c1;
		System.out.println(!hasCycle(c1) ? "INCORRECT" : "CORRECT");
	}

}