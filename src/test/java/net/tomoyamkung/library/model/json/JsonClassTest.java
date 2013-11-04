package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.util.JsonUtil;

import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonClassTest {

	public static class シンプルなパターン {
		@Test
		public void test() throws Exception {
			// Setup
			SimpleJson testJson = new SimpleJson(100, "ニックネーム");
			String jsonString = JsonUtil.serialize(testJson, "");

			// Exercise
			JsonClass actual = new JsonClass(jsonString);

			// Verify
			assertThat(actual, is(not(nullValue(JsonClass.class))));
			assertThat("snake_case で取得する", actual.getValue("user_id"),
					is(testJson.getUserId().toString()));
			assertThat(actual.getValue("nickname"), is(testJson.getNickname()));
			assertThat("存在しない属性名の場合は null が返ってくる", actual.getValue("x"),
					is(nullValue(String.class)));
		}

		private static final class SimpleJson {

			@JsonProperty("user_id")
			private Integer user_id;
			private String nickname;

			public SimpleJson(int userId, String nickname) {
				user_id = userId;
				this.nickname = nickname;
			}

			public Integer getUserId() {
				return user_id;
			}

			public String getNickname() {
				return nickname;
			}
		}
	}
}
