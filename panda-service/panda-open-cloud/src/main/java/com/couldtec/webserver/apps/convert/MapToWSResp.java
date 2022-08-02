package com.couldtec.webserver.apps.convert;

import java.util.Map;

import com.couldtec.webserver.apps.entity.WSRespObj;
import com.couldtec.webserver.util.StringUtil;

/**
 * 接口回参转换，restful回参-》webService回参
 */
public class MapToWSResp {
	/**
	 * 2.13 业务下发回调接口 restful回参-》webService回参
	 * @param param
	 * @return
	 */
	public  static  WSRespObj ServiceDoneReturn(Map<String,Object> param){
		WSRespObj resp=new WSRespObj();
		resp.setCmdID(StringUtil.getStringValue(param.get("requ_id")));
		resp.setRstCode(StringUtil.getStringValue(param.get("code")));
		resp.setRstMsg(StringUtil.getStringValue(param.get("reason")));
		return resp;
	}
}
