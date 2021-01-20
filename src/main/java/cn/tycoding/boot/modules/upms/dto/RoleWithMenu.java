package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Data
public class RoleWithMenu extends Role {

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    /**
     * 菜单ID集合
     */
    @ApiModelProperty(value = "菜单ID集合")
    private List<Long> menuIds;
}
