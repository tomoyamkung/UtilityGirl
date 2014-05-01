package net.tomoyamkung.library.props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import net.tomoyamkung.library.util.StringUtil;

/**
 * プロパティファイル app.properties を扱うシングルトンクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class AppProperties {

	/**
	 * 参照するプロパティファイル（クラスパス上にあること）。
	 */
	private static final String PROP_FILE_NAME = "app.properties";

	/**
	 * 本クラスのインスタンス。
	 */
	private static AppProperties instance;

	private Properties prop;

	/**
	 * app.properties を読み込む。
	 * 
	 */
	private AppProperties(String path) {
		prop = new Properties();

		InputStream inputStream = null;
		if(StringUtil.isNullOrEmpty(path)) {
			inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(PROP_FILE_NAME);
		} else {
			try {
				inputStream = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			prop.load(new InputStreamReader(inputStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * プロパティファイルはクラスパス上にある "app.properties" を参照する。
	 * 
	 * @return
	 */
	public static AppProperties getInstance() {
		if(instance == null) {
			instance = new AppProperties("");
		}
		return instance;
	}

	/**
	 * インスタンスを生成する。
	 * 
	 * 指定したパスのプロパティファイルを読み込む。
	 * 
	 * @param path プロパティファイルのパス
	 * @return
	 */
	public static AppProperties getInstance(String path) {
		if(instance == null) {
			instance = new AppProperties(path);
		}
		return instance;
	}

	/**
	 * 指定のキーに該当する値を取得する。
	 * 
	 * 次の条件に一致する場合、<code>IllegalArgumentException</code> を発生する。
	 * 
	 * <ul>
	 * <li>key が null</li>
	 * <li>key に該当する値が定義されていない</li>
	 * </ul>
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("key が指定されていません");
		}

		String value = prop.getProperty(key);
		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("該当する値がありません");
		}

		return value.trim();
	}

	/**
	 * 指定のキーに該当する値を int に変換して取得する。
	 * 
	 * 次の条件に一致する場合、<code>IllegalArgumentException</code> を発生する。
	 * 
	 * <ul>
	 * <li>key が null</li>
	 * <li>key に該当する値が定義されていない</li>
	 * </ul>
	 * 
	 * 値が int に変換できない場合は <code>NumberFormatException</code> を発生する。
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		String value = get(key);
		return Integer.parseInt(value);
	}

	/**
	 * 指定のキーに該当する値を String を格納する List に詰めて取得する。
	 * 
	 * 該当する値は CSV で定義されていること。 CSV で定義されていない場合は size が 1 の List を返す。
	 * 
	 * 次の条件に一致する場合、<code>IllegalArgumentException</code> を発生する。
	 * 
	 * <ul>
	 * <li>key が null</li>
	 * <li>key に該当する値が定義されていない</li>
	 * </ul>
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getStringList(String key) {
		String value = get(key);
		return Arrays.asList(value.split(","));
	}

}
