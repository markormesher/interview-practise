import java.util.*;

public class Reachability {

	private static boolean reachableBfs(ArrayList<ArrayList<Integer>> adj, int x, int y) {
		int n = adj.size();
		boolean[] visited = new boolean[n];

		LinkedList<Integer> toVisit = new LinkedList<Integer>();
		toVisit.push(x);

		while (!toVisit.isEmpty()) {
			int i = toVisit.removeFirst();
			if (i == y) return true;

			visited[i] = true;
			for (int j : adj.get(i)) {
				if (!visited[j]) toVisit.addLast(j);
			}
		}

		return false;
	}

	private static boolean reachableDfs(ArrayList<ArrayList<Integer>> adj, int x, int y) {
		int n = adj.size();
		boolean[] visited = new boolean[n];

		Stack<Integer> toVisit = new Stack<Integer>();
		toVisit.push(x);

		while (!toVisit.isEmpty()) {
			int i = toVisit.pop();
			if (i == y) return true;

			visited[i] = true;
			for (int j : adj.get(i)) {
				if (!visited[j]) toVisit.push(j);
			}
		}

		return false;
	}

	public static void main(String... args) {
		System.out.println("Input:");
		System.out.println(" [n] number of nodes");
		System.out.println(" [m] number of edges");
		System.out.println(" [a] [b] edge from a to b (0-index, m times)");
		System.out.println(" [x] [y] points for reachability test");

		// build nodes
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			adj.add(new ArrayList<Integer>());
		}

		// build edges
		int m = in.nextInt();
		for (int i = 0; i < m; ++i) {
			int a = in.nextInt();
			int b = in.nextInt();

			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		int x = in.nextInt();
		int y = in.nextInt();

		System.out.println("\nWith BFS:");
		System.out.println(reachableBfs(adj, x, y) ? "Connected" : "Not connected");

		System.out.println("\nWith DFS:");
		System.out.println(reachableDfs(adj, x, y) ? "Connected" : "Not connected");
	}

}