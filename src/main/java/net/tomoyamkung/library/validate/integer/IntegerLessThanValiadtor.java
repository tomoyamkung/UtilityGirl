package net.tomoyamkung.library.validate.integer;

import net.tomoyamkung.library.util.IntegerUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定値未満であることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerLessThanValiadtor implements Validator {

	/**
	 * 指定値。
	 */
	private int to;

	public IntegerLessThanValiadtor(int to) {
		this.to = to;
	}

	@Override
	public boolean execute(String value) {
		return IntegerUtil.isLessThan(value, to);
	}

}
