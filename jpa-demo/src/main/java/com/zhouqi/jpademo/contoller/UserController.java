package com.zhouqi.jpademo.contoller;

import com.zhouqi.jpademo.pojo.entity.User;
import com.zhouqi.jpademo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouqi9
 * date 2024/6/5
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping()
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
}
