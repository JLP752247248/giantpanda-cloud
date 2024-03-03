package com.panda.olap.controller;


import com.panda.olap.serivce.ServiceATFeign;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TestController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(value = "/echo/app/name")
    public String echo() {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("business");
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/echo1")
    public String echo1() {
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xxxxxxxxxxxxxxxxxxxxxxx";
    }
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private ServiceATFeign atFeign;


    @GlobalTransactional(timeoutMills = 60000 * 2)
    @GetMapping("/at-insert")
    public String insert() {
        log.info("------------------> xid = " + RootContext.getXID());
        template.update("update sys_role set name = 3 where id =3 ");
        atFeign.insertAT();
        //强行抛出异常回滚
        //throw new RuntimeException("AT服务测试回滚");
        return "success";
    }
}
