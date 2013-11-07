package net.tomoyamkung.library.model.string;

/**
 * 文字列をトリミングするクラス。
 * 
 * @author tomoyamkung
 *
 */
public class Trimming {
	
	/**
	 * トリミングされる文字列。
	 */
	private String value;

	/**
	 * トリミングを行う目印となる文字列。
	 */
	private String marker;
	
	/**
	 * トリミングの開始位置と終了位置を管理するクラス。
	 */
	private TrimmingPoint point;
	
	/**
	 * トリミングされる文字列とトリミングを行う目印となる文字列を設定する。
	 * 
	 * @param value トリミングされる文字列
	 * @param marker トリミングを行う目印となる文字列。この目印を区切りとしてトリミングを行う。
	 */
	public Trimming(String value, String marker) {
		this.value = value;
		this.marker = marker;
		
		point = new TrimmingPoint();
	}
	
	/**
	 * トリミングの開始位置と終了位置を次に進める。
	 * 
	 */
	public void shift() {
		int nextEnd = value.indexOf(marker, point.getEnd()) + 1;
		point.shift(nextEnd, value.length());
	}
	
	/**
	 * トリミングを実行する。
	 * 
	 * @return
	 */
	public String execute() {
		return value.substring(point.getBegin(), point.getEnd());
	}

}
