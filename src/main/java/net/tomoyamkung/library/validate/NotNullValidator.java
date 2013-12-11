package net.tomoyamkung.library.validate;

import net.tomoyamkung.library.util.StringUtil;

/**
 * 値が格納されていることを確認するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class NotNullValidator implements Validator {

	@Override
	public boolean execute(String value) {
		return !StringUtil.isNullOrEmpty(value);
	}

}
