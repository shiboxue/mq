package com.study.mq.dao;

import com.study.mq.vo.User;
import java.util.List;

public interface UserMapper {
    List<User> getAll();
    User getOne(Long id);
    void insert(User user);
    void update(User user);
    void delete(Long id);
}
