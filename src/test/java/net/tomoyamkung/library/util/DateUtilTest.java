package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.tomoyamkung.library.model.JapaneseDayOfWeekCharacter;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DateUtilTest {

	public static class GetTermDates {

		private static final String FORMAT_STRING = "yyyy-MM-dd";

		@Test
		public void 異常系_両期間ともnullを指定した場合() throws Exception {
			// Setup
			Date start = null;
			Date end = null;

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual.size(), is(0));
		}

		@Test
		public void 異常系_開始日にnullを指定した場合() throws Exception {
			// Setup
			Date start = null;
			Date end = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual.size(), is(0));
		}

		@Test
		public void 異常系_終了日にnullを指定した場合() throws Exception {
			// Setup
			Date start = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);
			Date end = null;

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual.size(), is(0));
		}

		@Test
		public void 異常系_終了日が開始日よりも過去だった場合() throws Exception {
			// Setup
			Date start = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);
			Date end = DateUtil.stringToDate("2013-09-10", FORMAT_STRING);

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual.size(), is(0));
		}

		@Test
		public void 正常系_開始日と終了日が同じだった場合() throws Exception {
			// Setup
			Date start = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);
			Date end = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual.size(), is(1));
			assertThat(DateUtils.isSameDay(actual.get(0), start), is(true));
			assertThat(DateUtils.isSameDay(actual.get(0), end), is(true));
		}

		@Test
		public void 正常系_終了日が開始日の一週間後だった場合() throws Exception {
			// Setup
			Date start = DateUtil.stringToDate("2013-10-10", FORMAT_STRING);
			Date end = DateUtil.stringToDate("2013-10-17", FORMAT_STRING);

			// Exercise
			List<Date> actual = DateUtil.getTeamDates(start, end);

			// Verify
			assertThat(actual, is(not(nullValue())));
			assertThat(actual.size(), is(8));
			assertThat(DateUtils.isSameDay(actual.get(0), start), is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(1),
							DateUtil.stringToDate("2013-10-11", FORMAT_STRING)),
					is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(2),
							DateUtil.stringToDate("2013-10-12", FORMAT_STRING)),
					is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(3),
							DateUtil.stringToDate("2013-10-13", FORMAT_STRING)),
					is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(4),
							DateUtil.stringToDate("2013-10-14", FORMAT_STRING)),
					is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(5),
							DateUtil.stringToDate("2013-10-15", FORMAT_STRING)),
					is(true));
			assertThat(
					DateUtils.isSameDay(actual.get(6),
							DateUtil.stringToDate("2013-10-16", FORMAT_STRING)),
					is(true));
			assertThat(DateUtils.isSameDay(actual.get(7), end), is(true));
		}

	}

	public static class IsSaturDay {

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSaturday(null), is(false));
		}

		@Test
		public void 土曜日以外の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSaturday(getSunDay()), is(false));
		}

		@Test
		public void 土曜日の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSaturday(getSaturDay()), is(true));
		}

	}

	private static Date getSaturDay() {
		Calendar sunday = Calendar.getInstance();
		sunday.set(2013, Calendar.NOVEMBER, 2);
		return sunday.getTime();
	}

	private static Date getSunDay() {
		Calendar sunday = Calendar.getInstance();
		sunday.set(2013, Calendar.NOVEMBER, 3);
		return sunday.getTime();
	}

	public static class IsSunday {

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSunday(null), is(false));
		}

		@Test
		public void 日曜日以外の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSunday(getSaturDay()), is(false));
		}

		@Test
		public void 日曜日の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.isSunday(getSunDay()), is(true));
		}

	}

	public static class DateToString {

		private static final String FORMAT_STRING = "yyyy/MM/dd";

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.dateToString(null, FORMAT_STRING),
					is(nullValue(String.class)));
		}

		@Test
		public void Dateオブジェクトの場合() throws Exception {
			// Setup
			String expected = "2013/10/10";
			Date date = DateUtil.stringToDate(expected, FORMAT_STRING);

			// Exercise
			String actual = DateUtil.dateToString(date, FORMAT_STRING);

			// Verify
			assertThat(actual, is(expected));
		}
	}

	public static class StringToDate {

		private static final String FORMAT_STRING = "yyyy/MM/dd";

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.stringToDate(null, FORMAT_STRING),
					is(nullValue(Date.class)));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.stringToDate("", FORMAT_STRING),
					is(nullValue(Date.class)));
		}

		@Test
		public void 日付に変換できない文字が含んでいる場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.stringToDate("2013/0x/0x", FORMAT_STRING),
					is(nullValue(Date.class)));
		}

		@Test
		public void 指定の文字列とフォーマットが一致していない場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.stringToDate("2013-11-01", FORMAT_STRING),
					is(nullValue(Date.class)));
			assertThat(DateUtil.stringToDate("2013", FORMAT_STRING),
					is(nullValue(Date.class)));
		}

		@Test
		public void 指定の文字列とフォーマットが一致している場合() throws Exception {
			// Setup
			Calendar expected = Calendar.getInstance();
			expected.set(2013, Calendar.NOVEMBER, 1, 0, 0, 0);

			// Exercise
			// Verify
			assertThat(DateUtils.isSameDay(
					DateUtil.stringToDate("2013/11/01", FORMAT_STRING),
					expected.getTime()), is(true));
		}
	}

	public static class GetDayOfWeek {

		private static final String FORMAT_STRING = "yyyy/MM/dd";

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.getDayOfWeek(null), is(""));
		}

		@Test
		public void 正しい日付の場合() throws Exception {
			Date date = DateUtil.stringToDate("2013/11/01", FORMAT_STRING);

			// Exercise
			// Verify
			assertThat(DateUtil.getDayOfWeek(date),
					is(JapaneseDayOfWeekCharacter.FRI.getValue()));
		}
	}

	public static class MatchFormat {

		private static final SimpleDateFormat FORMAT_yMdHms = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		@Test
		public void Nullの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.matchFormat(null, FORMAT_yMdHms), is(false));
		}

		@Test
		public void ブランクの場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(DateUtil.matchFormat("", FORMAT_yMdHms), is(false));
		}

		@Test
		public void 異なる形式の文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(
					DateUtil.matchFormat("2013/09/01 10:10:10", FORMAT_yMdHms),
					is(false));
		}

		@Test
		public void 指定と同じ形式の文字列の場合() throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(
					DateUtil.matchFormat("2013-09-01 10:10:10", FORMAT_yMdHms),
					is(true));
		}
	}
}
