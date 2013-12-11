package net.tomoyamkung.library.validate.integer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class IntegerRangeValidatorTest {

	private static final Integer MAX_VALUE = 9;
	private static final Integer MIN_VALUE = 0;

	@SuppressWarnings("rawtypes")
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture<String, Boolean>(null, false),
			new Fixture<String, Boolean>("", false),
			new Fixture<String, Boolean>(MIN_VALUE.toString(), true), // 最小値と同じ
			new Fixture<String, Boolean>(String.valueOf(MIN_VALUE - 1), false), // 最小値より1つ小さい
			new Fixture<String, Boolean>(MAX_VALUE.toString(), true), // 最大値と同じ
			new Fixture<String, Boolean>(String.valueOf(MAX_VALUE + 1), false), // 最大値より1つ大きい
	};

	@Theory
	public void test(Fixture<String, Boolean> p) throws Exception {
		// Setup
		Validator sut = new IntegerRangeValidator(MIN_VALUE, MAX_VALUE);

		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
