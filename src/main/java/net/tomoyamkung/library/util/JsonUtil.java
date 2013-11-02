package net.tomoyamkung.library.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
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
	 * 日付項目のフォーマットを指定する場合に使用する。
	 * 
	 * @param object シリアライズするクラス
	 * @param dateFormat 日付項目のフォーマット
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String serialize(Object object, String dateFormat) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
		return mapper.writeValueAsString(object);
	}

}
