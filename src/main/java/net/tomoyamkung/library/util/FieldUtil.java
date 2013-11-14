package net.tomoyamkung.library.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * <code>Field</code> についてのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class FieldUtil {

	/**
	 * <code>Field</code> に特定のアノテーションが付与されているかを問い合わせる。
	 * 
	 * @param field
	 *            検査する <code>Field</code>
	 * @param annotation
	 *            付与されているかを問い合わせるアノテーション
	 * @return 付与されている場合 true
	 */
	public static boolean hasAnnotation(Field field,
			Class<? extends Annotation> annotation) {
		return field.getAnnotation(annotation) != null;
	}

	/**
	 * フィールドが値を保持しているかを問い合わせる。
	 * 
	 * 処理中に発生する例外は <code>RuntimeException</code> にラップするようになっている。
	 * 
	 * @param field
	 *            確認するフィールド
	 * @param object
	 *            このフィールドを格納しているオブジェクト
	 * @return 値を保持している場合 true
	 */
	public static boolean hasValue(Field field, Object object) {
		try {
			return field.get(object) != null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
