package net.tomoyamkung.library.model.json.attribute;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.model.json.parser.JsonAttributeParser;
import net.tomoyamkung.library.util.JsonUtil;
import net.tomoyamkung.library.util.StringUtil;

import org.junit.Test;

public class JsonAttributeParserTest {

	@Test
	public void test() throws Exception {
		// Setup
		String json = JsonUtil.serialize(DummyJson.create(), "");
		json = StringUtil.removeStrings(json, "{", "}", "\"");
		
		// Exercise
		JsonAttributeParser sut = new JsonAttributeParser(json);
		JsonAttribute actual = sut.create();
		
		// Verify
		assertThat(actual.getName(), is("attr1"));
		assertThat(actual.getValue(), is("value1"));
	}
	
	static class DummyJson {
		
		private String attr1 = "value1";

		public static DummyJson create() {
			return new DummyJson();
		}

		public String getAttr1() {
			return attr1;
		}

		public void setAttr1(String attr1) {
			this.attr1 = attr1;
		}
	}

}
