package net.tomoyamkung.library.validate.date;

import java.text.SimpleDateFormat;

import net.tomoyamkung.library.util.DateUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * 値が指定の日付フォーマットであることを確認するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class DateFormatValidator implements Validator {
	
	private static SimpleDateFormat dateFormat;

	/**
	 * 日付フォーマットを設定する。
	 * 
	 * @param formatString
	 */
	public DateFormatValidator(String formatString) {
		dateFormat = new SimpleDateFormat(formatString);
	}

	@Override
	public boolean execute(String value) {
		return DateUtil.matchFormat(value, dateFormat);
	}

}
