package com.panda.auth.user.service;

import com.panda.auth.user.entity.Contract;
import java.util.Collection;
import java.util.List;

public interface ContractService {
    /**
     * 单条插入
     */
    int insert(Contract data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(Collection<Contract> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(Contract data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(Contract data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(Collection<Contract> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * id集合删除
     */
    int deleteByIdCollection(Collection<Long> idCollection);

    /**
     * 主键查询
     */
    Contract selectByPrimaryKey(Long userId);

    /**
     * id集合查询
     */
    List<Contract> selectByIdCollection(Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<Contract> listSelective(Contract query);

    /**
     * 新增
     */
    int add(Contract data);

    /**
     * 删除
     */
    int delete(Long userId);

    /**
     * 修改
     */
    int update(Contract data);

    /**
     * 详情
     */
    Contract detail(Long userId);

    /**
     * 列表查询
     */
    List<Contract> listQuery();
}