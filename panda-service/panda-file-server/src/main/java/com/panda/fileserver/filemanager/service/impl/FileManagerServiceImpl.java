package com.panda.fileserver.filemanager.service.impl;

import com.panda.fileserver.filemanager.dao.FileInfoResponsitory;
import com.panda.fileserver.filemanager.dto.FileInfo;
import com.panda.fileserver.filemanager.entity.FileInfoEntity;
import com.panda.fileserver.filemanager.service.CosObjectService;
import com.panda.fileserver.filemanager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-04  10:32
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class FileManagerServiceImpl implements FileManagerService {

    @Autowired
    private CosObjectService cosObjectService;

    @Autowired
    private FileInfoResponsitory fileInfoResponsitory;

    private String buildKey(String directory, String uuid) {
        String key = directory + "/" + uuid;
        return key;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadFileToCos(InputStream inputStream, FileInfo fileDesc, boolean isPriv) {
        String md5 = fileDesc.getMd5();
        FileInfoEntity existEntity = fileInfoResponsitory.findByMd5(md5);
        if (Objects.nonNull(existEntity)) {
            return existEntity.getFileUuid();
        }
        String uuid = UUID.randomUUID().toString();
        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setFileUuid(uuid);
        fileInfoEntity.setFileName(fileDesc.getName());
        fileInfoEntity.setDirectory(fileDesc.getDirectory());
        fileInfoEntity.setMd5(md5);
        cosObjectService.putFileToCos(inputStream, buildKey(fileDesc.getDirectory(), uuid), isPriv);
        fileInfoResponsitory.save(fileInfoEntity);
        return uuid;
    }

    @Override
    public String uploadFileToCos(InputStream inputStream, FileInfo fileDesc) {
        return uploadFileToCos(inputStream, fileDesc, false);
    }

    @Override
    public FileInfo getFileInfoFromCos(String fileUuid, boolean isPriv) {
        return null;
    }

    @Override
    public FileInfo getFileInfoFromCos(String fileUuid) {
        return null;
    }

    @Override
    public String getFileUrlFromCos(String fileUuid, boolean isPriv) {
        FileInfoEntity fileInfoEntity = fileInfoResponsitory.findByFileUuid(fileUuid);
        Assert.notNull(fileInfoEntity, "fileUuid 未找到对应文件");
        String url = cosObjectService.getPreSignedUrl(fileUuid, 60L);
        return url;
    }

    @Override
    public String getFileUrlFromCos(String fileUuid) {
        FileInfoEntity fileInfoEntity = fileInfoResponsitory.findByFileUuid(fileUuid);
        return cosObjectService.getPublicUrl(buildKey(fileInfoEntity.getDirectory(), fileUuid));
    }
}
