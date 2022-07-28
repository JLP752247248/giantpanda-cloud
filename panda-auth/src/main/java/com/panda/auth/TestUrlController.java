package com.panda.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUrlController {

    @GetMapping("/getsys")
    public String getSysInfo(){
        return "sysInfo";
    }
}
