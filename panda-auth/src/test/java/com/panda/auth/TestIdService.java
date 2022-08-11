package com.panda.auth;

import com.panda.auth.service.IdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  09:40
 * @Description: TODO
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIdService {
    @Autowired
    private IdService idService;

    @Test
    public void testGetId(){

        long nextKey = idService.getNextId("user_key");
        System.out.println(nextKey);
    }

    @Test
    public void testGetIdConcur(){
        AtomicInteger allTimes = new AtomicInteger();

        String key = "user_key";
        long nextKey = idService.getNextId("user_key");
        System.out.println(nextKey);

        int tCound =4;
        int times=1000;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<times;i++){
                    idService.getNextIdBatch(key, 1);
                    allTimes.getAndIncrement();
                    System.out.println(allTimes.get());
                }
            }
        };

        for(int i = 0;i<tCound;i++){
            Thread t =new Thread(r);
            t.start();
        }
    }
}
