package com.couldtec.webserver.apps.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.couldtec.webserver.apps.restful.h2entity.BssUserInfo;
import com.couldtec.webserver.apps.restful.h2entity.TransMitLog;

/**
 * 接口转换记录
 * @author jlp
 *
 */
public interface BssInfoDao extends JpaRepository<BssUserInfo,String>, JpaSpecificationExecutor<BssUserInfo>{
	
	
}
