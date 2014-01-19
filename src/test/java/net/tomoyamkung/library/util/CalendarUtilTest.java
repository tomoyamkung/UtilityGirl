package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CalendarUtilTest {
	
	private static final int YEAR = 2014;
	private static final int MONTH = Calendar.JANUARY;
	
	public static class GetLastTimeOfHour {
		
		private static final int DATE = 1;
		private static final int HOUR_OF_DAY = 10;
		
		@Test
		public void test() throws Exception {
			// Exercise
			Calendar actual = CalendarUtil.getLastTimeOfHour(YEAR, MONTH, DATE, HOUR_OF_DAY);
			
			// Verify
			assertThat(actual.get(Calendar.YEAR), is(2014));
			
		}
	}

	public static class GetCalendars {
		
		@Test
		public void test_2014年1月21日から31日までのCalendarオブジェクトを取得する() throws Exception {
			// Setup
			int startDate = 21;

			// Exercise
			List<Calendar> actual = CalendarUtil.getCalendars(YEAR, MONTH, startDate);

			// Verify
			assertThat(actual.size(), is(11));
			
			Calendar calendar = actual.get(0);
			assertThat(calendar.get(Calendar.YEAR), is(YEAR));
			assertThat(calendar.get(Calendar.MONTH), is(MONTH));
			assertThat(calendar.get(Calendar.DATE), is(startDate));
			assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(0));
			assertThat(calendar.get(Calendar.MINUTE), is(0));
			assertThat(calendar.get(Calendar.SECOND), is(0));
			
			calendar = actual.get(actual.size() - 1);
			assertThat(calendar.get(Calendar.DATE), is(31));
			
		}

		@Test
		public void test_2014年1月1日から31日までのCalendarオブジェクトを取得する() throws Exception {
			// Setup
			int startDate = 1;
			
			// Exercise
			List<Calendar> actual = CalendarUtil.getCalendars(YEAR, MONTH, startDate);

			// Verify
			assertThat(actual.size(), is(31));
		}
	}
	
	public static class GetCalendarsOfOneMonth {
		
		@Test
		public void test_2014年1月のCalendarオブジェクトを取得する() throws Exception {
			// Exercise
			List<Calendar> actual = CalendarUtil.getCalendarsOfOneMonth(YEAR, MONTH);
			
			// Verify
			assertThat(actual.size(), is(31));
		}
	}
}
