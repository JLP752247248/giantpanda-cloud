package com.panda.service.business.controller.test;


import com.panda.common.mvc.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
public class TestController {

    @Value("${server.port}")
    private String port;

    @Value("${test.conf}")
    private String testconf;


    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable String message) {
        return "Hello Nacos Discovery " + message + " , From port :" + port;
    }

    @GetMapping(value = "/echo1")
    public String echo1(String message) {
        return "Hello Nacos Discovery " + " , From port :" + 8081;
    }

    @GetMapping(value = "/echo2")
    public Response<String> echo2(String message) {
        return Response.createSuc("200");
    }

    @RequestMapping(value = "/t1", method = RequestMethod.GET)
    public Response<String> testmap() throws InterruptedException {
        Thread.sleep(1000);


        return Response.createSuc(testconf);
    }
}
