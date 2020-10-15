package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表(Menu)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:50
 */
@Data
@TableName("sys_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 427935315704878694L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    /**
     * URL
     */
    @ApiModelProperty(value = "URL")
    private String path;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * 类型：如button按钮 menu菜单
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * Vue组件
     */
    @ApiModelProperty(value = "Vue组件")
    private String component;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    /**
     * 是否是外链
     */
    @ApiModelProperty(value = "是否是外链")
    private Boolean frame;

}
