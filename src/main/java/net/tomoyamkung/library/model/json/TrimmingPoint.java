package net.tomoyamkung.library.model.json;

/**
 * トリミングの開始位置と終了位置を扱うクラス。
 * 
 * @author tomoyamkung
 *
 */
class TrimmingPoint {
	
	/**
	 * トリミングの開始位置。
	 */
	private int begin;
	
	/**
	 * トリミングの終了位置。
	 */
	private int end;
	
	/**
	 * トリミングの開始位置と終了位置を次に進める。
	 * 
	 * @param nextEnd 次の終了位置
	 * @param last 終端に達した場合に「終了位置」に設定する位置
	 */
	public void shift(int nextEnd, int last) {
		begin = end;
		end = nextEnd;
		
		if(begin == end) {
			end = last;
		} else if(end < begin) {
			end = last;
		}
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}
	
}