package net.tomoyamkung.library.props;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AppPropertiesTest {

	public static class GetStringList {
		
		AppProperties sut;
		
		@Before
		public void setUp() throws Exception {
			sut = AppProperties.getInstance();
		}

		@Test(expected = IllegalArgumentException.class)
		public void keyがnullの場合() throws Exception {
			// Setup
			// Exercise
			sut.getStringList(null);
			// Verify

		}

		@Test(expected = IllegalArgumentException.class)
		public void keyに該当するvalueが定義されていない場合() throws Exception {
			// Setup
			// Exercise
			sut.getStringList("not_found");
			// Verify

		}

		@Test
		public void keyに該当するvalueは定義されているが項目が1つの場合() throws Exception {
			// Setup
			// Exercise
			List<String> actual = sut.getStringList("test.get.list_single");

			// Verify
			assertThat(actual.size(), is(1));
			assertThat(actual.get(0), is("list_single"));
		}

		@Test
		public void keyに該当するvalueは定義されているがCSVになっていない場合() throws Exception {
			// Setup
			// Exercise
			List<String> actual = sut.getStringList("test.get.list_not_csv");

			// Verify
			assertThat(actual.size(), is(1));
			assertThat(actual.get(0), is("list_not_csv.1.2"));
		}

		@Test
		public void keyに該当するvalueが定義されている場合() throws Exception {
			// Setup
			// Exercise
			List<String> actual = sut.getStringList("test.get.list_csv");

			// Verify
			assertThat(actual.size(), is(3));
			assertThat(actual.get(0), is("list_not_csv"));
			assertThat(actual.get(1), is("1"));
			assertThat(actual.get(2), is("2"));
		}

	}

	public static class GetInt {

		AppProperties sut;
		
		@Before
		public void setUp() throws Exception {
			sut = AppProperties.getInstance();
		}

		@Test(expected = IllegalArgumentException.class)
		public void keyがnullの場合() throws Exception {
			// Setup
			// Exercise
			sut.getInt(null);
			// Verify

		}

		@Test(expected = IllegalArgumentException.class)
		public void keyに該当するvalueが定義されていない場合() throws Exception {
			// Setup
			// Exercise
			sut.getInt("not_found");
			// Verify

		}

		@Test(expected = NumberFormatException.class)
		public void keyに該当するvalueは定義されているが数値に変換できない場合() throws Exception {
			// Setup
			// Exercise
			sut.getInt("test.get");
			// Verify

		}

		@Test
		public void keyに該当するvalueが定義されている場合() throws Exception {
			// Setup
			// Exercise
			int actual = sut.getInt("test.get.int");

			// Verify
			assertThat(actual, is(100));

		}

	}

	public static class Get {

		AppProperties sut;
		
		@Before
		public void setUp() throws Exception {
			sut = AppProperties.getInstance();
		}

		@Test(expected = IllegalArgumentException.class)
		public void keyがnullの場合() throws Exception {
			// Setup
			// Exercise
			sut.get(null);
			// Verify

		}

		@Test(expected = IllegalArgumentException.class)
		public void keyに該当するvalueが定義されていない場合() throws Exception {
			// Setup
			// Exercise
			sut.get("not_found");
			// Verify

		}

		@Test
		public void keyに該当するvalueが定義されている場合() throws Exception {
			// Setup
			// Exercise
			String actual = sut.get("test.get");

			// Verify
			assertThat(actual, is("get"));

		}

	}

}
