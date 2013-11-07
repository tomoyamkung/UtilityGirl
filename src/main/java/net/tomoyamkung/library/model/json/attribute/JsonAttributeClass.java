package net.tomoyamkung.library.model.json.attribute;

import java.util.HashMap;
import java.util.Map;

import net.tomoyamkung.library.model.json.parser.JsonAttributeParser;
import net.tomoyamkung.library.util.StringUtil;

/**
 * JSON のオブジェクトに定義されている属性を保持するクラス。
 * 
 * <code>JsonAttribute</code> を保持するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonAttributeClass {

	/**
	 * <code>JsonAttribute</code> を保持する。
	 */
	private Map<String, JsonAttribute> attributes;

	/**
	 * オブジェクトが定義されている JSON を受け取る。
	 * 
	 * 引数に設定される JSON は "{"attr1":"value1","attr2":"value2","attr3":"value3"}"
	 * の形式とする。
	 * 
	 * @param json
	 */
	public JsonAttributeClass(String json) {
		attributes = new HashMap<String, JsonAttribute>();

		parse(json);
	}

	/**
	 * JSON を属性に分解する。
	 * 
	 * @param json
	 */
	private void parse(String json) {
		json = StringUtil.removeStrings(json, "{", "}", "\"").trim();

		for (String attribute : json.split(",")) {
			JsonAttribute jsonAttribute = new JsonAttributeParser(
					attribute.trim()).create();
			addAttribute(jsonAttribute);
		}
	}

	/**
	 * <code>JsonAttribute</code> を保持する属性に追加する。
	 * 
	 * @param jsonAttribute
	 */
	private void addAttribute(JsonAttribute jsonAttribute) {
		attributes.put(jsonAttribute.getName(), jsonAttribute);
	}

	/**
	 * <code>JsonAttribute</code> を取得する。
	 * 
	 * @param attributeName
	 *            属性名
	 * @return
	 */
	public JsonAttribute get(String attributeName) {
		return attributes.get(attributeName);
	}

}
