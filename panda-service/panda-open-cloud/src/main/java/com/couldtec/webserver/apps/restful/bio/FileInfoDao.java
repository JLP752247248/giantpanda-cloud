package com.couldtec.webserver.apps.restful.bio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.couldtec.webserver.apps.restful.h2entity.FileInfo;

/**
 * 接口转换记录
 * @author jlp
 *
 */
public interface FileInfoDao extends JpaRepository<FileInfo,String>, JpaSpecificationExecutor<FileInfo>{
}
