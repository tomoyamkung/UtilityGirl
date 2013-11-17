package net.tomoyamkung.library.util;

import java.util.HashMap;

/**
 * <code>HashMap</code> を拡張したクラス。
 * 
 * <code>HashMap#put</code> を連続して書けるように拡張したクラス。
 * 
 * @author tomoyamkung
 *
 * @param <K> キーとなる型パラメータ
 * @param <V> 値となる型パラメータ
 */
public class ExtHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1002187012074784628L;

	/**
	 * 要素を追加する。
	 * 
	 * @param key キー
	 * @param value 値
	 * @return
	 */
	public ExtHashMap<K, V> putThis(K key, V value) {
		this.put(key, value);
		return this;
	}
}
