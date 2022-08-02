package com.couldtec.webserver.apps.entity.responsemodels;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class BaseResponseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required=true,notes="唯一标识请求的ID值，全局唯一")
	private String requ_id;
	@ApiModelProperty(required=true,notes="接口调用结果返回码")
	private String code;
	@ApiModelProperty(required=false,notes="结果描述")
	private String reason;
	BaseResponseModel(){
		
	}
	public BaseResponseModel(String code, String res, String req_id) {
		this.reason=res;
		this.code=code;
		this.requ_id=req_id;
	}
	public String getRequ_id() {
		return requ_id;
	}
	public void setRequ_id(String requ_id) {
		this.requ_id = requ_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Map<String, Object> toMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("requ_id", this.requ_id);
		map.put("reason", this.reason);
		map.put("code", this.code);
		return map;
		
	}
}
