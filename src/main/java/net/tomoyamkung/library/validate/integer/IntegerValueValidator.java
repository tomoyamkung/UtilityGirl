package net.tomoyamkung.library.validate.integer;

import net.tomoyamkung.library.util.IntegerUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が <code>Integer</code> に変換できることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class IntegerValueValidator implements Validator {

	@Override
	public boolean execute(String value) {
		return IntegerUtil.isInteger(value);
	}

}
