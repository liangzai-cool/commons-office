package org.xueliang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import vms.common.web.HttpUtils;

public class TextUtils {
	
	/**
	 * 从网上读取文件
	 * @param url 地址
	 * @param charsetName 编码格式
	 * @return map
	 * @throws Exception 
	 */
	public static Map<String, String> toMapFromUrl(String url) throws Exception {
	    HttpUtils httpUtils = new HttpUtils();
	    String content = httpUtils.getUrlContent(url);
	    return toMap(content);
	}
	
	/**
	 * 将一个BufferedReader对象转成map，并对key和value进行trim
	 * @param bufferedReader
	 * @return map 目标BufferedReader对象
	 * @throws IOException
	 */
	public static Map<String, String> toMap(BufferedReader bufferedReader) throws IOException {
		return toMap(bufferedReader, true, true);
	}
	
	/**
	 * 将形如以下内容的字符串转成map
	 * <pre>
	 * a=b
	 * c=d
	 * </pre>
	 * @param content 字符串
	 * @return map
	 * @throws IOException
	 */
	public static Map<String, String> toMap(String content) throws IOException {
		Reader stringReader = new StringReader(content);
		BufferedReader bufferedReader = new BufferedReader(stringReader);
		return toMap(bufferedReader);
	}
	
	/**
	 * 将一个BufferedReader对象转成map
	 * @param bufferedReader 目标BufferedReader对象
	 * @param needTrimKey 是否对key进行trim
	 * @param needTrimValue 是否对value进行trim
	 * @return map
	 * @throws IOException
	 */
	public static Map<String, String> toMap(BufferedReader bufferedReader, boolean needTrimKey, boolean needTrimValue) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			int equalIndex = line.indexOf("=");
			if (equalIndex >= 0) {
				String key = line.substring(0, equalIndex);
				String value = line.substring(equalIndex + 1);
				if (needTrimKey) {
					key = key.trim();
				}
				if (needTrimValue) {
					value = value.trim();
				}
				map.put(key, value);
			}
		}
		return map;
	}
	
	/**
	 * 从输入流中读取内容，转成map对象
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> toMap(InputStream inputStream) throws IOException {
		Reader inputStreamReader = new InputStreamReader(inputStream);
		return toMap(new BufferedReader(inputStreamReader));
	}
}
