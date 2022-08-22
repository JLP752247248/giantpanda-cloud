package com.panda.service.business.controller.test;


import com.panda.common.mvc.Response;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RefreshScope
@Slf4j
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

    @Autowired
    private JdbcTemplate template;

    /**
     * 请求本地事务插入一条记录
     * 再请求at服务插入一条记录
     *
     * @param params - name
     * @return String
     */
    @GlobalTransactional(timeoutMills = 5000)
    @Transactional(rollbackFor = Exception.class ,propagation = Propagation.REQUIRED)

    @GetMapping("/insert-at")
    public String insertAT(Map<String, String> params) {
        log.info("------------------> xid = " + RootContext.getXID());
        //本地事务插入一条数据
        template.update("update sys_user set username = id ");
        //远程at事务插入一条数据


        return "success";
    }
}
