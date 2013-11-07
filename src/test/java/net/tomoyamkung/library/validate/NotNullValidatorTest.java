package net.tomoyamkung.library.validate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class NotNullValidatorTest {

	static class Fixture {
		String target;
		boolean expected;

		Fixture(String target, boolean expected) {
			this.target = target;
			this.expected = expected;
		}
	}

	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false),
		new Fixture("a", true)
	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		NotNullValidator sut = new NotNullValidator();
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
