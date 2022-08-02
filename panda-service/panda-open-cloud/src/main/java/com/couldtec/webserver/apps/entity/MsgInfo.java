package com.couldtec.webserver.apps.entity;

public class MsgInfo {
	
	private String reqId;
	private String msg;
	private Object data;
	private RespCode respCode;
	public MsgInfo(String reqId, String msg, Object data, RespCode respCode) {
		super();
		this.reqId = reqId;
		this.msg = msg;
		this.data = data;
		this.respCode = respCode;
	}
	
	public enum RespCode {
	    SUCCESS(1000),
	    FAILED(1001);
	    private int value;

		private RespCode(int num) {
			this.value = num;
		}

		public int toValue() {
			return value;
		}
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRespCode() {
		return respCode.toValue();
	}

	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}
	
	
	
}
