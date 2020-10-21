package com.xlhj.baojia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.baojia.entity.SysUser;
import com.xlhj.baojia.entity.vo.LoginUser;
import com.xlhj.baojia.mapper.SysUserMapper;
import com.xlhj.baojia.service.SysUserService;
import com.xlhj.baojia.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户管理业务层接口实现类
 * @Author liucaijing
 * @Date 2020/10/20 22:04
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginUser.getUsername());
        claims.put("password", loginUser.getPassword());
        JwtTokenUtils.createToken(claims);
        return null;
    }
}
