package com.panda.fileserver.filamanager.service;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-03  16:26
 * @Description: TODO
 * @Version: 1.0
 */
import java.io.File;
import java.io.IOException;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;

public class PutObject {
    static  COSCredentials cred = new BasicCOSCredentials("AKIDHQLI3at0tZ1IMCpFahcJl13sTr30lX5Q","BPaZKq7C2kFWYMZbQ2RfhWAew20qUxDH");
    // 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
    static ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));


    public static void main(String[] args) {
        putObjectDemo();
    }

    static void putObjectDemo() {
        // 初始化用户身份信息(secretId, secretKey)

        // 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        String bucketName = "panda-fileservice-1301703973";
        String key = "abc/abc.txt";
        String localPath = "F://xxx.txt";

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new File(localPath));
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        System.out.println(putObjectResult.getRequestId());
    }

    static void getObjectDemo() throws IOException {
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = "examplebucket-1250000000";
        String key = "exampleobject";
// 方法1 获取下载输入流
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
// 限流使用的单位是 bit/s, 这里设置下载带宽限制为10MB/s
        getObjectRequest.setTrafficLimit(80*1024*1024);
        COSClient cosclient = new COSClient(cred, clientConfig);
        COSObject cosObject = cosclient.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
// 下载对象的 CRC64
        String crc64Ecma = cosObject.getObjectMetadata().getCrc64Ecma();
// 关闭输入流
        cosObjectInput.close();

// 方法2 下载文件到本地
        String outputFilePath = "exampleobject";
        File downFile = new File(outputFilePath);
        getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosclient.getObject(getObjectRequest, downFile);
    }
}
