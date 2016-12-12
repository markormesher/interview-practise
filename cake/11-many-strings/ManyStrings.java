/*
Task: quickly check if a string is in a set that will be huge.
*/

import java.util.*;

public class ManyStrings {

	public static class TrieNode {
		
		Map<Character, TrieNode> nodes = new HashMap<>();
	}

	public static class Trie {

		private static final char terminalChar = '\0';
		private int storedCharCount = 0;
		private TrieNode root = new TrieNode();

		public boolean contains(String str) {
			TrieNode curr = root;
			char[] strChars = str.toCharArray();
			for (char strC : strChars) {
				if (curr.nodes.containsKey(strC)) {
					curr = curr.nodes.get(strC);
				} else {
					return false;
				}
			}
			return curr.nodes.containsKey(terminalChar);
		}

		public void insert(String str) {
			TrieNode curr = root;
			char[] strChars = str.toCharArray();
			for (char strC : strChars) {
				if (!curr.nodes.containsKey(strC)) {
					curr.nodes.put(strC, new TrieNode());
					++storedCharCount;
				}
				curr = curr.nodes.get(strC);
			}
			if (!curr.nodes.containsKey(terminalChar)) {
				curr.nodes.put(terminalChar, new TrieNode());
				++storedCharCount;
			}
		}

		public int getStoredCharCount() {
			return storedCharCount;
		}
	}

	public static void main(String... args) {
		String[][] operations = {
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"},
				{"insert", "pop"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"},
				{"insert", "popper"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"},
				{"insert", "poppadom"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"},
				{"insert", "curry"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curries"},
				{"insert", "curry sauce"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"},
				{"insert", "curry sauce"},
				{"check", "pop"},
				{"check", "popper"},
				{"check", "poppadom"},
				{"check", "curry"},
				{"check", "curries"},
				{"check", "curry sauce"}
		};

		Trie trie = new Trie();
		int argumentLengthSum = 0;
		for (String[] op : operations) {
			if (op[0].equals("check")) {
				System.out.println("CHECK  " + op[1] + "\n  " + (trie.contains(op[1]) ? "YES" : "NO") + "\n");
			} else {
				System.out.println("INSERT " + op[1] + "\n");
				trie.insert(op[1]);
				argumentLengthSum += op[1].length();
			}
		}

		System.out.println(argumentLengthSum + " chars in stored strings");
		System.out.println(trie.getStoredCharCount() + " chars stored in total");
	}

}