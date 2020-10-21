package com.xlhj.baojia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.baojia.entity.SysUser;

/**
 * @ClassName SysUserService
 * @Description 用户管理业务层接口
 * @Author liucaijing
 * @Date 2020/10/20 22:02
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    String login(String username, String password);
}
