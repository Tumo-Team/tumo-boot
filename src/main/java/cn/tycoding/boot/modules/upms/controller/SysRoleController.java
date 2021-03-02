package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/role")
@Api(value = "角色表接口", tags = "角色表接口")
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysRole>> list(@RequestBody SysRole sysRole) {
        return R.data(sysRoleService.list(sysRole));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色Tree")
    public R<List<Tree<Object>>> tree() {
        return R.data(sysRoleService.tree());
    }

    @GetMapping("/base/tree")
    @ApiOperation(value = "获取基础数据", notes = "此接口将获取角色表中id、name、ids等基础数据")
    public R<Dict> baseTree() {
        return R.data(sysRoleService.baseTree());
    }

    @GetMapping("/menu/list/{id}")
    @ApiOperation(value = "根据角色ID查询权限")
    public R getMenuListByRoleId(@PathVariable Long id) {
        List<SysMenu> sysMenuList = sysRoleService.getMenuListByRoleId(id);
        return R.data(sysMenuList.stream().map(SysMenu::getId).collect(Collectors.toList()));
    }

    @GetMapping("/{id}/user/list")
    @ApiOperation(value = "获取所属用户列表")
    public R<List<SysUser>> userList(@PathVariable Long id) {
        return R.data(sysRoleService.userList(id));
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody SysRole sysRole) {
        return R.data(sysRoleService.checkName(sysRole));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysRole> findById(@PathVariable Long id) {
        return R.data(sysRoleService.getById(id));
    }

    @PostMapping
    @ApiLog("新增角色")
    @ApiOperation(value = "新增")
    public R add(@RequestBody SysRole sysRole) {
        sysRoleService.add(sysRole);
        return R.ok();
    }

    @PostMapping("/permission/add/{id}")
    @ApiLog("为角色分配权限")
    @ApiOperation(value = "分配权限")
    public R addPermission(@RequestBody List<Long> permissionList, @PathVariable Long id) {
        sysRoleService.addPermission(permissionList, id);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改角色")
    @ApiOperation(value = "修改")
    public R update(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除角色")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<SysRole> list = sysRoleService.list();
        ExcelUtil.export(response, "角色数据", "角色数据", SysRole.class, list);
    }
}
