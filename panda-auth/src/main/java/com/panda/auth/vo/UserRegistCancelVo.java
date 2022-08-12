package com.panda.auth.vo;

import com.panda.auth.user.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  13:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserRegistCancelVo {

    @NotNull
    private Long id;

    @NotNull
    private Long phone;

    @NotNull
    private String password;

    @NotNull
    private String smsCode;


    public UserInfo buildUserInfo(){
        UserInfo userInfo =new UserInfo();
        userInfo.setId(id);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        return userInfo;

    }
}
