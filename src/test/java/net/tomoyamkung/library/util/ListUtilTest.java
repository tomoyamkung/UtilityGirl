package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ListUtilTest {

	public static class Copy {

		@Test
		public void toの中身が空の場合() throws Exception {

			// Setup
			List<String> from = new ExtArrayList<String>().addThis("1")
					.addThis("2").addThis("3");
			List<String> to = new ArrayList<String>();

			// Exercise
			ListUtil.copy(from, 1, to);

			// Verify
			assertThat(to.size(), is(1));
			assertThat(to.get(0), is("2"));
		}

		@Test
		public void toにオブジェクトが格納されている場合() throws Exception {

			// Setup
			List<String> from = new ExtArrayList<String>().addThis("1")
					.addThis("2").addThis("3");
			List<String> to = new ExtArrayList<String>().addThis("a")
					.addThis("b").addThis("c");

			// Exercise
			ListUtil.copy(from, 1, to);

			// Verify
			assertThat(to.size(), is(4));
			assertThat(to.get(3), is("2"));
		}

		@Test
		public void fromの中身が空の場合がnullの場合() throws Exception {

			// Setup
			List<String> from = new ArrayList<String>();
			List<String> to = new ArrayList<String>();

			// Exercise
			ListUtil.copy(from, 1, to);

			// Verify
			assertThat(to.size(), is(0));
		}

		@Test
		public void fromのサイズよりもインデックスのほうが大きい場合() throws Exception {

			// Setup
			List<String> from = new ExtArrayList<String>().addThis("1");
			List<String> to = new ArrayList<String>();

			// Exercise
			ListUtil.copy(from, 1, to);

			// Verify
			assertThat(to.size(), is(0));
		}
	}

}
