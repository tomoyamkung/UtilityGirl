package net.tomoyamkung.library.validate.doubles;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DoubleValueValidatorTest {

	@SuppressWarnings("rawtypes")
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture<String, Boolean>(null, false),
			new Fixture<String, Boolean>("", false),
			new Fixture<String, Boolean>("あ", false),
			new Fixture<String, Boolean>("123L", false),
			new Fixture<String, Boolean>("１", false),
			new Fixture<String, Boolean>("123", true),
			new Fixture<String, Boolean>("123.123", true) };

	@Theory
	public void test(Fixture<String, Boolean> p) throws Exception {
		// Setup
		Validator sut = new DoubleValueValidator();

		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(
				String.format("target:%s,expected:%s", p.target, p.expected),
				actual, is(p.expected));
	}

}
