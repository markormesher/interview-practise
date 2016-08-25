import java.util.*;

public class StackOfStacks extends Stack<Integer> {

	private static final int STACK_LIMIT = 5;

	private Stack<Stack<Integer>> stacks = new Stack<>();

	public Integer push(Integer value) {
		if (stacks.isEmpty() || stacks.peek().size() == STACK_LIMIT) {
			stacks.push(new Stack<Integer>());
		}
		return stacks.peek().push(value);
	}

	public Integer pop() {
		if (stacks.isEmpty()) throw new EmptyStackException();

		int value = stacks.peek().pop();

		if (stacks.peek().isEmpty()) stacks.pop();

		return value;
	}

	public Integer peek() {
		if (stacks.isEmpty()) throw new EmptyStackException();

		return stacks.peek().peek();
	}

	public int size() {
		if (stacks.isEmpty()) {
			return 0;
		} else {
			return ((stacks.size() - 1) * STACK_LIMIT) + stacks.peek().size();
		}
	}

	public int stackCount() {
		return stacks.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public static void main(String... args) {
		System.out.println("Input: + [x] to push x, - to pop, ? to peek, s for size, q to quit");

		StackOfStacks stack = new StackOfStacks();

		Scanner in = new Scanner(System.in);
		while (true) {
			try {
				String command = in.next();
				if (command.equals("+")) {
					stack.push(in.nextInt());
				} else if (command.equals("-")) {
					System.out.println(stack.pop());
				} else if (command.equals("?")) {
					System.out.println(stack.peek());
				} else if (command.equals("s")) {
					System.out.println(stack.size() + " elements in " + stack.stackCount() + " stacks");
				} else if (command.equals("q")) {
					return;
				} else {
					System.err.println("Invalid command");
				}
			} catch (EmptyStackException e) {
				System.err.println("Stack empty");
			}
		}
	}

}