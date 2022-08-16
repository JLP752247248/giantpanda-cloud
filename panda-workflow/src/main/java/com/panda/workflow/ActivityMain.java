package com.panda.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-16  15:38
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ActivityMain {
    public static void main(String[] args) {
        SpringApplication.run(ActivityMain.class, args);
    }

}
