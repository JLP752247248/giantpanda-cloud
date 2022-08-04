package com.panda.fileserver.filemanager.config.cos;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-04  09:30
 * @Description: 腾讯云COS相关配置
 * @Version: 1.0
 */
@Getter
@Component
public class CosConfig {


    @Value("${tecent.cos.accessKey}")
    private String accessKey;
    @Value("${tecent.cos.secretKey}")
    private String secretKey;
    @Value("${tecent.cos.regionName}")
    private String regionName;
    @Value("${tecent.cos.bucketName.public}")
    private String publicBucketName;
    @Value("${tecent.cos.bucketName.private}")
    private String privateBucketName;



}
