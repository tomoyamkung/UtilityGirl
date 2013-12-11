package net.tomoyamkung.library.util;

import java.util.ArrayList;

/**
 * <code>ArrayList</code> を拡張したクラス。
 * 
 * <code>ArrayList#add</code> を連続して書けるように拡張したクラス。
 * 
 * @author tomoyamkung
 * 
 * @param <E>
 *            型パラメータ
 */
public class ExtArrayList<E> extends ArrayList<E> {

	private static final long serialVersionUID = -3323720716588400248L;

	/**
	 * 要素を追加する。
	 * 
	 * @param element
	 *            要素
	 * @return
	 */
	public ExtArrayList<E> addThis(E element) {
		this.add(element);
		return this;
	}

}
