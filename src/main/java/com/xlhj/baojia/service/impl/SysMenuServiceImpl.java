package com.xlhj.baojia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.baojia.entity.SysMenu;
import com.xlhj.baojia.mapper.SysMenuMapper;
import com.xlhj.baojia.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysMenuServiceImpl
 * @Description 菜单权限管理业务层接口实现类
 * @Author liucaijing
 * @Date 2020/10/20 22:06
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
}
