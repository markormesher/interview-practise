import java.util.*;

public class QueueWithTwoStacks {

	private Stack<Integer> stackWithBackOnTop = new Stack<>();
	private Stack<Integer> stackWithFrontOnTop = new Stack<>();

	public void enqueue(int i) {
		shiftDataToBackOnTop();
		stackWithBackOnTop.push(i);
	}

	public int dequeue() throws EmptyQueueException {
		shiftDataToFrontOnTop();
		if (stackWithFrontOnTop.isEmpty()) throw new EmptyQueueException();
		return stackWithFrontOnTop.pop();
	}

	private void shiftDataToFrontOnTop() {
		while (!stackWithBackOnTop.isEmpty()) {
			stackWithFrontOnTop.push(stackWithBackOnTop.pop());
		}
	}

	private void shiftDataToBackOnTop() {
		while (!stackWithFrontOnTop.isEmpty()) {
			stackWithBackOnTop.push(stackWithFrontOnTop.pop());
		}
	}

	public static class EmptyQueueException extends Exception {}

	public static void main(String... args) {
		System.out.println("Input: + [x] to enqueue x, - to dequeue, q to quit");

		QueueWithTwoStacks queue = new QueueWithTwoStacks();

		Scanner in = new Scanner(System.in);
		while (true) {
			try {
				String command = in.next();
				if (command.equals("+")) {
					queue.enqueue(in.nextInt());
				} else if (command.equals("-")) {
					System.out.println(queue.dequeue());
				} else if (command.equals("q")) {
					return;
				} else {
					System.err.println("Invalid command");
				}
			} catch (EmptyQueueException e) {
				System.err.println("Queue empty");
			}
		}
	}


}