package net.tomoyamkung.library.validate.integer;

import net.tomoyamkung.library.util.IntegerUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定値よりも大きいことを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerGreaterThanValidator implements Validator {

	/**
	 * 指定値。
	 */
	private int to;

	public IntegerGreaterThanValidator(int to) {
		this.to = to;
	}

	@Override
	public boolean execute(String value) {
		return IntegerUtil.isGreaterThan(value, to);
	}

}
