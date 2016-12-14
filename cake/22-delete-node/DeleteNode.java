/*
Task: delete a node from a singly-linked list, given only a pointer to that node.
*/

public class DeleteNode {

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

	public static <E> void removeNode(Node<E> node) { // imperfect due to side effects
		if (node.next == null) {
			throw new IllegalArgumentException("Cannot remove tail");
		} else {
			node.value = node.next.value;
			node.next = node.next.next;
		}
	}

	public static void main(String... args) {
		// build a list
		Node<Integer> head = new Node<>(0);
		Node<Integer> curr = head;
		Node<Integer> itemThree = null;
		for (int i = 1; i < 10; ++i) {
			curr.next = new Node<Integer>(i);
			curr = curr.next;
			if (i == 3) itemThree = curr;
		}

		printList(head);

		// remove 3
		removeNode(itemThree);
		printList(head);

		// remove head
		removeNode(head);
		printList(head);
	}

}