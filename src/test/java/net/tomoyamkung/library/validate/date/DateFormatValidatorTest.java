package net.tomoyamkung.library.validate.date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DateFormatValidatorTest {

	private static final String formatString = "yyyy-MM-dd HH:mm:ss";

	@SuppressWarnings("rawtypes")
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture<String, Boolean>(null, false),
			new Fixture<String, Boolean>("", false),
			new Fixture<String, Boolean>("あ", false),
			new Fixture<String, Boolean>("2013/09/01 10:10:10", false),
			new Fixture<String, Boolean>("2013-09-01 10:10:10", true) };

	@Theory
	public void test(Fixture<String, Boolean> p) throws Exception {
		// Setup
		Validator sut = new DateFormatValidator(formatString);

		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(
				String.format("target:%s,expected:%s", p.target, p.expected),
				actual, is(p.expected));
	}

}
