package com.panda.auth.user.dao;

import com.panda.auth.user.entity.Contract;
import com.panda.auth.user.entity.ContractExample;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContractMapper {
    /**
     * 单条插入
     */
    int insert(@Param("data") Contract data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(@Param("dataCollection") Collection<Contract> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(@Param("data") Contract data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(@Param("data") Contract data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(@Param("dataCollection") Collection<Contract> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(@Param("userId") Long userId);

    /**
     * id集合删除
     */
    int deleteByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 主键查询
     */
    Contract selectByPrimaryKey(@Param("userId") Long userId);

    /**
     * id集合查询
     */
    List<Contract> selectByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<Contract> listSelective(@Param("query") Contract query);

    /**
     * 列表查询
     */
    List<Contract> listQuery();
}