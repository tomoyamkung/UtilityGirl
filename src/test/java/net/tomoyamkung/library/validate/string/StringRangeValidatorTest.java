package net.tomoyamkung.library.validate.string;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Fixture;
import net.tomoyamkung.library.validate.Validator;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class StringRangeValidatorTest {

	private static final Integer MAX_VALUE = 9;
	private static final Integer MIN_VALUE = 1;
	
	@DataPoints
	public static Fixture[] PARAMS = {
		new Fixture(null, false),
		new Fixture("", false), // 最小値より1文字少ない
		new Fixture(StringUtil.createString(MIN_VALUE), true), // 最小値と同じ
		new Fixture(StringUtil.createString(MIN_VALUE + 1), true), // 最小値より1文字多い
		new Fixture(StringUtil.createString(MAX_VALUE - 1), true), // 最大値より1文字少ない
		new Fixture(StringUtil.createString(MAX_VALUE), true), // 最大値と同じ
		new Fixture(StringUtil.createString(MAX_VALUE + 1), false), // 最大値より1つ大きい
	};

	@Theory
	public void test(Fixture p) throws Exception {
		// Setup
		Validator sut = new StringRangeValidator(MIN_VALUE, MAX_VALUE);
		
		// Exercise
		boolean actual = sut.execute(p.target);

		// Verify
		assertThat(String.format("target:%s,expected:%s", p.target, p.expected), actual, is(p.expected));
	}

}
