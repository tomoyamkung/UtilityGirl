package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DoubleUtilTest {
	
	public static class IsInRange {
		
		private static final Double MAX_VALUE = 150.01;
		private static final Double MIN_VALUE = 123.45;
		
		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange(null, MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange("", MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void 範囲内_最小値と同じ() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange(MIN_VALUE.toString(), MIN_VALUE, MAX_VALUE), is(true));
		}
		
		@Test
		public void 範囲外_最小値より1つ小さい値() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange("123.44", MIN_VALUE, MAX_VALUE), is(false));
		}
		
		@Test
		public void 範囲内_最大値と同じ() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange(MAX_VALUE.toString(), MIN_VALUE, MAX_VALUE), is(true));
		}
		
		@Test
		public void 範囲外_最大値より1つ大きい値() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isInRange("150.02", MIN_VALUE, MAX_VALUE), is(false));
		}
	}

	public static class IsDouble {
		
		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble(null), is(false));
		}
		
		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble(""), is(false));
		}
		
		@Test
		public void 文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("abc"), is(false));
		}
		
		@Test
		public void 記号の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("!"), is(false));
		}
		
		@Test
		public void 数値以外に文字列が含まれている() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("123L"), is(false));
		}
		
		@Test
		public void 全角数字の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("１"), is(false));
		}
		
		@Test
		public void 整数の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("123"), is(true));
		}
		
		@Test
		public void 小数点を含む場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DoubleUtil.isDouble("123.123"), is(true));
		}
	}

}
