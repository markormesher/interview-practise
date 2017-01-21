public class Reverse {

	public static String reverseWords(String s) {
		char[] str = s.trim().toCharArray();	 

		// reverse simple words
		int start = 0;
		int end = 0;
		while (start < str.length) {
			// skip start past spaces
			while (start < str.length && str[start] == ' ') {
				++start;
			}

			// move end to the end of the word
			end = start;
			while (end < str.length - 1 && str[end + 1] != ' ') {
				++end;
			}

			// reverse between start and end
			if (start < str.length) {
				int revStart = start;
				int revEnd = end;
				while (revStart < revEnd) {
					char tmp = str[revStart];
					str[revStart] = str[revEnd];
					str[revEnd] = tmp;
					++revStart;
					--revEnd;
				}
			}

			start = end + 1;
		}

		// compress extra spaces
		int copyTo = 0;
		int copyFrom = 0;
		while (copyFrom < str.length) {
			if (str[copyFrom] == ' ') {
				str[copyTo] = ' ';
				++copyTo;
			}
			while (copyFrom < str.length && str[copyFrom] == ' ') {
				++copyFrom;
			}
			str[copyTo] = str[copyFrom];
			++copyTo;
			++copyFrom;
		}

		// reverse whole string
		int revStart = 0;
		int revEnd = copyTo - 1;
		while (revStart < revEnd) {
			char tmp = str[revStart];
			str[revStart] = str[revEnd];
			str[revEnd] = tmp;
			++revStart;
			--revEnd;
		}

		return new String(str).substring(0, copyTo);
	}

	public static void main(String... args) {
		System.out.println(reverseWords(args[0]));
	}

}
