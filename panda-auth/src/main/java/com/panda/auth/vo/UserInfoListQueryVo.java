package com.panda.auth.vo;

import com.panda.auth.user.entity.UserInfo;
import com.panda.common.mvc.PageVo;
import lombok.Data;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-11  13:21
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserInfoListQueryVo extends PageVo {

    private String username;

    private Long phone;

    private boolean logined;

    private AddrVo addr;

    public UserInfo toDto() {
        UserInfo userInfo =new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPhone(phone);
        return  userInfo;
    }
}
