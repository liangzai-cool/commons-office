package org.xueliang.util;

import java.io.IOException;

import org.junit.Test;
import org.xueliang.util.CSVUtils;

public class CSVUtilsTest {

	@Test
	public void test() throws IOException {
//		fail("Not yet implemented");
		String pathname = "C:\\Users\\XueLiang\\Desktop\\1234.csv";
		System.out.println(CSVUtils.toJSONArray(pathname));
	}

}
