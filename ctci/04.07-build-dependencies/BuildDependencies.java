import java.util.*;

public class BuildDependencies {

	private static void dfsExploreAndAddLeaves(ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> reverseOrder, int i) {
		visited[i] = true;
		for (int j : adj.get(i)) {
			if (!visited[j]) dfsExploreAndAddLeaves(adj, visited, reverseOrder, j);
		}
		reverseOrder.push(i);
	}

	private static int[] topoSort(ArrayList<ArrayList<Integer>> adj) {
		int n = adj.size();
		boolean[] visited = new boolean[n];
		Stack<Integer> reverseOrder = new Stack<Integer>();

		for (int i = 0; i < n; ++i) {
			if (!visited[i]) dfsExploreAndAddLeaves(adj, visited, reverseOrder, i);
		}

		int[] output = new int[n];
		for (int i = 0; i < n; ++i) {
			output[i] = reverseOrder.pop();
		}

		return output;
	}

	public static void main(String... args) {
		System.out.println("Input:");
		System.out.println(" [n] number of nodes");
		System.out.println(" [m] number of edges");
		System.out.println(" [a] [b] edge from a to b (0-index, m times)");

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
			adj.get(in.nextInt()).add(in.nextInt());
		}

		int[] topoSortOrder = topoSort(adj);

		System.out.println("\nOutput:");
		System.out.println(Arrays.toString(topoSortOrder));
	}

}