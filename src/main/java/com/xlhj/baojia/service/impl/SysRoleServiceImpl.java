package com.xlhj.baojia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.baojia.entity.SysRole;
import com.xlhj.baojia.mapper.SysRoleMapper;
import com.xlhj.baojia.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysRoleServiceImpl
 * @Description 角色管理业务层接口实现类
 * @Author liucaijing
 * @Date 2020/10/20 22:05
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
