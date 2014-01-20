package net.tomoyamkung.library.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Calendar クラスのユーティリティクラス。
 * 
 * @author tomoyamkung
 *
 */
public class CalendarUtil {
	
	/**
	 * 指定した年月の Calendar オブジェクトの一覧を取得する。
	 * 
	 * <code>startDate</code> で取得開始日を指定することができる。
	 * 1 を指定した場合は月初から月末までの Calendar オブジェクトの一覧を取得する。
	 * 
	 * 時分秒は常に 00:00:00 を設定する。
	 * 
	 * @param year 年
	 * @param month 月
	 * @param startDate 取得開始日
	 * @return
	 */
	public static List<Calendar> getCalendars(int year, int month, int startDate) {
		List<Calendar> dates = new ArrayList<Calendar>();

		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR, year);
		instance.set(Calendar.MONTH, month);
		int lastDate = instance.getActualMaximum(Calendar.DATE);
		for(int date = startDate; date <= lastDate; date++) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, date, 0, 0, 0);
			dates.add(cal);
		}
		return dates;
	}
	
	/**
	 * 指定した年月の Calendar オブジェクトの一覧を取得する。
	 * 
	 * 例えば、次のパラメータを指定した場合、、、
	 * 
	 * <ul>
	 * <li>year → 2014</li>
	 * <li>month → 1</li>
	 * </ul>
	 * 
	 * 取得するリストは次のようになる。
	 * 
	 * <ul>
	 * <li>リストのサイズは 31</li>
	 * <li>リストの先頭は 2014/01/01 の Calendar オブジェクト</li>
	 * <li>リストの末尾は 2014/01/31 の Calendar オブジェクト</li>
	 * </ul>
	 * 
	 * @param year 年
	 * @param month 月
	 * @return
	 */
	public static List<Calendar> getCalendarsOfOneMonth(int year, int month) {
		return getCalendars(year, month, 1);
	}

	/**
	 * 指定した「時(hour)」の59分59秒に設定した Calendar オブジェクトを取得する。
	 * 
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @param hourOfDay 時間
	 * @return
	 */
	public static Calendar getLastTimeOfHour(int year, int month, int date,
			int hourOfDay) {
		Calendar instance = Calendar.getInstance();
		instance.set(year, month, date, hourOfDay, 59, 59);
		return instance;
	}

}
