package com.xlhj.baojia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.baojia.entity.SysMenu;

import java.util.Set;

/**
 * @ClassName SysMenuService
 * @Description 菜单权限管理业务层接口
 * @Author liucaijing
 * @Date 2020/10/20 22:03
 * @Version 1.0
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询菜单权限
     * @param id
     * @return
     */
    Set<String> selectPermsByUserId(Long id);
}
