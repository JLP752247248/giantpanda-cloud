package com.panda.auth.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
@Slf4j
public class OAuth2RestController {

    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @GetMapping("/callback/{registrationId}")
    public String oAuthLogin(@PathVariable("registrationId") String registrationId) {
        return "index";
    }

}
