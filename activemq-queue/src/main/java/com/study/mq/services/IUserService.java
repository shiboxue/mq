package com.study.mq.services;

import com.study.mq.vo.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserService {
    @WebMethod
    List<User> selectAll();
}
