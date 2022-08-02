package com.couldtec.webserver.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 *  json对象转为map
 * @author jlp
 *
 */
public class JSONMapUtil {
	
	/**
	 * 对象转map,递归
	 * @param jsonObject
	 * @return
	 */
	public static Map<String,String> jsonObject2Map(JSONObject jsonObject){
		Map <String,String>map=new HashMap<String,String>();
		Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
        for (Entry<String, Object> entry : entrySet) {
        	if(entry.getValue() instanceof JSONObject){
        		map.putAll(jsonObject2Map((JSONObject) entry.getValue()));
        	}else{
        		map.put(entry.getKey(), StringUtil.getStringValue(entry.getValue()));
        	}
           
        }
        return map;
	}
	
	/**
	 * 对象转map,不递归
	 * @param jsonObject
	 * @return
	 */
	public static Map<String,Object> jsonObject2ObjectMap(JSONObject jsonObject){
		Map <String,Object>map=new HashMap<String,Object>();
		Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
        for (Entry<String, Object> entry : entrySet) {
        	if(entry.getValue() instanceof JSONObject){
        		map.put(entry.getKey(),jsonObject2Map((JSONObject) entry.getValue()));
        	}else{
        		map.put(entry.getKey(), StringUtil.getStringValue(entry.getValue()));
        	}
           
        }
        return map;
	}
}
