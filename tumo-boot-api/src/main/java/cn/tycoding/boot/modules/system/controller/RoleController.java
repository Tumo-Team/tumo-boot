package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.AuthConstant;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.dto.RoleWithMenu;
import cn.tycoding.boot.modules.system.entity.Role;
import cn.tycoding.boot.modules.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色表(Role)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:25
 */
@RestController
@AllArgsConstructor
@RequestMapping(AuthConstant.API_PATH + "/role")
@Api(value = "角色表接口", tags = "角色表接口")
public class RoleController extends BaseController {

    private final RoleService roleService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Role>> list(@RequestBody Role role) {
        return new R<>(roleService.list(role));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Role role, QueryPage queryPage) {
        return new R<>(super.getData(roleService.list(role, queryPage)));
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param role id:当前修改对象的ID
     *             name:需要校验的名称
     * @return Boolean
     */
    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody Role role) {
        return new R<>(roleService.checkName(role));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Role> findById(@PathVariable Long id) {
        return new R<>(roleService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody RoleWithMenu roleWithMenu) {
        roleService.add(roleWithMenu);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody RoleWithMenu roleWithMenu) {
        roleService.update(roleWithMenu);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        roleService.delete(id);
        return new R();
    }
}
