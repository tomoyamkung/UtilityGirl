package net.tomoyamkung.library.model.json.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tomoyamkung.library.model.json.parser.JsonAttributeParser;
import net.tomoyamkung.library.util.ListUtil;
import net.tomoyamkung.library.util.StringUtil;
import net.tomoyamkung.library.validate.Validator;

/**
 * JSON のオブジェクトに定義されている属性を保持するクラス。
 * 
 * <code>JsonAttribute</code> を保持するクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class JsonAttributeClass {

	/**
	 * <code>JsonAttribute</code> を保持する。
	 */
	private Map<String, JsonAttribute> attributes;

	/**
	 * オブジェクトが定義されている JSON を受け取る。
	 * 
	 * 引数に設定される JSON は "{"attr1":"value1","attr2":"value2","attr3":"value3"}"
	 * の形式とする。
	 * 
	 * @param json
	 */
	public JsonAttributeClass(String json) {
		attributes = new HashMap<String, JsonAttribute>();

		parse(json);
	}

	/**
	 * JSON を属性に分解する。
	 * 
	 * @param json
	 */
	private void parse(String json) {
		json = StringUtil.removeStrings(json, "{", "}", "\"").trim();

		for (String attribute : json.split(",")) {
			JsonAttribute jsonAttribute = new JsonAttributeParser(
					attribute.trim()).create();
			addAttribute(jsonAttribute);
		}
	}

	/**
	 * <code>JsonAttribute</code> を保持する属性に追加する。
	 * 
	 * @param jsonAttribute
	 */
	private void addAttribute(JsonAttribute jsonAttribute) {
		attributes.put(jsonAttribute.getName(), jsonAttribute);
	}

	/**
	 * <code>JsonAttribute</code> を取得する。
	 * 
	 * @param attributeName
	 *            属性名
	 * @return
	 */
	public JsonAttribute get(String attributeName) {
		return attributes.get(attributeName);
	}

	/**
	 * <code>JsonAttribute</code> に格納されている値を取得する。
	 * 
	 * 属性名に該当する <code>JsonAttribute</code> が格納されていない場合は
	 * <code>NullPointerException</code> を投げる。
	 * 
	 * @param attributeName
	 *            属性名
	 * @return
	 */
	private String getValue(String attributeName) {
		JsonAttribute jsonAttribute = get(attributeName);
		if (jsonAttribute == null) {
			throw new NullPointerException(String.format("", attributeName));
		}
		return jsonAttribute.getValue();
	}

	/**
	 * 属性の妥当性を確認する。
	 * 
	 * 該当する属性が格納されていない場合は <code>NullPointerException</code> を投げる。
	 * 
	 * @param attributeName
	 *            妥当性を確認したい属性名
	 * @param validators
	 *            <code>Validator</code> インタフェースを実装したクラス
	 * @param errorMessages
	 *            バリデーションエラーメッセージを格納したリスト
	 * @return 妥当性にパスしなかったバリデーションエラーメッセージのリスト。リストが空の場合は妥当性にパスしたことを表す
	 */
	public List<String> validate(String attributeName,
			List<Validator> validators, List<String> errorMessages) {
		List<String> actualErrorMessages = new ArrayList<String>();
		String value = getValue(attributeName);
		for (int i = 0; i < validators.size(); i++) {
			if (!validate(validators.get(i), value)) {
				ListUtil.copy(errorMessages, i, actualErrorMessages);
			}
		}

		return actualErrorMessages;
	}

	/**
	 * 妥当性を確認する。
	 * 
	 * @param validator
	 *            <code>Validator</code> インタフェースを実装したクラス
	 * @param value
	 *            検査する値
	 * @return 妥当性確認に成功した場合 true
	 */
	private boolean validate(Validator validator, String value) {
		return validator.execute(value);
	}

}
