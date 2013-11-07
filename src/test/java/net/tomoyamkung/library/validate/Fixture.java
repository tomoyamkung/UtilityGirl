package net.tomoyamkung.library.validate;

/**
 * テストの検証値と期待値を格納した Fixture クラス。
 * 
 * @author tomoyamkung
 *
 */
public class Fixture {
	
	/**
	 * テストの検証値。
	 */
	public String target;
	
	/**
	 * テストの期待値。
	 */
	public boolean expected;

	/**
	 * テストの検証値と期待値を設定する。
	 * 
	 * @param target テストの検証値
	 * @param expected テストの期待値
	 */
	public Fixture(String target, boolean expected) {
		this.target = target;
		this.expected = expected;
	}
}
