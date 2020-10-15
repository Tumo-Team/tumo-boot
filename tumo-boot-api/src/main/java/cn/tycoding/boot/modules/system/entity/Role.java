package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(Role)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:22
 */
@Data
@TableName("sys_role")
@ApiModel(value = "角色表实体")
public class Role implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}
