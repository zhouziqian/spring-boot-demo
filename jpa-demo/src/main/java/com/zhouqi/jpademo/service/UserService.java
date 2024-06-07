package com.zhouqi.jpademo.service;

import com.zhouqi.jpademo.pojo.entity.User;

public interface UserService {
    User findById(Long id);
    User save(User user);
}
