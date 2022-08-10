package com.panda.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Author: JLP
 * @CreateTime: 2022-08-01  11:19
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.panda.auth.*.dao")
public class AuthApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplicationMain.class, args);
    }
}

