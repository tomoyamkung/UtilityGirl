package net.tomoyamkung.library.model.json.parser;

import net.tomoyamkung.library.model.json.attribute.JsonAttribute;

/**
 * 属性のみ定義された JSON を解析するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class JsonAttributeParser {
	
	/**
	 * 属性名称。
	 */
	private String name;
	
	/**
	 * 属性値。
	 */
	private String value;

	/**
	 * JSON を解析して、属性名称とその属性値に分解する。
	 * 
	 * 引数に設定される JSON は "attr1:value1" の形式とする。
	 * 
	 * @param json
	 */
	public JsonAttributeParser(String json) {
		name = extractName(json.trim());
		value = extractValue(json.trim());
	}
	
	/**
	 * 属性名称を抽出する。
	 * 
	 * @param json
	 * @return
	 */
	private String extractValue(String json) {
		int position = indexOfDelimiter(json);
		return json.substring(position + 1).trim();
	}

	/**
	 * 属性値を抽出する。
	 * 
	 * @param json
	 * @return
	 */
	private String extractName(String json) {
		int position = indexOfDelimiter(json);
		return json.substring(0, position).trim();
	}

	private int indexOfDelimiter(String json) {
		return json.indexOf(":");
	}
	
	/**
	 * <code>JsonAttribute</code> オブジェクトを生成する。
	 * 
	 * @return
	 */
	public JsonAttribute create() {
		return new JsonAttribute(name, value);
	}
	
}
