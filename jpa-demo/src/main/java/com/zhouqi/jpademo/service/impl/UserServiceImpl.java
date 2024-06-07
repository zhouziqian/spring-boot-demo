package com.zhouqi.jpademo.service.impl;

import com.zhouqi.jpademo.pojo.entity.User;
import com.zhouqi.jpademo.repository.UserRepository;
import com.zhouqi.jpademo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author zhouqi9
 * date 2024/6/5
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }
}
