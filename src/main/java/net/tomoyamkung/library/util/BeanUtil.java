package net.tomoyamkung.library.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	 * 自分自身に定義されているフィールドと、全スーパークラスに定義されているフィールドも取得対象とする。
	 * 
	 * @param src
	 *            取得するオブジェクト
	 * @return
	 */
	public static <T1> List<Field> getFields(T1 src) {
		List<Field> fields = new ArrayList<Field>();

		fields.addAll(getDeclaredFields(src.getClass()));
		for (Class<?> superClass : ClassUtils
				.getAllSuperclasses(src.getClass())) {
			fields.addAll(getDeclaredFields(superClass));
		}

		return fields;
	}

	/**
	 * フィールドを一覧で取得する。
	 * 
	 * 特定のアノテーションが付与されているフィールドは除外する。
	 * 
	 * フィールドは <code>BeanUtil{@link #getFields(Object)}</code> で取得する。
	 * 
	 * @param src
	 *            取得するオブジェクト
	 * @param excludeAnnotation
	 *            取得から除外したいアノテーション
	 * @return
	 */
	public static <T> List<Field> getFields(T src,
			Class<? extends Annotation> excludeAnnotation) {
		List<Field> fields = getFields(src);
		for (int i = 0; i < fields.size(); i++) {
			if (hasAnnotation(fields.get(i), excludeAnnotation)) {
				fields.remove(i--);
			}
		}

		return fields;
	}

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
	 * フィールド名は camelCase と snake_case の両方で検索する。
	 * 
	 * @param fieldName
	 * @param e
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static <E> Field getField(String fieldName, E e)
			throws NoSuchFieldException, SecurityException {
		Field field = null;
		try {
			field = e.getClass().getDeclaredField((fieldName));
		} catch (NoSuchFieldException ex) {
			try {
				field = e.getClass().getDeclaredField(
						StringUtil.toLowerSnakeCase(fieldName));
			} catch (NoSuchFieldException ex2) {
				field = e.getClass().getDeclaredField(
						StringUtil.toLowerCamelCase(fieldName));
			}
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

	/**
	 * 指定のクラスを生成し、各フィールドを初期化する。
	 * 
	 * 初期化するフィールドとその値は、フィールド名とフィールド値を格納した <code>Map</code> で指定する。
	 * 
	 * @param clazz
	 *            生成するクラス
	 * @param fieldValue
	 *            初期化するフィールドとその値を格納した <code>Map</code>
	 * @return
	 * @throws InstantiationException
	 *             インスタンスの生成に失敗した場合
	 * @throws IllegalAccessException
	 *             インスタンスの生成に失敗した場合
	 * @throws NoSuchFieldException
	 *             該当するフィールドがクラスに定義されていなかった場合
	 * @throws SecurityException
	 *             該当するフィールドへのアクセスに失敗した場合
	 */
	public static <T> T create(Class<T> clazz, Map<String, Object> fieldValue)
			throws InstantiationException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		T obj = clazz.newInstance();

		for (Iterator<String> it = fieldValue.keySet().iterator(); it.hasNext();) {
			String fieldName = it.next();
			Field field = getField(fieldName, obj);
			if (field == null) {
				continue;
			}

			Object value = fieldValue.get(fieldName);
			if (field.getType().equals(Integer.class)
					&& value instanceof BigDecimal) {
				// value は BigDecimal なのだが、field の型が Integer
				// のパターンがあったので、`intValue()` にしている
				value = ((BigDecimal) value).intValue();
			} else if (field.getType().equals(java.util.Date.class)
					&& value instanceof java.sql.Timestamp) {
				java.sql.Timestamp ts = (java.sql.Timestamp) value;
				value = new Date(ts.getTime());
			}
			field.set(obj, value);
		}

		return (T) obj;
	}

	/**
	 * 指定のクラスを生成し、各フィールドを初期化する。
	 * 
	 * 初期化するフィールドとその値は、フィールド名とフィールド値を格納した <code>Map</code> で指定する。
	 * 
	 * @param clazz
	 *            生成するクラス
	 * @param fieldValue
	 *            初期化するフィールドとその値を格納した <code>Map</code> を格納した <code>List</code>
	 * @return
	 * @throws InstantiationException
	 *             インスタンスの生成に失敗した場合
	 * @throws IllegalAccessException
	 *             インスタンスの生成に失敗した場合
	 * @throws NoSuchFieldException
	 *             該当するフィールドがクラスに定義されていなかった場合
	 * @throws SecurityException
	 *             該当するフィールドへのアクセスに失敗した場合
	 */
	public static <T> List<T> creates(Class<T> clazz,
			List<Map<String, Object>> fieldValues)
			throws InstantiationException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		List<T> objects = new ArrayList<T>();
		for (Map<String, Object> fieldValue : fieldValues) {
			objects.add(create(clazz, fieldValue));
		}
		return objects;
	}

}
