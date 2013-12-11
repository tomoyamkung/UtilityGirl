package net.tomoyamkung.library.validate.doubles;

import net.tomoyamkung.library.util.DoubleUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 文字列が Double 型に変換できることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class DoubleValueValidator implements Validator {

	@Override
	public boolean execute(String value) {
		return DoubleUtil.isDouble(value);
	}

}
