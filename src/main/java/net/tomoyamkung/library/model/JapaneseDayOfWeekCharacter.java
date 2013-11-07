package net.tomoyamkung.library.model;

import java.util.List;

import net.tomoyamkung.library.util.ExtArrayList;

/**
 * 日本語の「曜日」を定義するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public enum JapaneseDayOfWeekCharacter {

	/**
	 * 日曜日。
	 */
	SUN("日"),

	/**
	 * 月曜日。
	 */
	MON("月"),

	/**
	 * 火曜日。
	 */
	TUE("火"),

	/**
	 * 水曜日。
	 */
	WED("水"),

	/**
	 * 木曜日。
	 */
	THU("木"),

	/**
	 * 金曜日。
	 */
	FRI("金"),

	/**
	 * 土曜日。
	 */
	SAT("土");

	/**
	 * 「曜日」。
	 */
	private String value;

	JapaneseDayOfWeekCharacter(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 曜日の定義をリストで取得する。
	 * 
	 * リストには「日」「月」などの日本語の文字列を格納する。
	 * 
	 * @return
	 */
	public static List<String> asStringList() {
		List<String> list = new ExtArrayList<String>().addThis(SUN.getValue())
				.addThis(MON.getValue()).addThis(TUE.getValue())
				.addThis(WED.getValue()).addThis(THU.getValue())
				.addThis(FRI.getValue()).addThis(SAT.getValue());
		return list;

	}

}
