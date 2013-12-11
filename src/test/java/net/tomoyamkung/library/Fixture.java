package net.tomoyamkung.library;

/**
 * テストの検証値と期待値を格納した Fixture クラス。
 * 
 * @author tomoyamkung
 * 
 * @param <T1>
 * @param <T2>
 */
public class Fixture<T1, T2> {

	/**
	 * テストの検証値。
	 */
	public T1 target;

	/**
	 * テストの期待値。
	 */
	public T2 expected;

	/**
	 * テストの検証値と期待値を設定する。
	 * 
	 * @param target
	 *            テストの検証値
	 * @param expected
	 *            テストの期待値
	 */
	public Fixture(T1 target, T2 expected) {
		this.target = target;
		this.expected = expected;
	}
}
