package com.xlhj.baojia.controller;

import com.xlhj.baojia.common.ResultCode;
import com.xlhj.baojia.common.ResultData;
import com.xlhj.baojia.service.SysUserService;
import com.xlhj.baojia.util.JwtTokenUtils;
import com.xlhj.baojia.vo.LoginBody;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 用户登录控制器
 * @Author liucaijing
 * @Date 2020/10/20 22:25
 * @Version 1.0
 */
@RestController
//@CrossOrigin
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService userService;
    @Resource
    private JwtTokenUtils jwtToken;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginBody loginBody) {
        try {
            LoginBody currentUser = userService.validateUser(loginBody);
            if (currentUser != null) {
                String token = jwtToken.generateToken(currentUser);
                return ResultData.ok(ResultCode.SUCCESS, "登录成功!").data("token", token);
            } else {
                return ResultData.error(ResultCode.ERROR, "用户名或密码不正确!");
            }
        } catch (Exception e) {
            return ResultData.error(ResultCode.ERROR, e.getMessage());
        }
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
