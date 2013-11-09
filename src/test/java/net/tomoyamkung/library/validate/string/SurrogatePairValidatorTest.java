package net.tomoyamkung.library.validate.string;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.validate.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SurrogatePairValidatorTest {
	
	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false),
		new Fixture("𠀋aあ", false),
		new Fixture("aあ", true)

	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		Validator sut = new SurrogatePairValidator();
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
