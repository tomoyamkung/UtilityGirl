package net.tomoyamkung.library.validate.doubles;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.validate.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DoubleRangeValidatorTest {
	
	private static final Double MAX_VALUE = 150.01;
	private static final Double MIN_VALUE = 123.45;

	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false),
		new Fixture("!", false),
		new Fixture("123.44", false),
		new Fixture("123.45", true),
		new Fixture("123.46", true),
		new Fixture("150.00", true),
		new Fixture("150.01", true),
		new Fixture("150.02", false)
	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		Validator sut = new DoubleRangeValidator(MIN_VALUE, MAX_VALUE);
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(String.format("target:%s,expected:%s", p.target, p.expected), actual, is(p.expected));
	}

}
