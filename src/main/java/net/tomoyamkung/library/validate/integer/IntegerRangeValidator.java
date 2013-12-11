package net.tomoyamkung.library.validate.integer;

import net.tomoyamkung.library.util.IntegerUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定の範囲内であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerRangeValidator implements Validator {

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
	public IntegerRangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean execute(String value) {
		return IntegerUtil.isInRange(value, min, max);
	}

}
