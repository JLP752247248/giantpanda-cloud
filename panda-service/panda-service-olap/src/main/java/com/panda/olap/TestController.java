package com.panda.olap;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.panda.common.mvc.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping(value = "/olap")
public class TestController {


    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallback", groupKey = "Hello",
            commandKey = "hello",
            threadPoolKey = "helloThread"
    )
    @GetMapping(value = "/t1")
    public String hello() {
        String url = "http://panda-service-business/business/panda/api/test/t1";
        return restTemplate.getForObject(url, String.class)+ " including client";
    }

    public String fallback(Throwable hystrixCommand) {
        return "Fall Back Hello world";
    }
    @RequestMapping(value = "/t2", method = RequestMethod.GET)
    @ResponseBody
    public Response<String> testmap(){
        return Response.createSuc("100");
    }
}
