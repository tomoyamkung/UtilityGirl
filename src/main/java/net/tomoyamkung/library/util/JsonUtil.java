package net.tomoyamkung.library.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import net.tomoyamkung.library.model.json.JsonClass;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON のシリアライズとデシリアライズを行うユーティリティクラス。
 * 
 * シリアライズ・デシリアライズの実装は Jackson を利用している。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonUtil {

	/**
	 * オブジェクトをシリアライズする。
	 * 
	 * @param object
	 *            シリアライズするクラス
	 * @param dateFormat
	 *            日付項目のフォーマット
	 * @return JSON
	 * @throws IOException
	 *             シリアライズに失敗した場合
	 * @throws JsonMappingException
	 *             シリアライズに失敗した場合
	 * @throws JsonGenerationException
	 *             シリアライズに失敗した場合
	 */
	public static String serialize(Object object, String dateFormat)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
		return mapper.writeValueAsString(object);
	}

	/**
	 * JSON からオブジェクトにデシリアライズする。
	 * 
	 * @param jsonString
	 *            デシリアライズする JSON
	 * @param clazz
	 *            デシリアライズするクラス
	 * @param dateFormat
	 *            日付項目のフォーマット
	 * @return デシリアライズしたクラスのオブジェクト
	 * @throws JsonParseException
	 *             デシリアライズに失敗した場合
	 * @throws JsonMappingException
	 *             デシリアライズに失敗した場合
	 * @throws IOException
	 *             デシリアライズに失敗した場合
	 */
	public static <T> T deserialize(String jsonString, Class<T> clazz,
			String dateFormat) throws JsonParseException, JsonMappingException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
		return mapper.readValue(jsonString, clazz);
	}
	
	/**
	 * JSON をオブジェクトにデシリアライズせずに、設定されていた全属性を String オブジェクトとして抽出する。
	 * 
	 * @param jsonString 抽出する JSON
	 * @return
	 */
	public static JsonClass toJsonClass(String jsonString) {
		if(StringUtil.isNullOrEmpty(jsonString)) {
			return null;
		}
		return new JsonClass(jsonString);
		
	}
}
