import java.util.*;

public class StackMin {
	
	public static class StackWithMin extends Stack<Integer> {

		private Stack<Integer> min = new Stack<>();

		public Integer push(Integer value) {
			if (min.isEmpty() || value <= getMin()) {
				min.push(value);
			}
			return super.push(value);
		}

		public Integer pop() {
			Integer value = super.pop();
			if (getMin() == value) {
				min.pop();
			}
			return value;
		}

		public Integer getMin() {
			return min.peek();
		}

	}

	public static void main(String... args) {
		System.out.println("Input: + [x] to push x, - to pop, m to peek minimum, q to quit");

		StackWithMin stack = new StackWithMin();

		Scanner in = new Scanner(System.in);
		while (true) {
			String command = in.next();
			if (command.equals("+")) {
				stack.push(in.nextInt());
			} else if (command.equals("-")) {
				if (stack.isEmpty()) {
					System.out.println("Stack empty");
				} else {
					System.out.println("Pop: " + stack.pop());
				}
			} else if (command.equals("m")) {
				if (stack.isEmpty()) {
					System.out.println("Stack empty");
				} else {
					System.out.println("Min: " + stack.getMin());
				}
			} else if (command.equals("q")) {
				return;
			} else {
				System.err.println("Invalid command");
			}
		}
	}

}