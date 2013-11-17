package net.tomoyamkung.library.util;

import static com.google.common.base.CaseFormat.*;

import org.apache.commons.lang3.StringUtils;

/**
 * String クラスのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class StringUtil {

	/**
	 * 引数の文字列が Null または ブランクであるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @return Null または ブランクの場合 true
	 */
	public static Boolean isNullOrEmpty(String value) {
		return StringUtils.isBlank(value);
	}

	/**
	 * 引数の文字列の長さが指定した文字数を超えているかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param maxLength
	 *            許容可能な文字数
	 * @return 指定を超えている場合 true（引数の文字列の長さが指定した文字数と同じ場合は false）
	 */
	public static Boolean isOverLength(String value, int maxLength) {
		if (StringUtils.isBlank(value)) {
			return false;
		}
		return maxLength < StringUtils.length(value);
	}

	/**
	 * 引数の文字列が指定の値であるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param matches
	 *            指定の文字列
	 * @return 指定の値である場合 true
	 */
	public static Boolean isMatch(String value, String... matches) {
		for (String match : matches) {
			if (match.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定した文字数の String オブジェクトを生成する。
	 * 
	 * @param length
	 *            文字数
	 * @param value
	 *            String オブジェクトに格納する文字
	 * @return
	 */
	public static String createString(int length, String value) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < length; i++) {
			str.append(value);
		}
		return str.toString();
	}

	/**
	 * 指定した文字数の String オブジェクトを生成する。
	 * 
	 * @param length
	 *            文字数
	 * @return
	 */
	public static String createString(int length) {
		return createString(length, "x");
	}

	/**
	 * snake_case に変換する。
	 * 
	 * 変換例は次の通り。
	 * 
	 * <ul>
	 * <li>createdAt → created_at</li>
	 * <li>DummyUser → dummy_user</li>
	 * </ul>
	 * 
	 * @param target
	 * @return
	 */
	public static String toLowerSnakeCase(String target) {
		return LOWER_CAMEL.to(LOWER_UNDERSCORE, target);
	}

	/**
	 * camelCase に変換する。
	 * 
	 * 変換例は次の通り。
	 * 
	 * <ul>
	 * <li>created_at → createdAt</li>
	 * <li>dummy_user → dummyUser</li>
	 * </ul>
	 * 
	 * @param target
	 * @return
	 */
	public static String toLowerCamelCase(String target) {
		return LOWER_UNDERSCORE.to(LOWER_CAMEL, target);
	}

	/**
	 * アンダースコア（"_"）を取り除く。
	 * 
	 * 例は次の通り。
	 * 
	 * <ul>
	 * <li>created_At → createdAt</li>
	 * <li>DUMMY_USER → DUMMYUSER</li>
	 * <li>Annotation → Annotation</li>
	 * </ul>
	 * 
	 * @param target
	 * @return
	 */
	public static String removeUnderScore(String target) {
		return StringUtils.remove(target, "_");
	}

	/**
	 * 文字列の先頭と末尾の文字を削除する。
	 * 
	 * 動作は以下の通り。
	 * 
	 * <ul>
	 * <li>value が null → ブランク</li>
	 * <li>value が ブランク → ブランク</li>
	 * <li>value が "a" → ブランク</li>
	 * <li>value が "ab" → ブランク</li>
	 * <li>value が "abc" → "b"</li>
	 * </ul>
	 * 
	 * @param value
	 * @return
	 */
	public static String removeFirstAndLastCharacter(String value) {
		if (StringUtil.isNullOrEmpty(value)) {
			return "";
		}

		if (value.length() < 3) {
			return "";
		}

		String x = removeFirstCharacter(value);
		return removeLastCharacter(x);
	}

	/**
	 * 文字列の末尾の文字を削除する。
	 * 
	 * @param value
	 *            削除する文字列
	 * @return
	 */
	public static String removeLastCharacter(String value) {
		return value.substring(0, value.length() - 1).trim();
	}

	/**
	 * 文字列の先頭の文字を削除する。
	 * 
	 * @param value
	 *            削除する文字列
	 * @return
	 */
	public static String removeFirstCharacter(String value) {
		return value.substring(1).trim();
	}

	/**
	 * 文字列から指定した文字を削除する。
	 * 
	 * @param value
	 *            削除される文字列
	 * @param deletes
	 *            削除する文字
	 * @return
	 */
	public static String removeStrings(String value, String... removes) {
		if (StringUtil.isNullOrEmpty(value)) {
			return "";
		}

		for (int i = 0; i < removes.length; i++) {
			value = StringUtils.remove(value, removes[i]);
		}
		return value;
	}

	/**
	 * 文字列を <code>length</code> で切り取る。
	 * 
	 * <code>length</code> を超えた場合は、その部分を <code>replaceCharacter</code> に置き換える。
	 * <code>length</code> を超えない場合は、原文を返す。
	 * 
	 * @param value
	 *            原文
	 * @param length
	 *            指定の長さ
	 * @param replaceCharacter
	 *            置き換える文字
	 * @return
	 */
	public static String cutoff(String value, int length,
			String replaceCharacter) {
		if (value == null || value.length() == 0) {
			return "";
		}

		if (value.length() <= length) {
			return value;
		}
		return value.substring(0, length) + replaceCharacter;
	}
}
