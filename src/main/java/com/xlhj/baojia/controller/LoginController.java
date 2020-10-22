package com.xlhj.baojia.controller;

import com.xlhj.baojia.common.ResultData;
import com.xlhj.baojia.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService userService;

    /**
     * 登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ResultData login(@RequestBody Map<String, Object> map) {
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        String token = userService.login(username, password);
        return ResultData.ok().data("token", token);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("info")
    @ResponseBody
    public ResultData getInfo() {
        return ResultData.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    @ResponseBody
    public ResultData logout() {
        return ResultData.ok();
    }
}
