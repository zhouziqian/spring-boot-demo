package com.zhouqi.jpademo.user;

import cn.hutool.json.JSONUtil;
import com.zhouqi.jpademo.contoller.UserController;
import com.zhouqi.jpademo.pojo.entity.User;
import com.zhouqi.jpademo.repository.UserRepository;
import com.zhouqi.jpademo.service.UserService;
import com.zhouqi.jpademo.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhouqi9
 * date 2024/6/5
 */
@Slf4j
@AutoConfigureDataJpa
@WebMvcTest(UserController.class)
public class UserTest {
    @Resource
    private MockMvc mockMvc;
    @SpyBean
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(2L);
        user.setName("zhouqi");
        user.setPassword("password");
        user.setPhone("123456789");
        user.setEmail("zhouqi@qq.com");
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(user)))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
    }
}
