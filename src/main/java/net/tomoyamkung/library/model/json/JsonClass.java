package net.tomoyamkung.library.model.json;

import java.util.HashMap;
import java.util.Map;

import net.tomoyamkung.library.util.StringUtil;

/**
 * JSON の全属性を String オブジェクトとして抽出する。
 * 
 * JSON をデシリアライズする前に属性の妥当性を確認する用途で作成したクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonClass {

	/**
	 * 抽出した属性を保持する Map
	 */
	private Map<String, String> attributes;

	/**
	 * 解析する JSON を受け取り、個々の属性に展開する。
	 * 
	 * @param jsonString
	 *            解析する JSON
	 */
	public JsonClass(String jsonString) {
		if (StringUtil.isNullOrEmpty(jsonString)) {
			return;
		}

		parse(jsonString);
	}

	/**
	 * JSON を解析して属性を抽出する。
	 * 
	 * @param jsonString 解析する JSON
	 */
	private void parse(String jsonString) {
		attributes = new HashMap<String, String>();
		
		String json = removeExtractWord(jsonString);
		for (String attribute : json.split(",")) {
			String x = attribute.trim();
			int position = x.indexOf(":");
			String key = removeExtractWord(x.substring(0, position));
			String value = removeExtractWord(x.substring(position + 1));
			attributes.put(key, value);
		}
	}

	/**
	 * 属性名称や属性値を囲むダブルクォートやスペースなど不要な文字を取り除く。
	 * 
	 * @param json
	 * @return
	 */
	private String removeExtractWord(String json) {
		json = json.trim();
		if (!json.startsWith("\"") && !json.startsWith("{")) {
			return json;
		}

		return StringUtil.removeFirstAndLastCharacter(json).trim();
	}

	/**
	 * 属性の値を取得する。
	 * 
	 * 該当する属性名が存在しない場合 null を返す。
	 * 
	 * @param attributeName
	 *            取得する属性の名称
	 * @return
	 */
	public String getValue(String attributeName) {
		if (StringUtil.isNullOrEmpty(attributeName)) {
			return null;
		}

		if (attributes == null) {
			return null;
		}

		if (!attributes.containsKey(attributeName)) {
			return null;
		}

		String value = attributes.get(attributeName);
		if (StringUtil.isNullOrEmpty(value)) {
			return null;
		}
		return value;
	}

}
