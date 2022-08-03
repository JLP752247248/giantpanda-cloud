package com.couldtec.webserver.apps.restful.h2entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CLO_MSGINFO")
public class MsgInfo extends BaseObj
{

  @Id
  @GeneratedValue(generator="uuid")
  @GenericGenerator(name="uuid", strategy="uuid")
  private String id;
  private String reqId;
  private String msgContent;

  public MsgInfo(String req_id, String msg)
  {
    this.reqId = req_id;
    this.msgContent = msg;
  }

  public String getId()
  {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getMsgContent() {
    return this.msgContent;
  }
  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent;
  }
  public String getReqId() {
    return this.reqId;
  }
  public void setReqId(String reqId) {
    this.reqId = reqId;
  }
}