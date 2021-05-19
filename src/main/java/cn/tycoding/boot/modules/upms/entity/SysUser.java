package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(User)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:32:26
 */
@Data
@TableName("sys_user")
@ApiModel(value = "用户表实体")
@Accessors(chain = true)
public class SysUser implements Serializable {
    private static final long serialVersionUID = -94827981963832107L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String phone;

    /**
     * 状态 0锁定 1有效
     */
    @ApiModelProperty(value = "状态 0锁定 1有效")
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
