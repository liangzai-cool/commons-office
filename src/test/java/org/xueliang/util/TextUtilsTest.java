package org.xueliang.util;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

public class TextUtilsTest {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void toMapFromUrlTest() throws Exception {
		Map map = TextUtils.toMapFromUrl("http://o6ztar7bn.bkt.clouddn.com/02_shop.txt");
		System.out.println(new JSONObject(map));
	}
}
