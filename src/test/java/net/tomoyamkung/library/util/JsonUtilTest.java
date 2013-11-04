package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.model.json.JsonClass;

import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonUtilTest {

	public static class ToJsonClass {

		@Test
		public void nullの場合() {
			// Setup
			// Exercise
			// Verify
			assertThat(JsonUtil.toJsonClass(null), is(nullValue()));
		}
		
		@Test
		public void ブランクの場合() {
			// Setup
			// Exercise
			// Verify
			assertThat(JsonUtil.toJsonClass(""), is(nullValue()));
		}
		
		@Test
		public void test() throws Exception {
			// Setup
			TestJson testJson = new TestJson();
			String jsonString = JsonUtil.serialize(new TestJson(), "");
			
			// Exercise
			JsonClass actual = JsonUtil.toJsonClass(jsonString);
			
			// Verify
			assertThat(actual, is(not(nullValue(JsonClass.class))));
			assertThat(actual.getValue("user_id"), is(testJson.getUserId().toString()));
			assertThat(actual.getValue("nickname"), is(testJson.getNickname()));
			assertThat("存在しない属性名の場合は null が返ってくる", actual.getValue("x"), is(nullValue(String.class)));
		}
		
		private static final class TestJson {
			
			@JsonProperty("user_id")
			private Integer user_id = 100;
			private String nickname = "ニックネーム";
			
			public Integer getUserId() {
				return user_id;
			}
			public String getNickname() {
				return nickname;
			}
		}
		
	}
}
