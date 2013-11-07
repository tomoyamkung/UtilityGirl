package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.tomoyamkung.library.util.JsonUtil;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonAttributeClassSpliterTest {

	public static class オブジェクトが1つの場合 {
		
		@Test
		public void test() throws Exception {
			// Setup
			String json = JsonUtil.serialize(DummyJson.create(), "");
			
			// Exercise
			JsonAttributeClassSpliter sut = new JsonAttributeClassSpliter(json);
			
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
			JsonAttributeClassSpliter sut = new JsonAttributeClassSpliter(json);
			
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
			list.add(DummyJson.create());
			list.add(DummyJson.create("VALUE1", "VALUE2", "VALUE3"));
			list.add(DummyJson.create("値１", "値２", "値３"));
		}

		public List<DummyJson> getList() {
			return list;
		}

		public void setList(List<DummyJson> list) {
			this.list = list;
		}
	}

	static class DummyJson {
		private String attr1 = "value1";
		private String attr2 = "value2";
		private String attr3 = "value3";
		
		static DummyJson create() {
			return new DummyJson();
		}
		
		static DummyJson create(String value1, String value2, String value3) {
			DummyJson instance = new DummyJson();
			instance.attr1 = value1;
			instance.attr2 = value2;
			instance.attr3 = value3;
			return instance;
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
