package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SurrogatePairUtilTest {

	public static class Has {

		@Test
		public void nullの場合() throws Exception {
			// Setup
			String target = null;

			// Exercise
			boolean actual = SurrogatePairUtil.has(target);

			// Verify
			assertThat(actual, is(false));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			String target = "";

			// Exercise
			boolean actual = SurrogatePairUtil.has(target);

			// Verify
			assertThat(actual, is(false));
		}

		@Test
		public void サロゲートペアを含んでいない場合() throws Exception {
			// Setup
			String target = "aあ";

			// Exercise
			boolean actual = SurrogatePairUtil.has(target);

			// Verify
			assertThat(actual, is(false));
		}

		@Test
		public void サロゲートペアを含んでいる場合() throws Exception {
			// Setup
			String target = "𠀋aあ";

			// Exercise
			boolean actual = SurrogatePairUtil.has(target);

			// Verify
			assertThat(actual, is(true));
		}
	}

	public static class Exclude {

		@Test
		public void サロゲートペアを含んでいる場合() throws Exception {
			// Setup
			String target = "𠀋aあ";

			// Exercise
			String actual = SurrogatePairUtil.exclude(target);

			// Verify
			assertThat(actual, is("aあ"));
		}

		@Test
		public void サロゲートペアを含んでいない場合() throws Exception {
			// Setup
			String target = "aあ";

			// Exercise
			String actual = SurrogatePairUtil.exclude(target);

			// Verify
			assertThat(actual, is("aあ"));
		}

		@Test
		public void nullの場合() throws Exception {
			// Setup
			String target = null;

			// Exercise
			String actual = SurrogatePairUtil.exclude(target);

			// Verify
			assertThat(actual, is(""));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			String target = "";

			// Exercise
			String actual = SurrogatePairUtil.exclude(target);

			// Verify
			assertThat(actual, is(""));
		}
	}

}
