package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.tomoyamkung.library.model.json.parser.Iterator;
import net.tomoyamkung.library.model.json.parser.JsonAttributeClassIterator;
import net.tomoyamkung.library.util.JsonUtil;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonAttributeClassIteratorTest {

	public static class オブジェクトが1つの場合 {
		
		@Test
		public void test() throws Exception {
			// Setup
			String json = JsonUtil.serialize(DummyJson.newInstance(), "");
			
			// Exercise
			Iterator<String> sut = new JsonAttributeClassIterator(json);
			
			// Verify
			assertThat(sut.hasNext(), is(true));
			assertThat(sut.next(), is("{\"attr1\":\"value1\",\"attr2\":\"value2\",\"attr3\":\"value3\"}"));
			assertThat(sut.hasNext(), is(false));
		}
		
	}
	
	public static class オブジェクトが配列の場合 {
		
		@Test
		public void test() throws Exception {
			// Setup
			String json = JsonUtil.serialize(new DummyJsonList(), "");
			
			// Exercise
			Iterator<String> sut = new JsonAttributeClassIterator(json);
			
			// Verify
			assertThat(sut.hasNext(), is(true));
			assertThat(sut.next(), is("{\"attr1\":\"value1\",\"attr2\":\"value2\",\"attr3\":\"value3\"}"));
			assertThat(sut.hasNext(), is(true));
			assertThat(sut.next(), is("{\"attr1\":\"VALUE1\",\"attr2\":\"VALUE2\",\"attr3\":\"VALUE3\"}"));
			assertThat(sut.hasNext(), is(true));
			assertThat(sut.next(), is("{\"attr1\":\"値１\",\"attr2\":\"値２\",\"attr3\":\"値３\"}"));
			assertThat(sut.hasNext(), is(false));
		}

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
