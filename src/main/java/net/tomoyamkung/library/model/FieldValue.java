package net.tomoyamkung.library.model;

import java.lang.reflect.Field;

/**
 * フィールドの名称と値を保持するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class FieldValue {
	
	/**
	 * フィールドの名称。
	 */
	private String name;
	
	/**
	 * フィールドの値。
	 */
	private Object value;
	
	/**
	 * 保持するフィールドの名称と値を設定する。
	 * 
	 * @param name フィールドの名称
	 * @param value フィールドの値
	 */
	public FieldValue(String name, Object value) {
		this.name = name;
		
		if(value == null) {
			return;
		}
		this.value = value;
	}
	
	/**
	 * 保持するフィールドの名称と値を設定する。
	 * 
	 * @param src フィールドオブジェクト
	 * @param obj <code>src</code> を保持しているオブジェクト
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public <T> FieldValue(Field src, T obj) throws IllegalArgumentException, IllegalAccessException {
		this(src.getName(), src.get(obj));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
