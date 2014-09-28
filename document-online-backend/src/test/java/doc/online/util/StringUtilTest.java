package doc.online.util;

import doc.online.util.StringUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilTest {

	@Test
	public void testIsNullOrEmpty() {
		String s = null;
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = "";
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = " ";
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = "    ";
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = "\t";
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = "\n";
		assertTrue(StringUtil.isNullOrEmpty(s));

		s = " \t \n ";
		assertTrue(StringUtil.isNullOrEmpty(s));
	}

	@Test
	public void testGetWordsFromNullString() {
		String[] words = StringUtil.getWords(null);
		assertNotNull(words);
		assertTrue(words.length == 0);
	}

	@Test
	public void testGetWordsFromEmptyString() {
		String s = "";
		String[] words = StringUtil.getWords(s);
		assertTrue(words != null);
		assertTrue(words.length == 0);

		s = " ";
		words = StringUtil.getWords(s);
		assertTrue(words != null);
		assertTrue(words.length == 0);

		s = " \t \n ";
		words = StringUtil.getWords(s);
		assertTrue(words != null && words.length == 0);
	}

	@Test
	public void testGetWordsFromStringContainsOneWord() {
		String s = "a";
		String[] words = StringUtil.getWords(s);
		assertTrue(words.length == 1);
		assertEquals("a", words[0]);
	}

	@Test
	public void testGetWordsFromStringContainsTwoWord() {
		String s = "a b";
		String[] words = StringUtil.getWords(s);
		assertTrue(words.length == 2);
		assertEquals("a", words[0]);
		assertEquals("b", words[1]);

		s = "aaa bbb";
		words = StringUtil.getWords(s);
		assertTrue(words.length == 2);
		assertEquals("aaa", words[0]);
		assertEquals("bbb", words[1]);
	}

	@Test
	public void testGetWordsFromComplexString1() {
		String s = " \t \n aaa \n \t";
		String[] words = StringUtil.getWords(s);
		assertTrue(words.length == 1);
		assertEquals("aaa", words[0]);
	}

	@Test
	public void testGetWordsFromCompexString2() {
		String s = "\t\ta\n\nbb\n\n  ccc   ";
		String[] words = StringUtil.getWords(s);
		assertTrue(words.length == 3);
		assertEquals("a", words[0]);
		assertEquals("bb", words[1]);
		assertEquals("ccc", words[2]);
	}

	@Test
	public void testGetWordsFromString() {
		String s = "The dog is in it's house";
		String[] words = StringUtil.getWords(s);

		assertTrue(words.length == 6);
		assertEquals("The", words[0]);
		assertEquals("dog", words[1]);
		assertEquals("is", words[2]);
		assertEquals("in", words[3]);
		assertEquals("it's", words[4]);
		assertEquals("house", words[5]);
	}
}
