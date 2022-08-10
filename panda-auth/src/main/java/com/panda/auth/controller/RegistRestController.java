package com.panda.auth.controller;


import com.panda.auth.user.entity.UserInfo;
import com.panda.auth.user.service.UserInfoService;
import com.panda.auth.vo.UserRegistVo;
import com.panda.common.mvc.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/regist")
@Slf4j
public class RegistRestController {

    @Autowired
    private UserInfoService userService;

    /**
     * 用户注册
     * 两种模式，一种是普通账号注册，填写用户名，手机号（需要短信验证）
     *           另一种模式是第三方账户接入，需要绑定手机号（需要短信验证）
     * @return the rest
     */
    @PostMapping("/regist")
    public Response<UserInfo> regist(@RequestBody @Validated UserRegistVo data) {
        boolean isSmsCodeRight = smsCodeCheck(data.getSmsCode());
        if(!isSmsCodeRight){
            return Response.createErr(null);
        }
        UserInfo res = userService.insert(data.buildUserInfo());
        return Response.createSuc(res);
    }

    private boolean smsCodeCheck(String smsCode) {
        return true;
    }

}
