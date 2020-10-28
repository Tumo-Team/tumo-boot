package cn.tycoding.boot.modules.system.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.ApiConstant;
import cn.tycoding.boot.common.controller.BaseController;
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
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/role")
@Api(value = "角色表接口", tags = "角色表接口")
public class RoleController extends BaseController {

    private final RoleService roleService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Role>> list(@RequestBody Role role) {
        return new R<>(roleService.list(role));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色Tree")
    public R<List<Tree<Object>>> tree() {
        return new R<>(roleService.tree());
    }

    @GetMapping("/base/tree")
    @ApiOperation(value = "获取基础数据", notes = "此接口将获取角色表中id、name、ids等基础数据")
    public R<Map<String, Object>> baseTree() {
        return new R<>(roleService.baseTree());
    }

    @GetMapping("/permission/list/{id}")
    @ApiOperation(value = "根据角色ID查询权限")
    public R<List<Long>> menuList(@PathVariable Long id) {
        return new R<>(roleService.menuList(id));
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param role id:当前修改对象的ID
     *             name:需要校验的名称
     * @return true 当前名称可以用 false 当前名称已存在
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
    public R add(@RequestBody Role role) {
        roleService.add(role);
        return new R();
    }

    @PostMapping("/permission/add/{id}")
    @ApiOperation(value = "分配权限")
    public R addPermission(@RequestBody List<Long> permissionList, @PathVariable Long id) {
        roleService.addPermission(permissionList, id);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Role role) {
        roleService.update(role);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        roleService.delete(id);
        return new R();
    }
}
