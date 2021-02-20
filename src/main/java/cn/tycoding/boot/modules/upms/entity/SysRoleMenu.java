package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色资源关联表(RoleMenu)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:06
 */
@Data
@TableName("sys_role_menu")
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 854296054659457976L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
}
