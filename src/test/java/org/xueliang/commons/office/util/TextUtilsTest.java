package org.xueliang.commons.office.util;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.xueliang.commons.office.util.TextUtils;

public class TextUtilsTest {
	
	@SuppressWarnings({ "rawtypes"})
	@Test
	public void toMapFromUrlTest() throws Exception {
		Map map = TextUtils.toMapFromUrl("http://o6ztar7bn.bkt.clouddn.com/02_shop.txt");
		System.out.println(new JSONObject(map));
	}
}
