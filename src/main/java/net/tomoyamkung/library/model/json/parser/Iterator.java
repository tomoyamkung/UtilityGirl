package net.tomoyamkung.library.model.json.parser;

/**
 * Iterator インタフェース。
 * 
 * @author tomoyamkung
 * 
 * @param <T>
 *            <code>Iterator</code> が返すオブジェクト
 */
public interface Iterator<T> {

	/**
	 * 次の要素があるかを問い合わせる。
	 * 
	 * @return 次の要素がある場合 true
	 */
	public boolean hasNext();

	/**
	 * 次の要素を取得する。
	 * 
	 * @return
	 */
	public T next();

}
