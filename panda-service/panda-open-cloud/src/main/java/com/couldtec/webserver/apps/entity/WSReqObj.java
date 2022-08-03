package com.couldtec.webserver.apps.entity;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.couldtec.webserver.util.DateTimeUtil;
import com.couldtec.webserver.util.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;
@XmlRootElement
public class WSReqObj {
	//private String rollback;
	private String CmdID;
	private String CmdType;
	private String ClientType;
	private String DealDate;
	private String ServTypeId;
	private String OperateId;
	private Map<String,String> Param=new HashMap<String,String>();
	@JSONField(name="CmdID")
	public String getCmdID() {
		return CmdID;
	}
	public void setCmdID(String cmdID) {
		CmdID = cmdID;
	}
	@JSONField(name="CmdType")
	public String getCmdType() {
		return CmdType;
	}
	public void setCmdType(String cmdType) {
		CmdType = cmdType;
	}
	@JSONField(name="ClientType")
	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	@JSONField(name="DealDate")
	public String getDealDate() {
		return DealDate;
	}
	public void setDealDate(String dealDate) {
		if(StringUtil.IsEmpty(dealDate)){
			dealDate=new DateTimeUtil().getYYYYmmddhhmmss();
		}
		DealDate = dealDate;
	}
	@JSONField(name="ServTypeId")
	public String getServTypeId() {
		return ServTypeId;
	}
	public void setServTypeId(String servTypeId) {
		ServTypeId = servTypeId;
	}
	@JSONField(name="OperateId")
	public String getOperateId() {
		return OperateId;
	}
	public void setOperateId(String operateId) {
		OperateId = operateId;
	}
	@JSONField(name="Param")
	public Map<String, String> getParam() {
		return Param;
	}
	public void setParam(Map<String, String> param) {
		Param = param;
	}
	
	
	
	
	
	
}
