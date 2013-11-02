package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class IntegerUtilTest {
	
	public static class IsInRange {
		
		private static final Integer MAX_VALUE = 9;
		private static final Integer MIN_VALUE = 0;

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange(null, MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange("", MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void 範囲内_最小値と同じ() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange(MIN_VALUE.toString(), MIN_VALUE, MAX_VALUE), is(true));
		}
		
		@Test
		public void 範囲外_最小値より1つ小さい値() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange(String.valueOf(MIN_VALUE - 1), MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void 範囲内_最大値と同じ() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange(MAX_VALUE.toString(), MIN_VALUE, MAX_VALUE), is(true));
		}
		
		@Test
		public void 範囲外_最大値より1つ大きい値() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInRange(String.valueOf(MAX_VALUE + 1), MIN_VALUE, MAX_VALUE), is(false));
		}
	}

	public static class IsInteger {
		
		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger(null), is(false));
		}
		
		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger(""), is(false));
		}

		@Test
		public void 文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger("あ"), is(false));
		}
		
		@Test
		public void 数値以外に文字列が含まれている() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger("123L"), is(false));
		}
		
		@Test
		public void 数値の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger("123"), is(true));
		}
		
		@Test
		public void 全角数字の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(IntegerUtil.isInteger("１"), is(true));
		}
	}
}