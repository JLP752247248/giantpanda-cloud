package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.RoleInfoMapper;
import com.panda.auth.user.entity.RoleInfo;
import com.panda.auth.user.service.RoleInfoService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    private RoleInfoMapper mapper;

    @Override
    public int insert(RoleInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<RoleInfo> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(RoleInfo data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<RoleInfo> dataCollection) {
        return mapper.updateBatch(dataCollection);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIdCollection(Collection<Long> idCollection) {
        return mapper.deleteByIdCollection(idCollection);
    }

    @Override
    public RoleInfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RoleInfo> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<RoleInfo> listSelective(RoleInfo query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(RoleInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(RoleInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public RoleInfo detail(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RoleInfo> listQuery() {
        return mapper.listQuery();
    }
}