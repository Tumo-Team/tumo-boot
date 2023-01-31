package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 上级节点
     */
    private Long parentId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色别名
     */
    private String alias;

    /**
     * 描述
     */
    private String des;

    /**
     * 状态
     */
    private Boolean status;
}
