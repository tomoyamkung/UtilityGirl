package net.tomoyamkung.library.util;

import java.util.List;

/**
 * List に関するユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class ListUtil {

	/**
	 * インデックスの位置にあるオブジェクトを末尾に追加する。
	 * 
	 * 次の場合は何も処理を行わないで終了する。
	 * 
	 * <ul>
	 * <li><code>from</code> が <code>null</code> または空の場合</li>
	 * <li><code>from</code> のサイズよりも <code>index</code> のほうが大きい場合</li>
	 * </ul>
	 * 
	 * @param from
	 *            コピーされるリスト
	 * @param index
	 *            コピーするオブジェクトのインデックス
	 * @param to
	 *            追加されるリスト
	 */
	public static <T> void copy(List<T> from, int index, List<T> to) {
		if (isNullOrEmpty(from)) {
			return;
		}
		if (from.size() <= index) {
			return;
		}

		to.add(from.get(index));

	}

	/**
	 * リストが null または空であるかを問い合わせる。
	 * 
	 * @param list
	 *            検査するリスト
	 * @return null または空の場合 true
	 */
	public static <T> boolean isNullOrEmpty(List<T> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}

		return false;
	}
}
