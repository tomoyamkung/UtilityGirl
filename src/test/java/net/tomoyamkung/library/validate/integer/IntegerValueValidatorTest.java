package net.tomoyamkung.library.validate.integer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.validate.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class IntegerValueValidatorTest {
	
	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false),
		new Fixture("あ", false),
		new Fixture("123L", false),
		new Fixture("１", true),
		new Fixture("123", true)
	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		Validator sut = new IntegerValueValidator();
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
