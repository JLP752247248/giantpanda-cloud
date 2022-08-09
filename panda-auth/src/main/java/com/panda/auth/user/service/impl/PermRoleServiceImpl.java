package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.PermRoleMapper;
import com.panda.auth.user.entity.PermRole;
import com.panda.auth.user.service.PermRoleService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermRoleServiceImpl implements PermRoleService {
    @Autowired
    private PermRoleMapper mapper;

    @Override
    public int insert(PermRole data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<PermRole> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(PermRole data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(PermRole data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<PermRole> dataCollection) {
        return mapper.updateBatch(dataCollection);
    }

    @Override
    public int deleteByPrimaryKey(Long permissionId, Long roleId) {
        return mapper.deleteByPrimaryKey(permissionId, roleId);
    }

    @Override
    public PermRole selectByPrimaryKey(Long permissionId, Long roleId) {
        return mapper.selectByPrimaryKey(permissionId, roleId);
    }

    @Override
    public List<PermRole> listSelective(PermRole query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(PermRole data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long permissionId, Long roleId) {
        return mapper.deleteByPrimaryKey(permissionId, roleId);
    }

    @Override
    public int update(PermRole data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public PermRole detail(Long permissionId, Long roleId) {
        return mapper.selectByPrimaryKey(permissionId, roleId);
    }

    @Override
    public List<PermRole> listQuery() {
        return mapper.listQuery();
    }
}