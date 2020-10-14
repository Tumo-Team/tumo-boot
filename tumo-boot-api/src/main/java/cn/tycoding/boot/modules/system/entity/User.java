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
 * 用户表(User)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:32:26
 */
@Data
@TableName("sys_user" )
@ApiModel(value = "用户表实体" )
public class User implements Serializable {
    private static final long serialVersionUID = -94827981963832107L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "用户ID" )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名" )
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码" )
    private String password;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID" )
    private Long deptId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private Date createTime;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像" )
    private String avatar;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机" )
    private String phone;

    /**
     * 状态 0锁定 1有效
     */
    @ApiModelProperty(value = "状态 0锁定 1有效" )
    private Boolean status;

}
