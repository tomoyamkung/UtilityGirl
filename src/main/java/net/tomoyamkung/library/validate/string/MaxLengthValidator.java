package net.tomoyamkung.library.validate.string;

import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 文字列の長さが指定値以下であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class MaxLengthValidator implements Validator {

	/**
	 * 指定値。
	 * 
	 * 許可する文字列の長さ。
	 */
	private int maxLength;

	public MaxLengthValidator(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public boolean execute(String value) {
		if (StringUtil.isNullOrEmpty(value)) {
			return false;
		}

		return !StringUtil.isOverLength(value, maxLength);
	}

}
