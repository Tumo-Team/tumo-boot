package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色资源关联表(RoleMenu)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:06
 */
@Data
@TableName("sys_role_menu" )
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 854296054659457976L;

    private Long roleId;

    private Long menuId;
}
