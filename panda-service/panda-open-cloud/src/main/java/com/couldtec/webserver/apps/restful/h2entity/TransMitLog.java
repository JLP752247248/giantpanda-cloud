package com.couldtec.webserver.apps.restful.h2entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 接口转发信息
 * @author jlp
 *
 */
@Entity
@Table(name="ADP_TRANSMIT_LOG")
public class TransMitLog extends BaseObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = -921520041465511587L;
	/** id*/
	@Id
	private long transId;
	private String reqId;
	/** 方法名*/
	private String methodName;
	/** 1:GET,2:POST,3:PUT,4:DELETE*/
	private int methodType;
	/** 入参记录*/
	@Column(length=100000)
	private String inPutStr;
	/** 回参记录*/
	@Column(length=100000)
	private String outPutStr;
	public TransMitLog() {
	}
	public TransMitLog(String methodName, String inStr, String outStr, int methodType) {
		this.methodName=methodName;
		this.inPutStr=inStr;
		this.outPutStr=outStr;
		this.methodType=methodType;
	}
	public long getTransId() {
		return transId;
	}
	public void setTransId(long l) {
		this.transId = l;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getMethodType() {
		return methodType;
	}
	public void setMethodType(int methodType) {
		this.methodType = methodType;
	}
	public String getInPutStr() {
		return inPutStr;
	}
	public void setInPutStr(String inPutStr) {
		this.inPutStr = inPutStr;
	}
	public String getOutPutStr() {
		return outPutStr;
	}
	public void setOutPutStr(String outPutStr) {
		this.outPutStr = outPutStr;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	
	
	
	
}
