package net.tomoyamkung.library.model.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.tomoyamkung.library.model.json.JsonClassTest.シンプルなパターン.SimpleJson;
import net.tomoyamkung.library.util.ExtArrayList;
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
			assertThat("snake_case で取得する", actual.getString("user_id"),
					is(testJson.getUserId().toString()));
			assertThat(actual.getString("nickname"), is(testJson.getNickname()));
			assertThat("存在しない属性名の場合は null が返ってくる", actual.getString("x"),
					is(nullValue(String.class)));
		}

		static class SimpleJson {

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
	
	public static class 属性にリストを持っているパターン {
		
		@Test
		public void test() throws Exception {
			// Setup
			SimpleJson testJson = new ListJson(100, "ニックネーム");
			String jsonString = JsonUtil.serialize(testJson, "");

			// Exercise
			JsonClass actual = new JsonClass(jsonString);

			// Verify
			assertThat(actual, is(not(nullValue(JsonClass.class))));
			assertThat("snake_case で取得する", actual.getString("user_id"),
					is(testJson.getUserId().toString()));
			assertThat(actual.getString("nickname"), is(testJson.getNickname()));
			assertThat(actual.getString("stringList"), is("1st,2nd,3rd"));
			assertThat(actual.getList("stringList").get(0), is("1st"));
			assertThat(actual.getList("stringList").get(1), is("2nd"));
			assertThat(actual.getList("stringList").get(2), is("3rd"));
			
			assertThat("存在しない属性名の場合は null が返ってくる", actual.getString("x"),
					is(nullValue(String.class)));
		}
		
		static final class ListJson extends SimpleJson {
			
			private List<String> stringList;

			ListJson(int userId, String nickname) {
				super(userId, nickname);
				
				stringList = new ExtArrayList<String>().addThis("1st").addThis("2nd").addThis("3rd");
			}
			
			public List<String> getStringList() {
				return stringList;
			}
		}
	}
	
//	public static class オブジェクトを格納したリストの場合 {
//	
//	@Test
//	public void test() throws Exception {
//		// Setup
//		JsonList jsonList = new JsonList();
//		jsonList.add(new SimpleJson(10, "ニックネーム１"));
//		jsonList.add(new SimpleJson(100, "ニックネーム２"));
//		jsonList.add(new SimpleJson(1000, "ニックネーム３"));
//		String jsonString = JsonUtil.serialize(jsonList, "");
//		
//		// Exercise
//		JsonClass actual = new JsonClass(jsonString);
//		
//		// Verify
//		assertThat(actual.getValue("list"), is(not(nullValue())));
//	}
//	
//	static final class JsonList {
//		private List<SimpleJson> list = new ArrayList<SimpleJson>();
//		
//		public void add(SimpleJson obj) {
//			list.add(obj);
//		}
//	}
//}
}
