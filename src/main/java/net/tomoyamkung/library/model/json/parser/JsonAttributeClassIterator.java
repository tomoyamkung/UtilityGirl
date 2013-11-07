package net.tomoyamkung.library.model.json.parser;

import net.tomoyamkung.library.model.string.Trimming;
import net.tomoyamkung.library.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * JSON を解析して、オブジェクト単位に分解するクラス。
 * 
 * @author tomoyamkung
 *
 */
public class JsonAttributeClassIterator implements Iterator<String>{
	
	/**
	 * 属性の分解を開始するインデックス。
	 */
	private int index = 0;
	
	/**
	 * JSON に定義されているオブジェクトの個数。
	 */
	private int numOfObject;
	
	/**
	 * JSON をトリミングするクラス。
	 */
	private Trimming trim;

	/**
	 * 属性単位に分解する JSON を設定する。
	 * 
	 * @param json 解析する JSON
	 */
	public JsonAttributeClassIterator(String json) {
		String jsonString = json.trim();
		if(isList(jsonString)) {
			jsonString = StringUtil.removeFirstAndLastCharacter(jsonString);
			jsonString = removeLabel(jsonString);
			jsonString = removeListBracket(jsonString);
		}
		
		numOfObject = countObject(jsonString);
		trim = new Trimming(jsonString, "},{");
	}

	/**
	 * JSON がオブジェクトのリストを定義したものである場合、"[" と "]" を削除する。
	 * 
	 * @param json
	 * @return
	 */
	private String removeListBracket(String json) {
		return StringUtil.removeStrings(json, "[", "]").trim();
	}

	/**
	 * JSON がオブジェクトのリストを定義したものである場合、そのラベルを削除する。
	 * 
	 * @param json
	 * @return
	 */
	private String removeLabel(String json) {
		return json.substring(json.indexOf(":") + 1).trim();
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
	@Override
	public boolean hasNext() {
		return index < numOfObject;
	}

	/**
	 * 分解したオブジェクトを取得する。
	 * 
	 * @return
	 */
	@Override
	public String next() {
		trim.shift();
		String attributeEntryString = trim.execute();
		if(attributeEntryString.startsWith(",")) {
			attributeEntryString = StringUtil.removeFirstCharacter(attributeEntryString);
		}
		index++;
		return attributeEntryString;
	}
}
