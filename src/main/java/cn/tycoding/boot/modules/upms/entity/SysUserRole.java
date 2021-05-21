package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户角色关联表(UserRole)实体类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@TableName("sys_user_role")
@Accessors(chain = true)
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -24775118196826771L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}
