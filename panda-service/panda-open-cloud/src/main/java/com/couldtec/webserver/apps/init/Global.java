package com.couldtec.webserver.apps.init;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.couldtec.webserver.util.ThreadPoolCommon;

/**
 * 全局变量
 * @author Administrator (Ailk NO.)
 * @version 1.0
 * @since 2017-2-15
 */
public class Global {
	public final static String XMLHEAD_GBK="<?xml version=\"1.0\" encoding=\"GBK\"?>";
	public final static String XMLHEAD_UTF8="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	/** config.xml路径*/
	public static String G_ConfPath = null;
	
	public static ApplicationContext CONTEXT = null;
	
	public static ThreadPoolCommon THREAD_POOL_COMMON = null;
	
	/**属地编码、webservice url object*/
	public static Map<String,WsUrlObject> G_CityId_Url_Map = new HashMap<String,WsUrlObject>();
	
	public static Map<String,String> provMap=null;
	
	public static Map<String,String> cityMap=null;
	
	public static String restUrl = "";
	
	/**
	 * 是否是测试数据：如果是测试，则不调用实际省份接口，直接返回写死的数据
	 */
	public static boolean isTest=false;
	/**
	 * 回调地址是否使用数据库保存，否的话就放在内存中，但是多实例有问题
	 */
	public static boolean useDb=false;
	/**
	 * 回调地址
	 */
	public static String aimUrl;
	/**
	 * 调用省份接口超时断开时间，1000为1秒
	 */
	public static int WSTimeOut=60000;
	
	/**
	 * 去掉xml消息的信息头
	 * @param xmlStr
	 * @return
	 */
	public static String xmlRemoveHead(String xmlStr){
		return xmlStr.trim().replace(Global.XMLHEAD_GBK, "").replace(Global.XMLHEAD_UTF8, "");
	}
}
