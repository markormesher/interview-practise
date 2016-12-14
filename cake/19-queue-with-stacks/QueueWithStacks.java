/*
Task: implement a queue with two stacks.
Runtime: O(m) for m operations (use the accounting method).
*/

import java.util.*;

public class QueueWithStacks<E> {
	
	private Stack<E> incoming, outgoing;
	
	public QueueWithStacks() {
		incoming = new Stack<>();
		outgoing = new Stack<>();
	}

	private void stackIntoStack(Stack<E> from, Stack<E> to) {
		while (!from.empty()) to.push(from.pop());
	}
	
	public void push(E item) {
		incoming.push(item);
	}
	
	public E pop() {
		if (outgoing.empty()) {
			stackIntoStack(incoming, outgoing);
		}
		
		if (outgoing.empty()) {
			throw new IllegalStateException("Queue is empty!");
		} else {
			return outgoing.pop();
		}
	}
	
	public E peek() {
		if (outgoing.empty()) {
			stackIntoStack(incoming, outgoing);
		}
		
		if (outgoing.empty()) {
			throw new IllegalStateException("Queue is empty!");
		} else {
			return outgoing.peek();
		}
	}
	
	public boolean empty() {
		return incoming.empty() && outgoing.empty();
	}
	
	public static void main(String... args) {
		QueueWithStacks<Integer> q = new QueueWithStacks<>();
		q.push(1);
		q.push(2);
		q.push(3);
		System.out.println(q.pop());
		q.push(4);
		while (!q.empty()) {
			try {
				System.out.println(q.pop());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}