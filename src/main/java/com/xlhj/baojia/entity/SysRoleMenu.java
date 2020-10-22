package com.xlhj.baojia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SysRoleMenu
 * @Description 角色管理实体类
 * @Author liucaijing
 * @Date 2020/10/20 21:59
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleMenu对象", description="角色菜单表")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -6150356706799858484L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;
}
