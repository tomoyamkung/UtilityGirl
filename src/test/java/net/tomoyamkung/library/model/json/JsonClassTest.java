package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.tomoyamkung.library.model.json.attribute.JsonAttributeClass;
import net.tomoyamkung.library.util.JsonUtil;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonClassTest {

	public static class オブジェクトが1つの場合 {

		@Test
		public void test() throws Exception {
			// Setup
			String jsonString = JsonUtil.serialize(DummyJson.newInstance(), "");

			// Exercise
			JsonAttributeClass actual = new JsonClass(jsonString).get(0);

			// Verify
			assertThat(actual.getValue("attr1"), is("value1"));
			assertThat(actual.getValue("attr2"), is("value2"));
			assertThat(actual.getValue("attr3"), is("value3"));
		}
	}

	public static class オブジェクトが複数の場合 {

		@Test
		public void test() throws Exception {
			// Setup
			String jsonString = JsonUtil.serialize(new DummyJsonList(), "");

			// Exercise
			// Verify
			JsonAttributeClass actual = new JsonClass(jsonString).get(0);
			assertThat(actual.getValue("attr1"), is("value1"));
			assertThat(actual.getValue("attr2"), is("value2"));
			assertThat(actual.getValue("attr3"), is("value3"));

			actual = new JsonClass(jsonString).get(1);
			assertThat(actual.getValue("attr1"), is("VALUE1"));
			assertThat(actual.getValue("attr2"), is("VALUE2"));
			assertThat(actual.getValue("attr3"), is("VALUE3"));

			actual = new JsonClass(jsonString).get(2);
			assertThat(actual.getValue("attr1"), is("値１"));
			assertThat(actual.getValue("attr2"), is("値２"));
			assertThat(actual.getValue("attr3"), is("値３"));

		}

		static class DummyJsonList {

			private List<DummyJson> list;

			DummyJsonList() {
				list = new ArrayList<DummyJson>();
				list.add(DummyJson.newInstance());
				list.add(DummyJson.newInstance("VALUE1", "VALUE2", "VALUE3"));
				list.add(DummyJson.newInstance("値１", "値２", "値３"));
			}

			public List<DummyJson> getList() {
				return list;
			}
		}
	}

}
