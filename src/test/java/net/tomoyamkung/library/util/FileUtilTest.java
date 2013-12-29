package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import net.tomoyamkung.library.Fixture;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class FileUtilTest {
	
	public static class Create {
		
		private File parentDir = new File("src/test/resources/net/tomoyamkung/library/util");
		private String fileName = "FileUtilTest_create.txt";
		
		@Test
		public void 同名のファイルが存在している場合() throws Exception {
			// Setup
			File file = new File(parentDir, fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			long lastModified = new File(parentDir, fileName).lastModified();

			// Exercise
			File actual = FileUtil.create(parentDir, fileName);

			// Verify
			assertThat("ファイルを再作成しないので更新日時は変わらない",
					actual.lastModified(), is(lastModified));
		}
		
		public void 同名のファイルが存在しない場合() throws Exception {
			// Setup
			File file = new File(parentDir, fileName);
			if(file.exists()) {
				FileUtils.forceDelete(file);
			}
			assertThat(new File(parentDir, fileName).exists(), is(false));
			
			// Exercise
			File actual = FileUtil.create(parentDir, fileName);

			// Vefiry
			assertThat(actual.exists(), is(true));
		}

		
	}
	
	@RunWith(Theories.class)
	public static class GetExtension {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<File, String>(null, ""),
				new Fixture<File, String>(new File("a.jpg"), "jpg"),
				new Fixture<File, String>(new File("abc"), "")
		};

		@Theory
		public void test(Fixture<File, String> p) throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(String.format("TARGET:%s EXPECTED:%s", p.target, p.expected),
					FileUtil.getExtension(p.target), is(p.expected));
		}
	}

	@RunWith(Theories.class)
	public static class IsExtensionAsImageFile {

		@SuppressWarnings("rawtypes")
		@DataPoints
		public static Fixture[] PARAMS = {
				new Fixture<String, Boolean>(null, false),
				new Fixture<String, Boolean>("", false),
				new Fixture<String, Boolean>("a.jpg", true),
				new Fixture<String, Boolean>("a.JPG", true),
				new Fixture<String, Boolean>("a.jpeg", true),
				new Fixture<String, Boolean>("a.JPEG", true),
				new Fixture<String, Boolean>("a.gif", true),
				new Fixture<String, Boolean>("a.GIF", true),
				new Fixture<String, Boolean>("a.png", true),
				new Fixture<String, Boolean>("a.PNG", true),
				new Fixture<String, Boolean>("a.txt", false)
		};

		@Theory
		public void test(Fixture<String, Boolean> p) throws Exception {
			// Setup
			// Exercise
			// Verify
			assertThat(String.format("TARGET:%s EXPECTED:%s", p.target, p.expected),
					FileUtil.isExtensionAsImageFile(p.target), is(p.expected));
		}
	}

}
