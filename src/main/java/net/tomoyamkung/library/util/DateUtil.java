package net.tomoyamkung.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.tomoyamkung.library.model.JapaneseDayOfWeekCharacter;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Date クラスのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class DateUtil {

	/**
	 * 引数の文字列が指定の日付形式であるかを問い合わせる。
	 * 
	 * @param value
	 *            検査する文字列
	 * @param format
	 *            指定の日付形式
	 * @return 指定の日付形式の場合 true
	 */
	public static Boolean matchFormat(String value, SimpleDateFormat format) {
		try {
			format.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 日付の「曜日（日本語）」を取得する。
	 * 
	 * 「曜日」は "日"、"土" などの値とする。
	 * 
	 * @param date
	 *            日付
	 * @return
	 */
	public static String getDayOfWeek(Date date) {
		if (date == null) {
			return "";
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return JapaneseDayOfWeekCharacter.asStringList().get(dayOfWeek);
	}

	/**
	 * 文字列を Date 型のオブジェクトに変換する。
	 * 
	 * 次のケースでは Null を返す。
	 * 
	 * <ul>
	 * <li><code>dateString</code> が null</li>
	 * <li><code>dateString</code> が ブランク</li>
	 * <li><code>dateString</code> に日付に変換できない文字が含んでいる</li>
	 * <li><code>dateString</code> と <code>formatString</code> のフォーマットが一致していない</li>
	 * </ul>
	 * 
	 * @param dateString
	 *            変換する文字列
	 * @param formatString
	 *            <code>SimpleDateFormat</code> に設定する日付フォーマット
	 * @return
	 */
	public static Date stringToDate(String dateString, String formatString) {
		if (StringUtil.isNullOrEmpty(dateString)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日曜日であるかを問い合わせる。
	 * 
	 * @param date
	 *            問い合わせる日付
	 * @return 日曜日の場合 true
	 */
	public static boolean isSunday(Date date) {
		return DateUtil.getDayOfWeek(date).equals(
				JapaneseDayOfWeekCharacter.SUN.getValue());
	}

	/**
	 * 土曜日であるかを問い合わせる。
	 * 
	 * @param date
	 *            問い合わせる日付
	 * @return 土曜日の場合 true
	 */
	public static boolean isSaturday(Date date) {
		return DateUtil.getDayOfWeek(date).equals(
				JapaneseDayOfWeekCharacter.SAT.getValue());
	}

	/**
	 * 開始日と終了日を含んだ日付の一覧を取得する。
	 * 
	 * @param begin 開始日
	 * @param end 終了日
	 * @return
	 */
	public static List<Date> getTeamDates(Date begin, Date end) {
		if(begin == null || end == null) {
			return new ArrayList<Date>();
		}
		
		if(end.before(begin)) {
			return new ArrayList<Date>();
		}
		
		List<Date> dates = new ArrayList<Date>();
		if(DateUtils.isSameDay(end, begin)) {
			dates.add(begin);
			return dates;
		}
		
		Date targetDate = begin;
		while(!DateUtils.isSameDay(targetDate, end)) {
			dates.add(targetDate);
			targetDate = DateUtils.addDays(targetDate, 1);
		}
		dates.add(targetDate);
		
		return dates;
	}
	
}
