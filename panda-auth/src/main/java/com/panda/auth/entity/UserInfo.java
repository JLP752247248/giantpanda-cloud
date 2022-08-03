package com.panda.auth.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 业务用户信息
 * @author jlp
 *
 */
@Entity
@Table(name="SYS_USER_INFO")
@Data
public class UserInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid")
	private String userId;
	/*客户名称*/ 
	@Column(unique = true)
	private String userName;
	/*客户级别 */
	private int userLevel=1;
	/*客户地址*/
	private String address;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String mobile;
	private String passwd;

	
}
