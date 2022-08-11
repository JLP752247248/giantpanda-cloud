package com.panda.auth.user.controller;

import com.panda.auth.service.IdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "")
public class TestController {


    @Autowired
    private IdService idService;

    @GetMapping(value = "/test")
    @ApiOperation(value = "test", notes = "")
    public int test() {
        AtomicInteger allTimes = new AtomicInteger();
        String key = "user_key";
        int tCound = 4000;
        int times = 100;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < times; i++) {
                    idService.getNextIdBatch(key, 5);
                    allTimes.getAndIncrement();
                    System.out.println(allTimes.get());
                }
            }
        };

        for (int i = 0; i < tCound; i++) {
            Thread t = new Thread(r);
            t.start();
        }
        return allTimes.get();
    }
}