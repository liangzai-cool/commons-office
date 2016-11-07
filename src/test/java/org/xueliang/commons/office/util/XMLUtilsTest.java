package org.xueliang.commons.office.util;

import static org.junit.Assert.assertThat;

import org.dom4j.DocumentException;
import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xueliang.commons.office.util.XMLUtils;

public class XMLUtilsTest {
	
	static JSONObject json = new JSONObject();

	@BeforeClass
	public static void setUpBeforeClass() throws DocumentException {
		String pathname = "C:\\Users\\XueLiang\\Desktop\\csdn_blog_rss.xml";
		json = XMLUtils.toJSONObject("/rss/channel/item", pathname);
	}
	
	@Test
	public void test() throws DocumentException, JSONException {
		assertThat(json, CoreMatchers.notNullValue());
		assertThat(json.length(), CoreMatchers.not(CoreMatchers.equalTo(0)));
		System.out.println(json.optJSONArray("item").getJSONObject(0).optJSONObject("attrs").optString("index"));
		
	}
}
