package net.tomoyamkung.library.validate.string;

import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 文字列にサロゲートペアが含まれていないことを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class SurrogatePairValidator implements Validator {

	@Override
	public boolean execute(String value) {
		if (StringUtil.isNullOrEmpty(value)) {
			return false;
		}

		for (char c : value.toCharArray()) {
			if (isSurrogatePairCharacter(c)) {
				return false;
			}
		}

		return true;
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
