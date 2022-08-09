package com.panda.auth.user.service.impl;

import com.panda.auth.user.dao.UserInfoMapper;
import com.panda.auth.user.entity.UserInfo;
import com.panda.auth.user.service.UserInfoService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper mapper;

    @Override
    public int insert(UserInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int insertBatch(Collection<UserInfo> dataCollection) {
        return mapper.insertBatch(dataCollection);
    }

    @Override
    public int updateByPrimaryKey(UserInfo data) {
        return mapper.updateByPrimaryKey(data);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int updateBatch(Collection<UserInfo> dataCollection) {
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
    public UserInfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> selectByIdCollection(Collection<Long> idCollection) {
        return mapper.selectByIdCollection(idCollection);
    }

    @Override
    public List<UserInfo> listSelective(UserInfo query) {
        return mapper.listSelective(query);
    }

    @Override
    public int add(UserInfo data) {
        return mapper.insert(data);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(UserInfo data) {
        return mapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public UserInfo detail(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> listQuery() {
        return mapper.listQuery();
    }
}