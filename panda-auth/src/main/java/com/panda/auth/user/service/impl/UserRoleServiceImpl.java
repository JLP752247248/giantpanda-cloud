package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.UserRoleMapper;
import com.panda.auth.user.entity.UserRole;
import com.panda.auth.user.service.UserRoleService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper mapper;

    @Override
    public int insert(UserRole data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<UserRole> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(UserRole data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRole data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<UserRole> dataCollection) {
        return mapper.updateBatch(dataCollection);
    }

    @Override
    public int deleteByPrimaryKey(Long userId, Long roleId) {
        return mapper.deleteByPrimaryKey(userId, roleId);
    }

    @Override
    public UserRole selectByPrimaryKey(Long userId, Long roleId) {
        return mapper.selectByPrimaryKey(userId, roleId);
    }

    @Override
    public List<UserRole> listSelective(UserRole query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(UserRole data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long userId, Long roleId) {
        return mapper.deleteByPrimaryKey(userId, roleId);
    }

    @Override
    public int update(UserRole data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public UserRole detail(Long userId, Long roleId) {
        return mapper.selectByPrimaryKey(userId, roleId);
    }

    @Override
    public List<UserRole> listQuery() {
        return mapper.listQuery();
    }
}