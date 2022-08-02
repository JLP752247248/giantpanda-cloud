package com.couldtec.webserver.apps.restful.dao;

import com.couldtec.webserver.apps.restful.h2entity.MsgInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface MsgInfoDao extends JpaRepository<MsgInfo, String>
{
}