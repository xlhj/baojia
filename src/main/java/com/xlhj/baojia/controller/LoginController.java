package com.xlhj.baojia.controller;

import com.xlhj.baojia.common.ResultData;
import com.xlhj.baojia.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Description 用户登录控制器
 * @Author liucaijing
 * @Date 2020/10/20 22:25
 * @Version 1.0
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private SysUserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResultData login(@PathVariable String username, @PathVariable String password) {
        String token = userService.login(username, password);
        return ResultData.ok().data("token", token);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public ResultData getInfo() {
        return ResultData.ok();
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public ResultData logout() {
        return ResultData.ok();
    }
}
