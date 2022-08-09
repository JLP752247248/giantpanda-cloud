package com.panda.auth.user.dao;

import com.panda.auth.user.entity.PermInfo;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermInfoMapper {
    /**
     * 单条插入
     */
    int insert(@Param("data") PermInfo data);

    /**
     * 批量插入；数据集合为空会报错，使用时请判断是否空
     */
    int insertBatch(@Param("dataCollection") Collection<PermInfo> dataCollection);

    /**
     * 普通修改
     */
    int updateByPrimaryKey(@Param("data") PermInfo data);

    /**
     * 动态修改
     */
    int updateByPrimaryKeySelective(@Param("data") PermInfo data);

    /**
     * 批量修改：数据为空会报错，使用时请判断是否空
     */
    int updateBatch(@Param("dataCollection") Collection<PermInfo> dataCollection);

    /**
     * 主键删除
     */
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * id集合删除
     */
    int deleteByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 主键查询
     */
    PermInfo selectByPrimaryKey(@Param("id") Long id);

    /**
     * id集合查询
     */
    List<PermInfo> selectByIdCollection(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 动态查询
     */
    List<PermInfo> listSelective(@Param("query") PermInfo query);

    /**
     * 列表查询
     */
    List<PermInfo> listQuery();
}