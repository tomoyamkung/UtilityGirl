package net.tomoyamkung.library.util;

import java.util.UUID;

/**
 * UUID 生成のユーティリティクラス。
 * 
 * @author tomoyamkung
 * 
 */
public class UUIDUtil {

	/**
	 * タイプ４（擬似ランダム生成）の UUID を生成する。
	 * 
	 * @return uuid
	 */
	public static String createType4() {
		return UUID.randomUUID().toString();
	}
}
