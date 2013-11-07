package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
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
			assertThat(actual.get("attr1").getValue(), is("value1"));
			assertThat(actual.get("attr2").getValue(), is("value2"));
			assertThat(actual.get("attr3").getValue(), is("value3"));
		}
	}

}
