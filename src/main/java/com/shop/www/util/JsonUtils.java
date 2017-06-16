package com.shop.www.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	public static <T> String toJSON(T t) {
		if (null != t) {
			return JSON.toJSONString(t);
		}
		return null;
	}

	/**
	 * mapתjson
	 * 
	 * @author��sw
	 * @param map
	 * @return
	 */
	public static <K, V> String map2Json(Map<K, V> map) {
		if (!CollectionUtil.isEmpty(map)) {
			return JSON.toJSONString(map);
		}
		return null;
	}

	/**
	 * listתjson
	 * 
	 * @author��sw
	 * @param list
	 * @return
	 */
	public static <T> String list2JSON(List<T> list) {
		if (!CollectionUtil.isEmpty(list)) {
			return JSON.toJSONString(list);
		}
		return null;
	}

	/**
	 * JSONתmap
	 * 
	 * @param <K>
	 * @author��sw
	 * @param json
	 * @return
	 */
	public static <K, V> Map<K, V> json2Map(final String json) {
		if (StringUtils.isNotBlank(json)) {
			return JSON.parseObject(json, HashMap.class);
		}
		return null;
	}

	/**
	 * JSONתlist
	 * 
	 * @author��sw
	 * @param json
	 * @return
	 */
	public static <T> List<T> json2List(final String json) {
		if (StringUtils.isNotBlank(json)) {
			return JSON.parseObject(json, List.class);
		}
		return null;
	}

	/**
	 * jsonת����
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Object(final String json, Class<T> clazz) {
		if (StringUtils.isNotBlank(json) && null != clazz) {
			return JSON.parseObject(json, clazz);
		}
		return null;
	}

}
