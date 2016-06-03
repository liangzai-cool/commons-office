package org.xueliang.util;

import java.io.File;

import org.junit.Test;
import org.xueliang.util.ExcelUtils;

public class ExcelUtilsTest {

	@Test
	public void test() throws Exception {
		String path = "C:\\Users\\XueLiang\\Desktop\\test.xlsx";
		File file = new File(path);
		System.out.println(ExcelUtils.toJSONArray(file));
	}

}
