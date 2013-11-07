package net.tomoyamkung.library.model.json;

import net.tomoyamkung.library.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * JSON を解析して、オブジェクト単位に分解するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class JsonAttributeClassSpliter {

	/**
	 * 解析する JSON。
	 */
	private String json;
	
	/**
	 * 属性の分解を開始するインデックス。
	 */
	private int index = 0;
	
	/**
	 * JSON に定義されているオブジェクトの個数。
	 */
	private int numOfObject;
	
	private int trimBegin = 0;
	private int trimEnd = 0;
	
//	private Trimming trim;

	/**
	 * 属性単位に分解する JSON を設定する。
	 * 
	 * @param json 解析する JSON
	 */
	public JsonAttributeClassSpliter(String json) {
		this.json = json.trim();
		if(isList(this.json)) {
			this.json = StringUtil.removeFirstAndLastCharacter(this.json);
			this.json = removeLabel(this.json);
			this.json = removeListBracket(this.json);
		}
		
		numOfObject = countObject(this.json);
//		trim = new Trimming(this.json, "},{");
	}

	/**
	 * JSON がオブジェクトのリストを定義したものである場合、"[" と "]" を削除する。
	 * 
	 * @param json
	 * @return
	 */
	private String removeListBracket(String json) {
		return StringUtil.removeStrings(json, "[", "]");
	}

	/**
	 * JSON がオブジェクトのリストを定義したものである場合、そのラベルを削除する。
	 * 
	 * @param json
	 * @return
	 */
	private String removeLabel(String json) {
		return json.substring(json.indexOf(":") + 1);
	}

	/**
	 * JSON がオブジェクトのリストを定義したものであるかを問い合わせる。
	 * 
	 * @param json 調査する JSON
	 * @return オブジェクトのリストを定義したものである場合 true
	 */
	private boolean isList(String json) {
		return json.contains(":[{") && json.contains("}]");
	}

	/**
	 * JSON に定義されているオブジェクトの個数をカウントする。
	 * 
	 * @param json 調査する JSON
	 * @return
	 */
	private int countObject(String json) {
		return StringUtils.countMatches(json, "},{") + 1;
	}

	/**
	 * 分解可能な属性があるかを問い合わせる。
	 * 
	 * @return 分解可能な属性がある場合 true
	 */
	public boolean hasNext() {
		return index < numOfObject;
	}

	/**
	 * 分解したオブジェクトを取得する。
	 * 
	 * @return
	 */
	public String next() {
		calculateNextTrimPoint();
		String attributeEntryString = json.substring(trimBegin, trimEnd);
		if(attributeEntryString.startsWith(",")) {
			attributeEntryString = StringUtil.removeFirstCharacter(attributeEntryString);
		}
		index++;
		return attributeEntryString;
	}

	/**
	 * 次のインデックスを計算する。
	 * 
	 * @return
	 */
	private void calculateNextTrimPoint() {
		trimBegin = trimEnd;
		trimEnd = json.indexOf("},{", trimBegin) + 1;
		if(trimBegin == trimEnd) {
			trimEnd = json.length();
		} else if(trimEnd < trimBegin) {
			trimEnd = json.length();
		}
	}
}
