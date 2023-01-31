package cn.tycoding.boot.modules.upms.controller;

import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单表(Menu)表控制层
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("/tree")
    public R<List<MenuTree<SysMenu>>> tree(SysMenu sysMenu) {
        return R.ok(sysMenuService.tree(sysMenu));
    }

    @GetMapping("/build")
    public R<List<MenuTree<SysMenu>>> build() {
        return R.ok(sysMenuService.build(AuthUtil.getUserId()));
    }

    @GetMapping("/list")
    public R<List<SysMenu>> list(SysMenu sysMenu) {
        return R.ok(sysMenuService.list(sysMenu));
    }

    @GetMapping("/{id}")
    public R<SysMenu> findById(@PathVariable Long id) {
        return R.ok(sysMenuService.getById(id));
    }

    @PostMapping
    @ApiLog("新增菜单")
    @PreAuthorize("@auth.hasAuth('upms:menu:add')")
    public R add(@RequestBody SysMenu sysMenu) {
        sysMenuService.add(sysMenu);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改菜单")
    @PreAuthorize("@auth.hasAuth('upms:menu:update')")
    public R update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除菜单")
    @PreAuthorize("@auth.hasAuth('upms:menu:delete')")
    public R delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return R.ok();
    }
}
