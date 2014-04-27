package net.tomoyamkung.library.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.tomoyamkung.library.props.AppProperties;

import org.apache.commons.io.FileUtils;


/**
 * ファイルに関するユーティリティクラス。
 * 
 * @author tomoyamkung
 *
 */
public class FileUtil {
	
	private static final int KB = 1024;
	
	/**
	 * ファイルを作成する（実ファイルを作成する）。
	 * 
	 * <ul>
	 * <li>実ファイルが存在する → 何もしない</li>
	 * <li>実ファイルが存在しない → ファイルを作成する</li>
	 * </ul>
	 * 
	 * @param parentDir ファイルを作成するディレクトリ
	 * @param fileName ファイル名
	 * @return File オブジェクト
	 * @throws IOException ファイルの作成に失敗した場合
	 */
	public static File create(File parentDir, String fileName) throws IOException {
		File f = new File(parentDir, fileName);
		if(f.exists()) {
			return f;
		}
		f.createNewFile();
		
		return f;
	}

	private static long getSize(File file) {
		return FileUtils.sizeOf(file);
	}
	
	/**
	 * ファイルサイズを取得する。
	 * 
	 * 単位は KB。
	 * 
	 * @param file
	 * @return
	 */
	public static long getSizeKB(File file) {
		return getSize(file) / KB;
	}
	
	/**
	 * 拡張子を取得する。
	 * 
	 * @param file 拡張子を取得した File オブジェクト
	 * @return
	 */
	public static String getExtension(File file) {
		if(file == null) {
			return ""; // IllegalArgumentException のほうが適切かも
		}

		String[] array = file.getName().split("\\.");
		if(array.length < 2) {
			return "";
		}
		return array[array.length - 1];
	}
	
	/**
	 * 画像ファイルであるかを拡張子で問い合わせる。
	 * 
	 * 拡張子が次のどれかに当てはまれば画像ファイルと見なす。
	 * 
	 * <ul>
	 * <li>.jpg, .JPG</li>
	 * <li>.jpeg, .JPEG</li>
	 * <li>.gif, .GIF</li>
	 * <li>.png, .PNG</li>
	 * </ul>
	 * @param fileName 検査するファイル名
	 * 
	 * @return 拡張子が当てはまれば true
	 */
	public static boolean isExtensionAsImageFile(String fileName) {
		if(StringUtil.isNullOrEmpty(fileName)) {
			return false;
		}

		List<String> extensions;
		try {
			extensions = AppProperties.getInstance().getStringList("upload.file.image.extensions");
		} catch(IllegalArgumentException e) {
			extensions = getExtensions();
		}

		for(String extension : extensions) {
            if(isMatchExtension(fileName, extension)) {
                return true;
            }
        }
		return false;
	}
	
	/**
	 * ファイルが指定の拡張子で終了しているかを問い合わせる。
	 * 
	 * @param fileName ファイル名
	 * @param extension 検査する拡張子
	 * @return 指定の拡張子で終了していれば true
	 */
	private static boolean isMatchExtension(String fileName, String extension) {
		if(fileName.endsWith(extension)) {
			return true;
		}
		
		if(fileName.endsWith(extension.toUpperCase())) {
			return true;
		}
		
		return false;
	}

	/**
	 * 画像ファイルとして許可する拡張子を取得する。
	 */
	private static List<String> getExtensions() {
		return new ExtArrayList<String>()
				.addThis(".jpg").addThis(".JPG")
				.addThis(".jpeg").addThis(".JPEG")
				.addThis(".gif").addThis(".GIF")
				.addThis(".png").addThis(".PNG");
	}
}
