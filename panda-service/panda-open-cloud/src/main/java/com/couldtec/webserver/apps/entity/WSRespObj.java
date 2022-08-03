package com.couldtec.webserver.apps.entity;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.couldtec.webserver.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
@XmlRootElement
public class WSRespObj {
	private String CmdID;
	private String RstCode;
	private String RstMsg;
	private String Loid;
	private String LoidPrev;
	private Object Param;
	private Map<String,Object> ParamMap;
	@JSONField(name="CmdID")
	public String getCmdID() {
		return CmdID;
	}
	public void setCmdID(String cmdID) {
		CmdID = cmdID;
	}
	@JSONField(name="RstCode")
	public String getRstCode() {
		return RstCode;
	}
	public void setRstCode(String rstCode) {
		if("0".equals(rstCode)){
			RstCode="200";
		}else{
			RstCode = rstCode;
		}
	}
	@JSONField(name="RstMsg")
	public String getRstMsg() {
		return RstMsg;
	}
	public void setRstMsg(String rstMsg) {
		RstMsg = rstMsg;
	}
	
	public Object getParam() {
		return Param;
	}
	
	@JSONField(name="Param")
	public Map<String,String> getParamMap(){
		Map<String,String> map=new HashMap<String,String>();
		if(null!=Param && !StringUtil.IsEmpty(Param.toString())){
			map=JSONObject.parseObject(Param.toString(), map.getClass());
		}
		for(String key:map.keySet()){
			map.put(key, StringUtil.getStringValue(map.get(key)));
		}
		return map;
	}
	public void setParam(String param) {
		Param = param;
	}
	@JSONField(name="Loid")
	public String getLoid() {
		return Loid;
	}
	public void setLoid(String loid) {
		Loid = loid;
	}
	@JSONField(name="LoidPrev")
	public String getLoidPrev() {
		return LoidPrev;
	}
	public void setLoidPrev(String loidPrev) {
		LoidPrev = loidPrev;
	}
	
	public String getParamValue(String key){
		if(null!=this.Param && !StringUtil.IsEmpty(Param.toString())){
			return StringUtil.getStringValue(this.getParamMap().get(key));
		}else{
			return "";
		}
	}
	
}
