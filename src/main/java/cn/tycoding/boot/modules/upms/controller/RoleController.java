package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        return R.data(roleService.list(role));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色Tree")
    public R<List<Tree<Object>>> tree() {
        return R.data(roleService.tree());
    }

    @GetMapping("/base/tree")
    @ApiOperation(value = "获取基础数据", notes = "此接口将获取角色表中id、name、ids等基础数据")
    public R<Dict> baseTree() {
        return R.data(roleService.baseTree());
    }

    @GetMapping("/permission/list/{id}")
    @ApiOperation(value = "根据角色ID查询权限")
    public R<List<Long>> menuList(@PathVariable Long id) {
        return R.data(roleService.menuList(id));
    }

    @GetMapping("/{id}/user/list")
    @ApiOperation(value = "获取所属用户列表")
    public R<List<User>> userList(@PathVariable Long id) {
        return R.data(roleService.userList(id));
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody Role role) {
        return R.data(roleService.checkName(role));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Role> findById(@PathVariable Long id) {
        return R.data(roleService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Role role) {
        roleService.add(role);
        return R.ok();
    }

    @PostMapping("/permission/add/{id}")
    @ApiOperation(value = "分配权限")
    public R addPermission(@RequestBody List<Long> permissionList, @PathVariable Long id) {
        roleService.addPermission(permissionList, id);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Role role) {
        roleService.update(role);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        roleService.delete(id);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<Role> list = roleService.list();
        ExcelUtil.export(response, "角色数据", "角色数据", Role.class, list);
    }
}
