package net.tomoyamkung.library.model.json;

import net.tomoyamkung.library.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * JSON の属性名称とその属性値をペアで保持するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class JsonItem {

	/**
	 * 属性名称。
	 */
	private String name;
	
	/**
	 * 属性値。
	 */
	private String value;

	/**
	 * 属性名称とその属性値が定義されている文字列を受け取り、値を保持する。
	 * 
	 * @param itemString
	 */
	public JsonItem(String itemString) {
		int position = itemString.indexOf(":");
		
		name = StringUtil.removeFirstAndLastCharacter(itemString.substring(0, position)).trim();
		value = StringUtils.remove(itemString.substring(position + 1), "\"").trim();
		if(isListValue(value)) {
			value = StringUtils.remove(StringUtils.remove(value, "["), "]");
		}
	}
	
	/**
	 * 属性値がリストであるかを問い合わせる。
	 * 
	 * @param itemValue
	 * @return リストの場合 true
	 */
	private boolean isListValue(String itemValue) {
		return itemValue.contains("[");
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}	
	
}
