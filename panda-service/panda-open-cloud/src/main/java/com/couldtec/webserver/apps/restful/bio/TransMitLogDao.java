package com.couldtec.webserver.apps.restful.bio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.couldtec.webserver.apps.restful.h2entity.TransMitLog;

/**
 * 接口转换记录
 * @author jlp
 *
 */
public interface TransMitLogDao extends JpaRepository<TransMitLog,Long>, JpaSpecificationExecutor<TransMitLog>{
	TransMitLog findByReqId(String reqId);
	TransMitLog findByReqIdAndMethodName(String reqId,String methodName);
}
