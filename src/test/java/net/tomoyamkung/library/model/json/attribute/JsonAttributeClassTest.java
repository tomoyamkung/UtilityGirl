package net.tomoyamkung.library.model.json.attribute;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.util.JsonUtil;

import org.junit.Test;

public class JsonAttributeClassTest {

	@Test
	public void test() throws Exception {
		// Setup
		String json = JsonUtil.serialize(DummyJson.create(), "");
		
		// Exercise
		JsonAttributeClass sut = new JsonAttributeClass(json);
		
		// Verify
		assertThat(sut.get("attr1").getValue(), is("value1"));
		assertThat(sut.get("attr2").getValue(), is("value2"));
		assertThat(sut.get("attr3").getValue(), is("value3"));
		assertThat("定義されていない属性名称を指定した場合は null になる",
				sut.get("x"), is(nullValue(JsonAttribute.class)));
	}
	
	static class DummyJson {
		private String attr1 = "value1";
		private String attr2 = "value2";
		private String attr3 = "value3";
		
		public static DummyJson create() {
			return new DummyJson();
		}

		public String getAttr1() {
			return attr1;
		}

		public void setAttr1(String attr1) {
			this.attr1 = attr1;
		}

		public String getAttr2() {
			return attr2;
		}

		public void setAttr2(String attr2) {
			this.attr2 = attr2;
		}

		public String getAttr3() {
			return attr3;
		}

		public void setAttr3(String attr3) {
			this.attr3 = attr3;
		}
	}

}
