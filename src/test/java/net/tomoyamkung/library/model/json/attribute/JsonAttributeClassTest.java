package net.tomoyamkung.library.model.json.attribute;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.model.json.DummyJson;
import net.tomoyamkung.library.util.JsonUtil;

import org.junit.Test;

public class JsonAttributeClassTest {

	@Test
	public void test() throws Exception {
		// Setup
		String json = JsonUtil.serialize(DummyJson.newInstance(), "");
		
		// Exercise
		JsonAttributeClass sut = new JsonAttributeClass(json);
		
		// Verify
		assertThat(sut.get("attr1").getValue(), is("value1"));
		assertThat(sut.get("attr2").getValue(), is("value2"));
		assertThat(sut.get("attr3").getValue(), is("value3"));
		assertThat("定義されていない属性名称を指定した場合は null になる",
				sut.get("x"), is(nullValue(JsonAttribute.class)));
	}

}
