package org.xueliang.poi;

import java.io.File;

import org.json.JSONArray;
import org.junit.Test;
import org.xueliang.util.ExcelUtils;

public class ExcelUtilsTest {

	@Test
	public void test() throws Exception {
		String path = "C:\\test.xls";
		File file = new File(path);
		ExcelUtils utils = new ExcelUtils();
		JSONArray jsonSheet = utils.excelToJSONArray(file);
		System.out.println(jsonSheet);
	}

}
