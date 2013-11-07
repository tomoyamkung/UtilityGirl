package net.tomoyamkung.library.validate;

/**
 * 妥当性確認を行うクラスのインタフェース。
 * 
 * @author tomoyamkung
 *
 */
public interface Validator {
	
	/**
	 * 妥当性を確認する。
	 * 
	 * @param value 妥当性を確認する文字列
	 * @return 要件を満たす場合は true を返す
	 */
	boolean execute(String value);

}
