package doc.online.util;

import java.util.List;
import java.util.ArrayList;

public class StringUtil {
	public static boolean isNullOrEmpty(String s) {
		return (s == null) || (s.trim().isEmpty());
	}

	public static String[] getWords(String s) {
		List<String> wordList = new ArrayList<String>();

		if (s == null)
			return wordList.toArray(new String[0]);

		int len = s.length();
		int iStartWord, iEndWord;
		char cur;

		iStartWord = 0;
		while (true) {
			for (; iStartWord < len; iStartWord++) {
				cur = s.charAt(iStartWord);
				if ((cur != ' ') && (cur != '\t') && (cur != '\n'))
					break;
			}

			if (iStartWord >= len)
				break;

			for (iEndWord = iStartWord+1; iEndWord < len; iEndWord++) {
				cur = s.charAt(iEndWord);
				if ((cur == ' ') || (cur == '\t') || (cur == '\n'))
					break;
			}

			if (iStartWord < len) {
				wordList.add(s.substring(iStartWord, iEndWord));
				iStartWord = iEndWord;
			}
		}

		return wordList.toArray(new String[wordList.size()]);
	}
}
