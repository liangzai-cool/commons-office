package org.xueliang.commons.office.util;

import org.junit.Test;
import org.xueliang.commons.office.util.ExcelReadUtils;

public class ExcelUtilsTest {

	@Test
	public void test() throws Exception {
		String path = "C:\\Users\\XueLiang\\OneDrive\\文档\\cekasp\\newspapers-more.xlsx";
		System.out.println(ExcelReadUtils.toJSONArray(path));
	}

}
