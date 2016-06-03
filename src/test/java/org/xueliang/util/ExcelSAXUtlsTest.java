package org.xueliang.util;

import java.io.File;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.junit.Test;
import org.xueliang.util.ExcelSAXUtls;

public class ExcelSAXUtlsTest {

	@Test
	public void test() throws Exception {
//		fail("Not yet implemented");
		// String path1 = "C:\\Users\\XueLiang\\Desktop\\discipline2.xlsx";
		// String path2 = "C:\\Users\\XueLiang\\Desktop\\abc.xlsx";
		// String path3 = "C:\\Users\\XueLiang\\Desktop\\68C32930.xlsx";
		String path4 = "C:\\Users\\XueLiang\\Desktop\\论文基础数据-PART III.xlsx";
		File file = new File(path4);
		OPCPackage opcPackage = OPCPackage.open(file.getPath(), PackageAccess.READ);
		ExcelSAXUtls howto = new ExcelSAXUtls(opcPackage);
		howto.processAllSheets();
	}

}
