package com.panda.fileserver.filemanager.service;

import com.panda.fileserver.filemanager.dto.FileInfo;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 文件服务
 */
@Service
public interface FileManagerService {


    //文件上传服务
    //给文件生成一个uuid，将uuid作为key上传到cos，返回uuid
    String uploadFileToCos(InputStream inputStream, FileInfo fileDesc, boolean isPriv);

    //文件上传服务
    //给文件生成一个uuid，将uuid作为key上传到cos，返回uuid
    String uploadFileToCos(InputStream inputStream, FileInfo fileDesc);

    //文件url查询服务
    //根据传入的uuid查询出文件类型，文件url
    FileInfo getFileInfoFromCos(String fileUuid, boolean isPriv);

    //文件url查询服务
    //根据传入的uuid查询出文件类型，文件url
    FileInfo getFileInfoFromCos(String fileUuid);

    //
    String getFileUrlFromCos(String fileUuid, boolean isPriv);

    String getFileUrlFromCos(String fileUuid);

}
