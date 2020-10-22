package com.xlhj.baojia.auth;

import com.xlhj.baojia.entity.SysUser;
import com.xlhj.baojia.entity.vo.LoginUser;
import com.xlhj.baojia.service.SysMenuService;
import com.xlhj.baojia.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/22 11:47
 * @Description: 用户验证处理
 * @Version: 0.0.1
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysMenuService menuService;

    /**
     * 验证用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (null == user) {
            logger.info("用户：{}不存在!", username);
        }
        if (user.getStatus() == 20) {
            logger.info("用户：{}已锁定!", username);
        } else if (user.getStatus() == 30) {
            logger.info("用户：{}已注销!", username);
        }
        //获取菜单权限
        Set<String> perms = menuService.selectPermsByUserId(user.getId());
        return new LoginUser(user, perms);
    }
}
