package net.tomoyamkung.library.model.json;

/**
 * テスト用の JSON クラス。
 * 
 * @author tomoyamkung
 * 
 */
public class DummyJson {

	/**
	 * 属性１。
	 */
	private String attr1 = "value1";

	/**
	 * 属性２。
	 */
	private String attr2 = "value2";

	/**
	 * 属性３。
	 */
	private String attr3 = "value3";

	/**
	 * インスタンスを生成する。
	 * 
	 * フィールドの値はデフォルト値を設定する。
	 * 
	 * @return
	 */
	public static DummyJson newInstance() {
		return new DummyJson();
	}

	/**
	 * インスタンスを生成する。
	 * 
	 * @param value1 属性１に設定する値
	 * @param value2 属性２に設定する値
	 * @param value3 属性３に設定する値
	 * @return
	 */
	public static DummyJson newInstance(String value1, String value2,
			String value3) {
		DummyJson instance = new DummyJson();
		instance.attr1 = value1;
		instance.attr2 = value2;
		instance.attr3 = value3;
		return instance;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
}
