package com.panda.fileserver.filemanager.dao;

import com.panda.fileserver.filemanager.entity.FileInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-04  10:42
 * @Description: TODO
 * @Version: 1.0
 */

@Repository
public interface FileInfoResponsitory extends CrudRepository<FileInfoEntity, Long> {

    FileInfoEntity findByFileUuid(String fileUuid);

    FileInfoEntity findByMd5(String md5);
}
