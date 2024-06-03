package com.zhouqi.demoauthsecurity.pojo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@Data
@Entity
@Table(name = "sec_role")
public class Role {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
