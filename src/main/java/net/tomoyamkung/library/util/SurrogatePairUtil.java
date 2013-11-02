package net.tomoyamkung.library.util;

/**
 * サロゲートペアのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class SurrogatePairUtil {

	/**
	 * サロゲートペアを含んでいる場合、サロゲートペアを除外する。
	 * 
	 * @param value
	 *            検査する文字列
	 * @return サロゲートペアを除外した文字列
	 */
	public static String exclude(String value) {
		if (value == null) {
			return "";
		}

		StringBuilder buf = new StringBuilder();
		for (char c : value.toCharArray()) {
			if (!isSurrogatePairCharacter(c)) {
				buf.append(c);
			}
		}
		return buf.toString();
	}

	/**
	 * サロゲートペアを含んでいるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @return サロゲートペアを含んでいる場合 true
	 */
	public static boolean has(String value) {
		if (value == null) {
			return false;
		}

		for (char c : value.toCharArray()) {
			if (isSurrogatePairCharacter(c)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * サロゲートペアであるか問い合わせる。
	 * 
	 * @param c
	 *            検査する文字
	 * @return サロゲートペアである場合 true
	 */
	private static boolean isSurrogatePairCharacter(char c) {
		return !Character.isDefined(c) || Character.isHighSurrogate(c)
				|| Character.isLowSurrogate(c);
	}
}
