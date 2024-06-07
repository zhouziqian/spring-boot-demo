package com.zhouqi.jpademo.repository;

import com.zhouqi.jpademo.pojo.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * @author zhouqi9
 * date 2024/6/5
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 当查询没有产生结果时，抛出一个 EmptyResultDataAccessException
    // 当入参为 null 时，抛出一个 IllegalArgumentException
    @NonNull
    User findByPhone(@NonNull String phone);

    // 支持查询到null
    // 支持传入参数为null
    @Nullable
    User findByEmail(@Nullable String email);

}
