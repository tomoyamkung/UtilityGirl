package net.tomoyamkung.library.util;

/**
 * Double クラスのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class DoubleUtil {

	/**
	 * 引数の文字列が Double 型に変換できるものであるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @return Double 型に変換できる場合 true
	 */
	public static Boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
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
	public static Boolean isInRange(String value, double min, double max) {
		if (!isDouble(value)) {
			return false;
		}

		try {
			double doubleValue = Double.parseDouble(value);
			return min <= doubleValue && doubleValue <= max;
		} catch (Exception e) {
			return false;
		}
	}
}
