package org.xueliang.commons.office.util;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XMLUtils {

	/** 用来记录日志 **/
	private final static Log log = LogFactory.getLog(XMLUtils.class);
	
	public static JSONObject toJSONObject(String handlerPath, String pathname) throws DocumentException {
		final JSONObject json = new JSONObject();
		SAXReader reader = new SAXReader();
		try {
			reader.addHandler(handlerPath,
				    new ElementHandler() {
						JSONObject jsonElement = null;
			        	JSONArray jsonAttributes = null;
			        	JSONObject jsonAttrs = null;
						
				        public void onStart(ElementPath path) {
				        	//
				        	jsonElement = new JSONObject();
				        	jsonAttributes = new JSONArray();
				        	jsonAttrs = new JSONObject();
				        	
				        	Element elem = path.getCurrent();	//当前节点
				        	//解析属性
				        	Iterator<?> iterator = elem.attributeIterator();
				        	while (iterator.hasNext()) {
				        		DefaultAttribute attr = (DefaultAttribute) iterator.next();
				        		JSONObject jsonAttr = new JSONObject();
				        		try {
									jsonAttr.put(attr.getName(), attr.getValue());
					        		jsonAttrs.put(attr.getName(), attr.getValue());
								} catch (JSONException e) {
									log.error("addHandler error", e);
								}
				        		jsonAttributes.put(jsonAttr);
							}
				        }
				        public void onEnd(ElementPath path) {
				            // process a ROW element
				        	Element elem = path.getCurrent();	//当前节点
				        	String name = elem.getName();		//节点名称
				        	
				        	//将属性绑定到节点对象上
//				        	jsonElement.put("attributes", jsonAttributes);
				        	try {
					        	jsonElement.put("attrs", jsonAttrs);
					        	jsonElement.put("element", elem);
							} catch (JSONException e) {
								log.error("addHandler error", e);
							}
				        	
				        	Object jsonValue = json.opt(name);
				        	if (jsonValue == null) {
								try {
									json.put(name, jsonElement);
								} catch (JSONException e) {
									log.error("addHandler error", e);
								}
							} else {
								if (jsonValue instanceof JSONArray) {
									JSONArray arrayValue = (JSONArray) jsonValue;
									arrayValue.put(jsonElement);
								} else {
									JSONArray array = new JSONArray();
									array.put(jsonValue);
									array.put(jsonElement);
									try {
										json.put(name, array);
									} catch (JSONException e) {
										log.error("addHandler error", e);
									}
								}
							}
				        	jsonElement = null;
				        	jsonAttributes = null;
				        	jsonAttrs = null;
				        	
				        	readChildren(elem);
				        }  
				    }  
				);
		} catch (Exception e) {
			
		}
		reader.read(pathname);
		return json;
	}
	
	@SuppressWarnings("rawtypes")
	public static JSONObject readChildren(Element element) {
		JSONObject json = new JSONObject();
		List children = element.elements();
		for (int i = 0, len = children.size(); i < len; i++) {
			DefaultElement elem = (DefaultElement) children.get(i);
			System.out.println(elem);
		}
		return json;
	}
}
