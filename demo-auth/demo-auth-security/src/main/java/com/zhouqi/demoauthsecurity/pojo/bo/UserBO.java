package com.zhouqi.demoauthsecurity.pojo.bo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhouqi.demoauthsecurity.common.Const;
import com.zhouqi.demoauthsecurity.pojo.entity.Permission;
import com.zhouqi.demoauthsecurity.pojo.entity.Role;
import com.zhouqi.demoauthsecurity.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBO implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Long birthday;
    private Integer sex;
    private Integer status;
    private Long createTime;
    private Long updateTime;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserBO create(User user, List<Role> roleList, List<Permission> permissionList) {
        List<String> roleNameList = roleList.stream().map(Role::getName).toList();
        List<GrantedAuthority> authorityList = permissionList.stream()
                .filter(permission -> StrUtil.isNotEmpty(permission.getPermission()))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        return new UserBO(user.getId(), user.getUsername(), user.getPassword(), user.getNickname(),
                user.getPhone(), user.getEmail(), user.getBirthday(), user.getSex(), user.getStatus(),
                user.getCreateTime(), user.getUpdateTime(), roleNameList, authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, Const.ENABLE);
    }
}
