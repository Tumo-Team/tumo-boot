package cn.tycoding.boot.modules.upms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装前端路由所需的路径名称、图标格式
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@AllArgsConstructor
public class MenuMeta implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 图标名称
     */
    @ApiModelProperty(value = "图标名称")
    private String icon;
}
