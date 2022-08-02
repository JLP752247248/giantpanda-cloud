package com.couldtec.webserver.util;


import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.json.XML;

/**
 * json xml互转工具类
 * @author jlp
 *
 */
public class XmlJsonUtil {
	private static XMLSerializer xmlserializer = new XMLSerializer();
	
	static {
		xmlserializer.setTypeHintsEnabled(false);
		xmlserializer.setRootName("root");
		xmlserializer.setSkipNamespaces(true);
	}
    /**
     * xml格式字符串转化成jsonObject或者jsonArray
     * @param xml
     * @return
     */
    public static String xml2json1(String xml){
    	String rs = "";
    	try {
    		rs = xmlserializer.read(xml).toString();
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("xml转化为json异常...");
		}
        return rs;
    }
    
    
    /**
     * 这个和xml2json1的区别是，对象为空时是""，而前者是[]
     * @param xml
     * @return
     */
    public static String xml2json(String xml){
    	org.json.JSONObject jsonObj = XML.toJSONObject(xml);  
        jsonObj = jsonObj.getJSONObject("root");//org.json需去除根标签  
        return jsonObj.toString();
    }
   
    /**
     * jsonArray或者jsonObject字符串转化为xml
     * @param json
     * @return
     */
    public static String json2xml(String json){
    	String rs = "";
    	xmlserializer.setSkipNamespaces(true);
    	xmlserializer.setTypeHintsEnabled(false);
    	try {
    		if(json.contains("[")&&json.contains("]")){
    			//jsonArray
    			JSONArray jobj = JSONArray.fromObject(json);
        		rs =  xmlserializer.write(jobj);
    		}else{
    			//jsonObject
    			JSONObject jobj = JSONObject.fromObject(json);
        		rs =  xmlserializer.write(jobj);
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("jsonArray转化为xml异常...");
		}
        return rs;
    }
    /** 
     * 将对象直接转换成String类型的 XML输出 
     *  
     * @param obj 
     * @return 
     */  
    public static String convertToXml(Object obj) {  
    	String json=com.alibaba.fastjson.JSONObject.toJSONString(obj);
    	String xml=json2xml(json);
        return xml;
    } 
    
    /** 
     * 将对象直接转换成String类型的 XML输出 
     *  
     * @param obj 
     * @return 
     */  
    public static String convertToXml1(Object obj) {  
        // 创建输出流  
        StringWriter sw = new StringWriter();  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  

            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  
                    Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return sw.toString();  
    }  
    
    /**
     * Xml转成对象
     * @param clazz
     * @param xmlStr
     * @return
     */
    public static Object xmlStr2Obj(Class clazz, String xmlStr) {  
        Object xmlObject = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);  
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);  
            xmlObject = unmarshaller.unmarshal(sr);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return xmlObject;  
    } 
    
}
