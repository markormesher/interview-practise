import java.util.*;

public class AnimalQueue {

	public static class Animal {
		String name;
		int order = 0;

		public Animal(String n) {
			name = n;
		}

		public String toString() {
			return name;
		}
	}

	public static class Cat extends Animal {
		public Cat(String n) {
			super(n + " (cat)");
		}
	}

	public static class Dog extends Animal {
		public Dog(String n) {
			super(n + " (dog)");
		}
	}

	public static class EmptyQueueException extends Exception {}

	private LinkedList<Cat> cats = new LinkedList<Cat>();
	private LinkedList<Dog> dogs = new LinkedList<Dog>();
	private int counter = 0;

	public void enqueue(Animal a) {
		a.order = counter++;

		if (a instanceof Cat) {
			cats.addFirst((Cat) a);
		} else {
			dogs.addFirst((Dog) a);
		}
	}

	public Animal dequeueAny() throws EmptyQueueException {
		if (cats.isEmpty() && dogs.isEmpty()) throw new EmptyQueueException();
		if (cats.isEmpty()) return dequeueDog();
		if (dogs.isEmpty()) return dequeueCat();

		Cat cat = cats.getLast();
		Dog dog = dogs.getLast();

		if (cat.order < dog.order) {
			return dequeueCat();
		} else {
			return dequeueDog();
		}
	}

	public Cat dequeueCat() throws EmptyQueueException {
		if (cats.isEmpty()) throw new EmptyQueueException();
		return cats.removeLast();
	}

	public Dog dequeueDog() throws EmptyQueueException {
		if (dogs.isEmpty()) throw new EmptyQueueException();
		return dogs.removeLast();
	}

	public static void main(String... args) {
		System.out.println("Input: +c [name] or +d [name] to enqueue cat/dog, -a to dequeue any, -c or -d to dequeue cat or dog, q to quit");

		AnimalQueue queue = new AnimalQueue();

		Scanner in = new Scanner(System.in);
		while (true) {
			try {
				String command = in.next();
				if (command.equals("+c")) {
					queue.enqueue(new Cat(in.next()));
				} else if (command.equals("+d")) {
					queue.enqueue(new Dog(in.next()));
				} else if (command.equals("-a")) {
					System.out.println(queue.dequeueAny());
				} else if (command.equals("-c")) {
					System.out.println(queue.dequeueCat());
				} else if (command.equals("-d")) {
					System.out.println(queue.dequeueDog());
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