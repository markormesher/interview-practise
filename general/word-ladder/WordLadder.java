import java.util.*;

public class WordLadder {
	
	public static class Step {
		String word;
		int level;
		public Step(String word, int level) {
			this.word = word;
			this.level = level;
		}
	}
	
	public static Set<String> getSuccessors(Set<String> dict, String wordStr) {
		char[] word = wordStr.toCharArray();

		Set<String> result = new HashSet<>();

		for (int i = 0; i < word.length; ++i) {
			char original = word[i];
			for (int j = 0; j < 26; ++j) {
				word[i] = (char) ('a' + j);
				if (dict.contains(new String(word))) {
					dict.remove(new String(word));
					result.add(new String(word));
				}
			}
			word[i] = original;
		}

		return result;
	}
	
	public static int ladderLength(String beginWord, String endWord, Set<String> dict) {
		dict.add(endWord);
		
		LinkedList<Step> openList = new LinkedList<>();
		openList.addLast(new Step(beginWord, 1));
		
		while (!openList.isEmpty()) {
			Step step = openList.removeFirst();
			Set<String> successors = getSuccessors(dict, step.word);
			for (String s : successors) {
				if (s.equals(endWord)) {
					return step.level + 1;
				} else {
					openList.addLast(new Step(s, step.level + 1));
				}
			}
		}
		
		return 0;
	}

	public static void main(String... args) {
		Set<String> dict = new HashSet<>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");

		System.out.println(ladderLength("hit", "cog", dict));
	}
}