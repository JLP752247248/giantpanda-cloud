package com.panda.auth.service;

import com.panda.auth.user.dao.AddrMapper;
import com.panda.auth.user.dao.ContractMapper;
import com.panda.auth.user.dao.RoleInfoMapper;
import com.panda.auth.user.dao.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserInfoMapper userRepository;

    @Autowired
    private ContractMapper contactRepository;

    @Autowired
    private AddrMapper addressRepository;

    @Autowired
    private RoleInfoMapper roleRepository;

    @Value("${microservice.security.salt:'1234567890'}")
    private String salt;


}
