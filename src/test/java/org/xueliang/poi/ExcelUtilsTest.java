package org.xueliang.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.json.JSONArray;
import org.junit.Test;
import org.xueliang.util.ExcelUtils;

public class ExcelUtilsTest {

	@Test
	public void test() throws Exception {
//		fail("Not yet implemented");
		String path = "C:\\Users\\XueLiang\\Desktop\\test.xls";
//		String path2 = "C:\\Users\\XueLiang\\Desktop\\test-97-2003.xls";
		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		ExcelUtils utils = new ExcelUtils();
		JSONArray jsonSheet = utils.excelToJSONArray(inputStream);
		System.out.println(jsonSheet);
	}

}
