package org.xueliang.commons.office.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.json.JSONArray;

import com.csvreader.CsvReader;

public class CSVUtils {

	/**
	 * csv转jsonarray
	 * @param pathname csv文件的路径
	 * @param skipHeader 是否跳过表头
	 * @param charset 编码格式
	 * @return
	 * @throws IOException
	 */
	public static JSONArray toJSONArray(String pathname, boolean skipHeader, String charset) throws IOException {
		JSONArray array = new JSONArray();
		CsvReader csvReader = new CsvReader(pathname, ',', Charset.forName(charset));
		// 读取表头
		if (skipHeader) {
			csvReader.readHeaders();
		}
		while (csvReader.readRecord()) {
        	String[] row = csvReader.getValues();
        	JSONArray jsonRow = new JSONArray();
        	for (int j = 0, k = row.length; j < k; j++) {
        		jsonRow.put(row[j]);
        	}
        	array.put(jsonRow);
		}
		csvReader.close();
		return array;
	}
	
	public static JSONArray toJSONArray(String pathname, boolean skipHeader) throws IOException {
		return toJSONArray(pathname, skipHeader, "utf8");
	}
	
	public static JSONArray toJSONArray(String pathname) throws IOException {
		return toJSONArray(pathname, true, "utf8");
	}
}
