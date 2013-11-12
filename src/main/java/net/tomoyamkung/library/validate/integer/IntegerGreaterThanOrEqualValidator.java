package net.tomoyamkung.library.validate.integer;

import net.tomoyamkung.library.util.IntegerUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定値以上であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerGreaterThanOrEqualValidator implements Validator {

	/**
	 * 指定値。
	 */
	private int to;

	public IntegerGreaterThanOrEqualValidator(int to) {
		this.to = to;
	}

	@Override
	public boolean execute(String value) {
		return IntegerUtil.isGreaterThanOrEqual(value, to);
	}

}
