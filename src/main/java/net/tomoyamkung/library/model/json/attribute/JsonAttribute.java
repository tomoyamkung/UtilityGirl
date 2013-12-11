package net.tomoyamkung.library.model.json.attribute;

/**
 * 属性名称とその属性値を保持するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonAttribute {

	/**
	 * 属性名称。
	 */
	private String name;

	/**
	 * 属性値。
	 */
	private String value;

	/**
	 * 属性名称とその属性値を保持する。
	 * 
	 */
	public JsonAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
