package net.tomoyamkung.library.model.json;

/**
 * JSON を属性単位に分解するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class JsonAttributeSpliter {

	/**
	 * 分解する JSON。
	 */
	private String json;
	
	/**
	 * 属性の分解を開始するインデックス。
	 */
	private int index = 0;

	/**
	 * 属性単位に分解する JSON を設定する。
	 * 
	 * @param json
	 */
	public JsonAttributeSpliter(String json) {
		this.json = json;
	}

	/**
	 * 分解可能な属性があるかを問い合わせる。
	 * 
	 * @return 分解可能な属性がある場合 true
	 */
	public boolean hasNext() {
		return -1 < getNextIndex();
	}

	/**
	 * 分解した属性を取得する。
	 * 
	 * @return
	 */
	public JsonItem next() {
		int nextIndex = getNextIndex();
		String itemString = json.substring(index, nextIndex);
		JsonItem jsonItem = new JsonItem(itemString);

		index = nextIndex + 1;
		return jsonItem;
	}

	/**
	 * 次のインデックスを取得する。
	 * 
	 * @return
	 */
	private int getNextIndex() {
		int candicate = json.indexOf(",", index);
		if(candicate < 0) {
			return candicate;
		}
		
		String nextItemString = json.substring(index, candicate);
		if(isListItem(nextItemString)) {
			return json.indexOf("],", index) + 1;
		}
		return candicate;
	}

	/**
	 * この属性値がリストであるかを問い合わせる。
	 * 
	 * @param itemString
	 * @return リストであれば true
	 */
	private boolean isListItem(String itemString) {
		return itemString.contains("[");
	}

}
