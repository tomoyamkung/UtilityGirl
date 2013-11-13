package net.tomoyamkung.library.validate.date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.validate.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DateFormatValidatorTest {
	
	private static final String formatString = "yyyy-MM-dd HH:mm:ss";

	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false),
		new Fixture("あ", false),
		new Fixture("2013/09/01 10:10:10", false),
		new Fixture("2013-09-01 10:10:10", true)
	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		Validator sut = new DateFormatValidator(formatString);
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(String.format("target:%s,expected:%s", p.target, p.expected), actual, is(p.expected));
	}

}
