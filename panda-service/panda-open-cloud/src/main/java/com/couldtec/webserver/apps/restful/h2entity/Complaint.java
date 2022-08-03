package com.couldtec.webserver.apps.restful.h2entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ITMS_COMPLAINT_LOG")
public class Complaint extends BaseObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid")
	private  String id;
	private String complaintMsg;
	private String userId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComplaintMsg() {
		return complaintMsg;
	}
	public void setComplaintMsg(String complaintMsg) {
		this.complaintMsg = complaintMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
