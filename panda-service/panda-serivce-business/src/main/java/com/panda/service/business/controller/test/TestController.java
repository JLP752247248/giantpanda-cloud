package com.panda.service.business.controller.test;


import com.panda.common.mvc.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/panda/api/test")
public class TestController {


    @RequestMapping(value = "/t1", method = RequestMethod.GET)
    @ResponseBody
    public Response<String> testmap(){
        return Response.createSuc("100");
    }
}
