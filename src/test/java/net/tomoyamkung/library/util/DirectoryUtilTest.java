package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DirectoryUtilTest {
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	private String parentDirPath;
	private String dirName = "children";
	private File parentDir;
	
	@Before
	public void setup() throws Exception {
		createParentDir();
	}

	private void createParentDir() {
		parentDirPath = tempFolder.getRoot().getAbsolutePath();
		parentDir = new File(parentDirPath, dirName);
		parentDir.mkdirs();
		assertThat(parentDir.exists(), is(true));
	}

	@Test
	public void recreate() throws Exception {
		// Setup
		long lastModified = parentDir.lastModified();

		// Exercise
		File actual = DirectoryUtil.recreate(parentDirPath, dirName);

		// Verify
		assertThat(lastModified <= actual.lastModified(), is(true));
	}

}
