/*
Task: implement an int stack that also has an O(1) getMax() operation.
*/

import java.util.*;

public class MaxStack {
	
	private Stack<Integer> values = new Stack<>();
	private Stack<Integer> maximums = new Stack<>();
	
	public void push(int item) {
		values.push(item);
		if (maximums.empty()) {
			maximums.push(item);
		} else {
			maximums.push(Math.max(item, maximums.peek()));
		}
	}
	
	public int pop() {
		maximums.pop();
		return values.pop();
	}
	
	public int peek() {
		return values.peek();
	}
	
	public int getMax() {
		return maximums.peek();
	}
	
	public boolean empty() {
		return values.empty();
	}
	
	public static void main(String... args) {
		int[] values = {1, 3, 5, 2, 9, 0, 4};
		
		MaxStack s = new MaxStack();
		for (int i : values) {
			s.push(i);
			System.out.println("PUSHED " + i);
			System.out.println("  Max: " + s.getMax());
		}
		while (!s.empty()) {
			System.out.println("POPPED " + s.pop());
			if (!s.empty()) {
				System.out.println("  Max: " + s.getMax());
			} else {
				System.out.println("  Empty");
			}
		}
	}
	
}