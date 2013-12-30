package net.tomoyamkung.library.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * ディレクトリに関するユーティリティクラス。
 * 
 * @author tomoyamkung
 *
 */
public class DirectoryUtil {
	
	/**
	 * ディレクトリを再作成する。
	 * 
	 * ディレクトリが存在する場合は、削除後に同名のディレクトリを作成する。
	 * 
	 * @param parentDirPath 親ディレクトリの絶対パス
	 * @param dirName 作成するディレクトリの名前
	 * @return 作成したディレクトリ
	 * @throws IOException ディレクトリの削除、または、作成に失敗した場合
	 */
	public static File recreate(String parentDirPath, String dirName) throws IOException {
		File d = new File(parentDirPath, dirName);
		if(d.exists()) {
			FileUtils.forceDelete(d);
		}
		FileUtils.forceMkdir(d);
		
		return d;
	}

}
