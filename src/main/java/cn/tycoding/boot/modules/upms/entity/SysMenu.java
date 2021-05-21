package cn.tycoding.boot.modules.upms.entity;

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
 * @since 2021/5/21
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 427935315704878694L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * 菜单类型：如button按钮 menu菜单
     */
    @ApiModelProperty(value = "菜单类型")
    private String type;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    /**
     * 是否外链
     */
    @ApiModelProperty(value = "是否外链")
    private Boolean isExt;

    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否缓存")
    private Boolean isKeepalive;

    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    public void setParentId(Long parentId) {
        this.parentId = parentId == null ? 0 : parentId;
    }
}
