package com.jf.myspringmvc;

import java.util.HashMap;
import java.util.Map;

public class RequestURIMap {
	private static Map<String, Class<?>> urlMap = new HashMap<>();
	
	/**
	 * 判断URL是否存在
	 */
	public static boolean isExist(String url) {
		return urlMap.containsKey(url);
	}
	

	public static void put(String path, Class<?> className) {
		urlMap.put(path, className);
	}

	public static Map<String, Class<?>> getUrlMap() {
		return urlMap;
	}

	public static void setUrlMap(Map<String, Class<?>> urlMap) {
		RequestURIMap.urlMap = urlMap;
	}
}
