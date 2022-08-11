package com.panda.auth;

import com.panda.auth.user.entity.UserInfo;
import com.panda.auth.user.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-10  10:02
 * @Description: TODO
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testGetId(){
        UserInfo user =new UserInfo();
        user.setUsername("xx");
        user.setPassword("343fdagdfsgfd");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userInfoService.insert(user);




    }
}
