package net.tomoyamkung.library.validate.doubles;

import net.tomoyamkung.library.util.DoubleUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定の範囲内であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class DoubleRangeValidator implements Validator {

	/**
	 * 最小値。
	 */
	private double min;

	/**
	 * 最大値。
	 */
	private double max;

	/**
	 * 値のとりうる範囲を設定する。
	 * 
	 * @param min
	 *            最小値
	 * @param max
	 *            最大値
	 */
	public DoubleRangeValidator(double min, double max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean execute(String value) {
		return DoubleUtil.isInRange(value, min, max);
	}

}
