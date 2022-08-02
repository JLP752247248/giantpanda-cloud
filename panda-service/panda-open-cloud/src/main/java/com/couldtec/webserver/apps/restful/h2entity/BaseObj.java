package com.couldtec.webserver.apps.restful.h2entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.couldtec.webserver.apps.init.Global;
import com.alibaba.fastjson.annotation.JSONField;
@MappedSuperclass  
public class BaseObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String requestId;
	/** 省份id**/
	private int provCode;
	private int cityCode;
	@Transient
	private String provName;
	@Transient
	private String cityName;
	public String getProvName() {
		if(null!=Global.provMap && null!=Global.provMap.get(provCode+""))
			return Global.provMap.get(provCode+"");
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getCityName() {
		if(null!=Global.cityMap && null!=Global.cityMap.get(cityCode+""))
			return Global.cityMap.get(cityCode+"");
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date time;
	BaseObj(){
		this.time=new Date();
	}
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	public int getProvCode() {
		return provCode;
	}
	public void setProvCode(int provCode) {
		this.provCode = provCode;
	}
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
