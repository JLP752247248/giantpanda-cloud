package com.couldtec.webserver.apps.restful.h2entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 业务用户信息
 * @author jlp
 *
 */
@Entity
@Table(name="ITMS_BSSUSER_INFO")
public class BssUserInfo extends BaseObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	/*客户名称*/ 
	private String userName;
	/*宽带账号 */
	private String account;
	/*loid*/
	private String loid;
	/*云网关序列号*/ 
	private String seriesNumber;
	/*客户级别 */
	private int userLevel=1;
	/*带宽 单位：M*/
	private int tapeWidth=20;
	/*客户地址*/
	private String address;
	private String userType="其他";
	private String contact;
	private String contactPhone;
	public BssUserInfo(){
		
	}
	public BssUserInfo(String account){
		this.userId=account;
		this.account=account;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/*描述*/
	private String detail;
	/*@OneToMany(mappedBy="userId",fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Complaint> complaints;*/
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSeriesNumber() {
		return seriesNumber;
	}
	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public int getTapeWidth() {
		return tapeWidth;
	}
	public void setTapeWidth(int tapeWidth) {
		this.tapeWidth = tapeWidth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoid() {
		return loid;
	}
	public void setLoid(String loid) {
		this.loid = loid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
