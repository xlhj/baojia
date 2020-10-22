package com.xlhj.baojia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.baojia.entity.SysMenu;
import com.xlhj.baojia.mapper.SysMenuMapper;
import com.xlhj.baojia.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysMenuServiceImpl
 * @Description 菜单权限管理业务层接口实现类
 * @Author liucaijing
 * @Date 2020/10/20 22:06
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> permList = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        if (permList.size() > 0) {
            for (String perm : permList) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
