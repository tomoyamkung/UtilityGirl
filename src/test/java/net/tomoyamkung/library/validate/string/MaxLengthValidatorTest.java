package net.tomoyamkung.library.validate.string;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.Fixture;
import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MaxLengthValidatorTest {

	private static final int MAX_LENGTH = 10;

	@SuppressWarnings("rawtypes")
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture<String, Boolean>(null, false),
			new Fixture<String, Boolean>("", false),
			new Fixture<String, Boolean>(
					StringUtil.createString(MAX_LENGTH - 1), true),
			new Fixture<String, Boolean>(StringUtil.createString(MAX_LENGTH),
					true),
			new Fixture<String, Boolean>(
					StringUtil.createString(MAX_LENGTH + 1), false) };

	@Theory
	public void test(Fixture<String, Boolean> p) throws Exception {
		// Setup
		Validator sut = new MaxLengthValidator(MAX_LENGTH);

		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
