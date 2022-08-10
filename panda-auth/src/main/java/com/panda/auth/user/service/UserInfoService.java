package com.panda.auth.user.service;

import com.panda.auth.user.entity.UserInfo;
import java.util.Collection;
import java.util.List;

public interface UserInfoService {

    /**
     * 单条插入
     */
    UserInfo insert(UserInfo data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<UserInfo> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(UserInfo data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(UserInfo data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<UserInfo> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long id);

    /**
     * id集合删除
     */
    int deleteByIdCollection(Collection<Long> idCollection);

    /**
     * 主键查询
     */
    UserInfo selectByPrimaryKey(Long id);

    /**
     * id集合查询
     */
    List<UserInfo> selectByIdCollection(Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<UserInfo> listSelective(UserInfo query);

}