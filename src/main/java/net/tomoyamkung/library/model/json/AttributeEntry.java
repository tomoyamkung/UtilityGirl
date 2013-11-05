package net.tomoyamkung.library.model.json;

/**
 * JSON の属性名称とその属性値を保持するクラスのインタフェース。
 * 
 * @author tomoyamkung
 * 
 * @param <T>
 *            保持している属性値の型パラメータ
 */
public interface AttributeEntry<T> {

	/**
	 * 保持している属性名称を取得する。
	 * 
	 * @return 属性名称
	 */
	String getName();

	/**
	 * 保持している属性値を取得する。
	 * 
	 * @return 属性値
	 */
	T getValue();

}
