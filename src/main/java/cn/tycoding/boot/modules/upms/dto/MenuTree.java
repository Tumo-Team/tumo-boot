package cn.tycoding.boot.modules.upms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 安装前端路由格式封装
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTree<T> implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 节点ID
     */
    @ApiModelProperty(value = "节点ID")
    private Long id;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID")
    private Long parentId;

    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称")
    private String name;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    /**
     * icon && title 信息
     */
    @ApiModelProperty(value = "icon && title 信息")
    private MenuMeta meta;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

    /**
     * 是否外链
     */
    @ApiModelProperty(value = "是否外链")
    private Boolean isExt;

    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否缓存")
    private Boolean keepalive;

    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean show;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<MenuTree<T>> children = new ArrayList<>();
}
