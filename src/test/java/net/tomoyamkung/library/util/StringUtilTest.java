package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.tomoyamkung.library.Fixture;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class StringUtilTest {

	@RunWith(Theories.class)
	public static class ToWhiteSpaceSeparatedValue {

		static List<String> list = new ExtArrayList<String>().addThis("a")
				.addThis("b").addThis("c");

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<List<String>, String>(null, ""),
				new Fixture<List<String>, String>(new ArrayList<String>(), ""),
				new Fixture<List<String>, String>(list, "a b c") };

		@Theory
		public void test(Fixture<List<String>, String> p) throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(String.format("TARGET:%s EXPECTED:%s", p.target,
					p.expected),
					StringUtil.toWhiteSpaceSeparatedValue(p.target),
					is(p.expected));
		}
	}

	@RunWith(Theories.class)
	public static class Cutoff {

		private static final int length = 30;
		private static final String REPLACE_CHARACTER = "…";

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, String>(null, ""),
				new Fixture<String, String>("", ""),
				new Fixture<String, String>(
						StringUtil.createString(length - 1),
						StringUtil.createString(length - 1)),
				new Fixture<String, String>(StringUtil.createString(length),
						StringUtil.createString(length)),
				new Fixture<String, String>(
						StringUtil.createString(length + 1),
						StringUtil.createString(length) + REPLACE_CHARACTER), };

		@Theory
		public void test(Fixture<String, String> p) throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(String.format("TARGET:%s EXPECTED:%s", p.target,
					p.expected), StringUtil.cutoff(p.target, length,
					REPLACE_CHARACTER), is(p.expected));
		}
	}

	public static class RemoveStrings {

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.removeStrings(null, "x"), is(""));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.removeStrings("", "x"), is(""));
		}

		@Test
		public void 削除する文字が含まれていない場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.removeStrings("abc", "x"), is("abc"));
		}

		@Test
		public void 削除する文字が含まれている場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(
					StringUtil.removeStrings(
							"{\"attr1\":\"value1\",\"attr2\":\"value2\",\"attr3\":\"value3\"}",
							"{", "}", "\""),
					is("attr1:value1,attr2:value2,attr3:value3"));
		}
	}

	@RunWith(Theories.class)
	public static class RemoveFirstAndLastCharacter {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, String>(null, ""),
				new Fixture<String, String>("", ""),
				new Fixture<String, String>("a", ""),
				new Fixture<String, String>("ab", ""),
				new Fixture<String, String>("abc", "b"), };

		@Theory
		public void test(Fixture<String, String> p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.removeFirstAndLastCharacter(p.target);

			// Verify
			assertThat(actual, is(p.expected));
		}

	}

	@RunWith(Theories.class)
	public static class toLowerSnakeCase {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, String>("createdAt", "created_at"),
				new Fixture<String, String>("DummyUser", "dummy_user"), };

		@Theory
		public void test(Fixture<String, String> p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.toLowerSnakeCase(p.target);

			// Verify
			assertThat(actual, is(p.expected));
		}
	}

	@RunWith(Theories.class)
	public static class toLowerCamelCase {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, String>("created_at", "createdAt"),
				new Fixture<String, String>("dummy_user", "dummyUser"), };

		@Theory
		public void test(Fixture<String, String> p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.toLowerCamelCase(p.target);

			// Verify
			assertThat(actual, is(p.expected));
		}
	}

	@RunWith(Theories.class)
	public static class removeUnderScore {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, String>("created_At", "createdAt"),
				new Fixture<String, String>("DUMMY_USER", "DUMMYUSER"),
				new Fixture<String, String>("Annotation", "Annotation"), };

		@Theory
		public void test(Fixture<String, String> p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.removeUnderScore(p.target);

			// Verify
			assertThat(actual, is(p.expected));
		}

	}

	public static class IsMatch {

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isMatch(null, "x", "y"), is(false));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isMatch("", "x", "y"), is(false));
		}

		@Test
		public void 不一致な文字の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isMatch("a", "x", "y"), is(false));
		}

		@Test
		public void 一致する文字の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isMatch("x", "x"), is(true));
		}

		@Test
		public void 一致する文字の場合_配列の末尾() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isMatch("z", "x", "y", "z"), is(true));
		}
	}

	public static class IsOverLength {

		private static final int MAX_LENGTH = 10;

		@Test
		public void nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isOverLength(null, MAX_LENGTH), is(false));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isOverLength("", MAX_LENGTH), is(false));
		}

		@Test
		public void サイズ10の文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isOverLength(
					StringUtil.createString(MAX_LENGTH), MAX_LENGTH), is(false));
		}

		@Test
		public void サイズ11の文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isOverLength(StringUtil.createString(11),
					MAX_LENGTH), is(true));
		}
	}

	public static class IsNullOrEmpty {

		@Test
		public void nullの場合() throws Exception {
			// Setup
			// Excercise
			// Verify
			assertThat(StringUtil.isNullOrEmpty(null), is(true));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Excercise
			// Verify
			assertThat(StringUtil.isNullOrEmpty(""), is(true));
		}

		@Test
		public void 文字列が設定されている場合の場合() throws Exception {
			// Setup
			// Excercise
			// Verify
			assertThat(StringUtil.isNullOrEmpty("a"), is(false));
		}
	}
}
