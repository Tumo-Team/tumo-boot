package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户角色关联表(UserRole)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:44:42
 */
@Data
@TableName("sys_user_role" )
@Accessors(chain = true)
public class UserRole implements Serializable {
    private static final long serialVersionUID = -24775118196826771L;

    private Long userId;

    private Long roleId;
}
