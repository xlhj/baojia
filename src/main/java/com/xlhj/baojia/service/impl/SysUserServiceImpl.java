package com.xlhj.baojia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.baojia.entity.SysUser;
import com.xlhj.baojia.mapper.SysUserMapper;
import com.xlhj.baojia.service.SysUserService;
import com.xlhj.baojia.util.JwtTokenUtils;
import com.xlhj.baojia.vo.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

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

    private Map<String, LoginBody> users = new HashMap<>();
    {
        LoginBody user = new LoginBody();
        user.setUsername("admin");
        user.setPassword("123456");
        users.put(user.getUsername(), user);
    }

    @Override
    public LoginBody validateUser(LoginBody loginBody) {
        LoginBody user = null;
        user = this.users.get(loginBody.getUsername());
        if (user != null) {
            if (!user.getPassword().equals(loginBody.getPassword())) {
                user = null;
            }
        }
        return user;
    }

    /*@Autowired
    private SysUserMapper userMapper;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    /*@Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = JwtTokenUtils.createToken(loginUser);
        return token;
    }*/

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(wrapper);
        return user;
    }

    /*public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }*/


}
