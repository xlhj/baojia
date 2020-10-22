package com.xlhj.baojia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.baojia.entity.SysMenu;

import java.util.List;

/**
 * @ClassName SysMenuMapper
 * @Description 菜单权限管理持久层接口
 * @Author liucaijing
 * @Date 2020/10/20 22:01
 * @Version 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(Long userId);
}
