package com.panda.fileserver.filemanager.service;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-03  16:26
 * @Description: COS服务
 * @Version: 1.0
 */

import com.panda.fileserver.filemanager.config.cos.CosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * COS对接服务
 */
@Service
public class CosObjectService {

    @Autowired
    private CosConfig cosConfig;

    /**
     * 获取cos客户端
     *
     * @return COSClient
     */
    private COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegionName()));
        // 初始化用户身份信息(secretId, secretKey)
        // 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }

    /**
     * 获取cos客户端
     *
     * @return COSClient
     */
    private COSClient getCosClientTest() {
        COSCredentials cred = new BasicCOSCredentials("AKIDHQLI3at0tZ1IMCpFahcJl13sTr30lX5Q", "BPaZKq7C2kFWYMZbQ2RfhWAew20qUxDH");
        ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));
        // 初始化用户身份信息(secretId, secretKey)
        // 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }


    /**
     * 上传文件到
     *
     * @param file
     * @param aimPath
     */
    public void putFileToCos(File file, String aimPath) {
        Assert.isTrue(Objects.nonNull(file), "file 不可以为空！！！");
        Assert.isTrue(StringUtils.isNotEmpty(aimPath), "aimPath 不可以为空！！！");
        COSClient cosClient = getCosClient();
        String bucketName = cosConfig.getPublicBucketName();
        String key = aimPath + file.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(putObjectResult.getRequestId());
    }

    /**
     * 文件上传Cos
     *
     * @param inputStream
     * @param aimPath
     */
    public void putFileToCos(InputStream inputStream, String aimPath, boolean isPrivate) {
        Assert.isTrue(Objects.nonNull(inputStream), "inputStream 不可以为空！！！");
        Assert.isTrue(StringUtils.isNotEmpty(aimPath), "aimPath 不可以为空！！！");
        COSClient cosClient = getCosClient();
        String bucketName = isPrivate?cosConfig.getPrivateBucketName():cosConfig.getPublicBucketName();
        String key = aimPath;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, null);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
    }


    /**
     * @param key
     * @return
     * @throws IOException
     */
    @Deprecated
    public InputStream getObjectFromCos(String key) throws IOException {
        Assert.isTrue(StringUtils.isNotEmpty(key), "key 不可以为空！！！");
        COSClient cosclient = getCosClient();
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = cosConfig.getPublicBucketName();
        // 方法1 获取下载输入流
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        // 限流使用的单位是 bit/s, 这里设置下载带宽限制为10MB/s
        getObjectRequest.setTrafficLimit(80 * 1024 * 1024);
        COSObject cosObject = cosclient.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
        return cosObjectInput;
    }

    /**
     * 获取私有桶文件临时签名url
     *
     * @param key
     * @param seconds
     * @return
     */
    public String getPreSignedUrl(String key, Long seconds) {
        COSClient cosclient = getCosClientTest();
        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<>();
        //params.put("param1", "value1");

        // 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<>();
        //headers.put("header1", "value1");
        URL url = cosclient.generatePresignedUrl(cosConfig.getPrivateBucketName(), key, new Date(System.currentTimeMillis() + seconds * 1000), HttpMethodName.GET, headers, params);
        return url.toString();
    }

    /**
     * 获取公有桶文件url
     * @param key
     * @return
     */
    public String getPublicUrl(String key) {
        String bucketName = cosConfig.getPublicBucketName();
        COSClient cosclient = getCosClient();
        return cosclient.getObjectUrl(bucketName, key).toString();
    }


    public static void main(String[] args) {
        CosObjectService service = new CosObjectService();
        service.getCosClientTest();
    }
}
