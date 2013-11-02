package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class StringUtilTest {
	
	static class Fixture {
		String target;
		String expected;
		
		Fixture(String target, String expected) {
			this.target = target;
			this.expected = expected;
		}
	}
	
	@RunWith(Theories.class)	
	public static class toLowerSnakeCase {

		@DataPoints
		public static Fixture[] PARAMS = {
			new Fixture("createdAt", "created_at"),
			new Fixture("DummyUser", "dummy_user"),
		};
		
		@Theory
		public void test(Fixture p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.toLowerSnakeCase(p.target);
			
			// Verify
			assertThat(actual, is(p.expected));
		}
	}
	
	@RunWith(Theories.class)
	public static class toLowerCamelCase {
		
		@DataPoints
		public static Fixture[] PARAMS = {
			new Fixture("created_at", "createdAt"),
			new Fixture("dummy_user", "dummyUser"),
		};
		
		@Theory
		public void test(Fixture p) throws Exception {
			// Setup
			// Exercise
			String actual = StringUtil.toLowerCamelCase(p.target);
			
			// Verify
			assertThat(actual, is(p.expected));
		}
	}
	
	@RunWith(Theories.class)	
	public static class removeUnderScore {
		
		@DataPoints
		public static Fixture[] PARAMS = {
			new Fixture("created_At", "createdAt"),
			new Fixture("DUMMY_USER", "DUMMYUSER"),
			new Fixture("Annotation", "Annotation"),
		};
		
		@Theory
		public void test(Fixture p) throws Exception {
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
			assertThat(StringUtil.isOverLength(StringUtil.createString(MAX_LENGTH), MAX_LENGTH), is(false));
		}
		
		@Test
		public void サイズ11の文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(StringUtil.isOverLength(StringUtil.createString(11), MAX_LENGTH), is(true));
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
