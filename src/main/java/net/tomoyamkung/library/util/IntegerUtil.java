package net.tomoyamkung.library.util;

/**
 * Integer クラスのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerUtil {

	/**
	 * 引数の文字列が数値を表すものであるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @return 数値型に変換できる場合 true
	 */
	public static Boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 引数の文字列が指定した範囲内の数値であるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param min
	 *            最小値
	 * @param max
	 *            最大値
	 * @return 範囲内の場合 true
	 */
	public static Boolean isInRange(String value, int min, int max) {
		if (!isInteger(value)) {
			return false;
		}

		try {
			int intValue = Integer.parseInt(value);
			return min <= intValue && intValue <= max;
		} catch (Exception e) {
			return false;
		}
	}

}
