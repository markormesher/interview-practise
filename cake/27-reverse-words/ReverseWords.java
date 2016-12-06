public class ReverseWords {

	public static String reverseWords(String arg) {
		char[] chars = arg.toCharArray();
		
		// reverse whole string
		int start = 0;
		int end = chars.length - 1;
		while (start < end) {
			char tmp = chars[end];
			chars[end] = chars[start];
			chars[start] = tmp;
			
			++start;
			--end;
		}
		
		// reverse individual words
		start = 0;
		int wordEnd = 0;
		while (wordEnd < chars.length) {
			while (wordEnd < chars.length && chars[wordEnd] != ' ') {
				++wordEnd;
			}

			end = wordEnd - 1;
			while (start < end) {
				char tmp = chars[end];
				chars[end] = chars[start];
				chars[start] = tmp;
				
				++start;
				--end;
			}
			
			start = wordEnd + 1;
			wordEnd += 2;
		}
		
		return new String(chars);
	}

	public static void main(String[] args) {
		String[] strs = new String[]{
			"find you will pain only go you recordings security the into if",
			"world hello"
		};

		for (int i = 0; i < strs.length; ++i) {
			System.out.println("In: " + strs[i]);
			System.out.println("Out: " + reverseWords(strs[i]));
			System.out.println();
		}
	}
}