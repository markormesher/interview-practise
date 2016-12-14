/*
Task: find the unique ID in an array that contains many paired duplicates.
*/

public class FindUnique {
	
	public static int findUnique(int[] ids) {
		int returnId = 0;
		for (int id : ids) {
			returnId ^= id;
		}
		return returnId;
	}
	
	public static void main(String... args) {
		int[][] arrays = {
			{1, 2, 1, 3, 3}
		};
		int[] answers = {
			2
		};
		
		for (int i = 0; i < arrays.length; ++i) {
			int answer = findUnique(arrays[i]);
			if (answer == answers[i]) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT (expected " + answers[i] + ", got " + answer +")");
			}
		}
	}
	
}