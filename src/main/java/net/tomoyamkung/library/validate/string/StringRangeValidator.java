package net.tomoyamkung.library.validate.string;

import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 文字列の長さが指定の範囲内であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class StringRangeValidator implements Validator {

	/**
	 * 最小値。
	 */
	private int min;

	/**
	 * 最大値。
	 */
	private int max;

	/**
	 * 値のとりうる範囲を設定する。
	 * 
	 * @param min
	 *            最小値
	 * @param max
	 *            最大値
	 */
	public StringRangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean execute(String value) {
		if (StringUtil.isNullOrEmpty(value)) {
			return false;
		}

		int stringLength = value.length();
		return min <= stringLength && stringLength <= max;
	}

}
