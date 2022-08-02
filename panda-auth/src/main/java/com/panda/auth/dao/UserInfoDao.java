package com.panda.auth.dao;

import com.panda.auth.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public abstract interface UserInfoDao extends JpaRepository<UserInfo, String>
{
	UserInfo findByUserName(String userName);
	UserInfo findByUserNameOrEmailOrMobile(String userName,String email,String mobile);
}