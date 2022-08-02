package com.couldtec.webserver.apps.init;

public class WsUrlObject
{
	private String provCode;
	private String provName;
	private String eservUrl;
	private String itmsServiceUrl;
	private String eservTns;
	private String itmsServiceTns;
	private String targetNamespaceParam;
	public String getEservUrl()
	{
		return eservUrl;
	}
	
	public void setEservUrl(String eservUrl)
	{
		this.eservUrl = eservUrl;
	}
	
	public String getItmsServiceUrl()
	{
		return itmsServiceUrl;
	}
	
	public void setItmsServiceUrl(String itmsServiceUrl)
	{
		this.itmsServiceUrl = itmsServiceUrl;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getEservTns() {
		return eservTns;
	}

	public void setEservTns(String eservTns) {
		this.eservTns = eservTns;
	}

	public String getItmsServiceTns() {
		return itmsServiceTns;
	}

	public void setItmsServiceTns(String itmsServiceTns) {
		this.itmsServiceTns = itmsServiceTns;
	}

	public String getTargetNamespaceParam() {
		return targetNamespaceParam;
	}

	public void setTargetNamespaceParam(String targetNamespaceParam) {
		this.targetNamespaceParam = targetNamespaceParam;
	}
}
