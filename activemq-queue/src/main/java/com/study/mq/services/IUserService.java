package com.study.mq.services;

import com.study.mq.vo.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * WebService 暴露接口配置注解
 */
@WebService
public interface IUserService {
    @WebMethod
    List<User> selectAll();
}
