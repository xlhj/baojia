package com.xlhj.baojia.entity.vo;

import com.xlhj.baojia.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/22 9:33
 * @Description: 用户登录身份信息
 * @Version: 0.0.1
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**用户唯一标识*/
    private String token;

    /**登录时间*/
    private Long loginTime;

    /**过期时间*/
    private Long expireTime;

    /**权限列表*/
    private Set<String> permissions;

    /**用户信息*/
    private SysUser user;

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
        return true;
    }
}
