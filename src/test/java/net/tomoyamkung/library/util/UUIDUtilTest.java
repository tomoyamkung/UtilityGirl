package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UUIDUtilTest {

	@Test
	public void UUIDがブランクで生成されること() {
		// Setup
		// Exercise
		String actual = UUIDUtil.createType4();

		// Verify
		assertThat("ブランクで生成されないこと", actual, is(not(nullValue(String.class))));
		assertThat("生成される UUID の長さが 36 であること", actual.length(), is(36));
	}

}
