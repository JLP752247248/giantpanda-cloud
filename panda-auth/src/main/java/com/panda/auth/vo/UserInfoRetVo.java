package com.panda.auth.vo;

import lombok.Data;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-11  13:21
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserInfoRetVo {

    private String username;

    private Long phone;

    private boolean logined;

    private String headPicUrl;

    private AddrVo addr;


}
