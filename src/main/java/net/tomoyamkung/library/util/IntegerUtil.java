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

		int intValue = Integer.parseInt(value);
		return min <= intValue && intValue <= max;
	}

	/**
	 * 引数の文字列が指定した値以上であるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param to
	 *            比較する値
	 * @return <code>value</code> が <code>to</code> 以上であれば true
	 */
	public static Boolean isGreaterThanOrEqual(String value, int to) {
		if (!isInteger(value)) {
			return false;
		}

		int intValue = Integer.parseInt(value);
		return to <= intValue;
	}

	/**
	 * 引数の文字列が指定した値以上であるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param to
	 *            比較する値
	 * @return <code>value</code> が <code>to</code> よりも大きい値であれば true
	 */
	public static Boolean isGreaterThan(String value, int to) {
		if (!isInteger(value)) {
			return false;
		}

		int intValue = Integer.parseInt(value);
		return to < intValue;
	}

}
