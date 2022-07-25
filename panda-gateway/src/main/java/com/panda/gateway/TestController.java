package com.panda.gateway;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable String message) {
        return "Hello Nacos Discovery " + message + " , From port :" + 8081;
    }

    @GetMapping(value = "/echo1")
    public String echo1(String message) {
        return "Hello Nacos Discovery "  + " , From port :" + 8081;
    }

}
