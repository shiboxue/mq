package com.study.mq.services.impl;

import com.study.mq.dao.UserMapper;
import com.study.mq.services.IUserService;
import com.study.mq.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import java.util.List;

/**
 * @className UserServiceImpl
 * @Description TODO
 * @Auhtor shiboxue
 * @Date 2019/9/12 9:29
 * @Version 1.0
 **/
@WebService(targetNamespace="http://services.mq.study.com/",endpointInterface="com.study.mq.services.IUserService")
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.getAll();
    }
}
