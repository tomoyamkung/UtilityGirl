package net.tomoyamkung.library.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tomoyamkung.library.model.FieldValue;

import org.apache.commons.lang3.ClassUtils;

/**
 * オブジェクトについてのユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class BeanUtil {

	/**
	 * フィールドの値をコピーする。
	 * 
	 * スーパークラスに設定されているフィールドもコピーの対象とする。<br />
	 * コピーの際に発生する例外は <code>RuntimeException</code> にラップして <code>throw</code>
	 * するようになっている。<br />
	 * 
	 * @param src
	 *            コピー元
	 * @param dest
	 *            コピー先
	 */
	public static <T1, T2> void copy(T1 src, T2 dest) {
		for (Field f : getFields(src)) {
			try {
				setValue(new FieldValue(f, src), dest);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException(e);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * フィールドを一覧で取得する。
	 * 
	 * 自分自身に定義されているフィールドとスーパークラスに定義されているフィールドも取得対象とする。
	 * 
	 * @param src
	 *            取得するオブジェクト
	 * @return
	 */
	private static <T1> List<Field> getFields(T1 src) {
		List<Field> fields = new ArrayList<Field>();

		fields.addAll(getDeclaredFields(src.getClass()));
		for(Class<?> superClass : ClassUtils.getAllSuperclasses(src.getClass())) {
			fields.addAll(getDeclaredFields(superClass));
		}
		
		return fields;
	}

	/**
	 * クラスに定義されているフィールドを一覧で取得する。
	 * 
	 * フィールドは自分自身に定義されている全てのフィールドを対象とする（スーパークラスは対象外）。<br />
	 * また、フィールドはアクセス可能な状態に設定する。
	 * 
	 * @param clazz
	 *            取得する Class オブジェクト
	 * @return
	 */
	private static List<Field> getDeclaredFields(Class<? extends Object> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Field f = declaredFields[i];
			f.setAccessible(true);
		}
		return Arrays.asList(declaredFields);
	}

	/**
	 * フィールドを取得する。
	 * 
	 * 指定したフィールド名がそのクラスに定義されていない場合は、スーパークラスを検索する。
	 * 
	 * @param fieldName
	 * @param e
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static <E> Field getField(String fieldName, E e)
			throws NoSuchFieldException, SecurityException {
		Field field = null;
		try {
			field = e.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException ex) {
			field = e.getClass().getDeclaredField(StringUtil.toLowerSnakeCase(fieldName));
		}

		if (field == null) {
			return null;
		}

		field.setAccessible(true);
		return field;
	}

	/**
	 * オブジェクトにフィールドの値を設定する。
	 * 
	 * 指定したフィールドがオブジェクトに定義されている場合は、フィールドの値を設定する。<br />
	 * 定義されていない場合は何もしない。
	 * 
	 * @param fieldValue
	 *            設定したいフィールド情報を保持しているオブジェクト
	 * @param dest
	 *            フィールドの値が設定されるオブジェクト
	 * @throws NoSuchFieldException
	 *             値の設定に失敗した場合
	 * @throws SecurityException
	 *             値の設定に失敗した場合
	 * @throws IllegalArgumentException
	 *             値の設定に失敗した場合
	 * @throws IllegalAccessException
	 *             値の設定に失敗した場合
	 */
	private static <T2> void setValue(FieldValue fieldValue, T2 dest)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getField(fieldValue.getName(), dest);
		if (field == null) {
			return;
		}

		field.set(dest, fieldValue.getValue());
	}
}
