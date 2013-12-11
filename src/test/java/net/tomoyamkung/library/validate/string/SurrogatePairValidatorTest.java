package net.tomoyamkung.library.validate.string;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SurrogatePairValidatorTest {

	@SuppressWarnings("rawtypes")
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture<String, Boolean>(null, false),
			new Fixture<String, Boolean>("", false),
			new Fixture<String, Boolean>("𠀋aあ", false),
			new Fixture<String, Boolean>("aあ", true)

	};

	@Theory
	public void test(Fixture<String, Boolean> p) throws Exception {
		// Setup
		Validator sut = new SurrogatePairValidator();

		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(actual, is(p.expected));
	}

}
