package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单表(Menu)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/menu")
@Api(value = "菜单表接口", tags = "菜单表接口")
public class SysMenuController extends BaseController {

    private final SysMenuService sysMenuService;

    @GetMapping("/tree")
    @ApiOperation(value = "构建菜单Tree树", notes = "此接口将获取菜单表中所有数据")
    public R<List<MenuTree<SysMenu>>> tree() {
        return R.ok(sysMenuService.tree());
    }

    @GetMapping("/base/tree")
    @ApiOperation(value = "获取基础数据", notes = "此接口将获取菜单表中id、name、ids等基础数据")
    public R<Dict> baseTree() {
        return R.ok(sysMenuService.baseTree());
    }

    @GetMapping("/build")
    @ApiOperation(value = "加载系统左侧权限菜单", notes = "此接口将获取菜单中`menu`类型的数据")
    public R<List<MenuTree<SysMenu>>> build() {
        return R.ok(sysMenuService.build());
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody SysMenu sysMenu) {
        return R.ok(sysMenuService.checkName(sysMenu));
    }

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysMenu>> list(@RequestBody SysMenu sysMenu) {
        return R.ok(sysMenuService.list(sysMenu));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysMenu> findById(@PathVariable Long id) {
        return R.ok(sysMenuService.getById(id));
    }

    @PostMapping
    @ApiLog("新增菜单")
    @ApiOperation(value = "新增")
    public R add(@RequestBody SysMenu sysMenu) {
        sysMenuService.add(sysMenu);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改菜单")
    @ApiOperation(value = "修改")
    public R update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除菜单")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<SysMenu> list = sysMenuService.list();
        ExcelUtil.export(response, "菜单数据", "用户数据", SysMenu.class, list);
    }
}
