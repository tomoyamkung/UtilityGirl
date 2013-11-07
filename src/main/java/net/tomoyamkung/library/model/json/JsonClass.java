package net.tomoyamkung.library.model.json;

import java.util.ArrayList;
import java.util.List;

import net.tomoyamkung.library.model.json.attribute.JsonAttributeClass;
import net.tomoyamkung.library.model.json.parser.Iterator;
import net.tomoyamkung.library.model.json.parser.JsonAttributeClassIterator;
import net.tomoyamkung.library.util.StringUtil;

/**
 * JSON に定義されているオブジェクトを保持するクラス。
 * 
 * JSON をデシリアライズする前に、属性の妥当性を確認する用途で作成したクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonClass {

	/**
	 * 抽出したオブジェクトを保持するリスト。
	 */
	private List<JsonAttributeClass> classes;

	/**
	 * 解析する JSON を受け取り、個々のオブジェクトに展開する。
	 * 
	 * @param json
	 *            解析する JSON
	 */
	public JsonClass(String json) {
		if (StringUtil.isNullOrEmpty(json)) {
			return;
		}

		classes = new ArrayList<JsonAttributeClass>();
		parse(json);
	}
	
	/**
	 * JSON を解析してオブジェクトを抽出する。
	 * 
	 * @param json
	 *            解析する JSON
	 */
	private void parse(String json) {
		Iterator<String> spliter = new JsonAttributeClassIterator(json);
		while (spliter.hasNext()) {
			String jsonObjectString = spliter.next();
			addClass(new JsonAttributeClass(jsonObjectString));
		}
	}

	/**
	 * <code>JsonAttributeClass</code> を保持するオブジェクトに追加する。
	 * 
	 * @param jsonAttributeClass
	 */
	private void addClass(JsonAttributeClass jsonAttributeClass) {
		classes.add(jsonAttributeClass);
	}
	
	/**
	 * 抽出したオブジェクトを取得する。
	 * 
	 * @param index
	 * @return
	 */
	public JsonAttributeClass get(int index) {
		return classes.get(index);
	}

}
