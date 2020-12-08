package cn.tycoding.boot.modules.system.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.modules.system.dto.MenuTree;
import cn.tycoding.boot.modules.system.entity.Menu;
import cn.tycoding.boot.modules.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单表(Menu)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:53
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/menu")
@Api(value = "菜单表接口", tags = "菜单表接口")
public class MenuController extends BaseController {

    private final MenuService menuService;

    @GetMapping("/tree")
    @ApiOperation(value = "构建菜单Tree树", notes = "此接口将获取菜单表中所有数据")
    public R<List<Tree<Object>>> tree() {
        return new R<>(menuService.tree());
    }

    @GetMapping("/base/tree")
    @ApiOperation(value = "获取基础数据", notes = "此接口将获取菜单表中id、name、ids等基础数据")
    public R<Map<String, Object>> baseTree() {
        return new R<>(menuService.baseTree());
    }

    @GetMapping("/build")
    @ApiOperation(value = "加载系统左侧权限菜单", notes = "此接口将获取菜单中`menu`类型的数据")
    public R<List<MenuTree<Menu>>> build() {
        return new R<>(menuService.build());
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param menu id:当前修改对象的ID
     *             name:需要校验的名称
     * @return true 当前名称可以用 false 当前名称已存在
     */
    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody Menu menu) {
        return new R<>(menuService.checkName(menu));
    }

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Menu>> list(@RequestBody Menu menu) {
        return new R<>(menuService.list(menu));
    }

    @PostMapping("/list")
    @ApiOperation(value = "条件查询")
    public R<Map<String, Object>> list(@RequestBody Menu menu, QueryPage queryPage) {
        return new R<>(super.getData(menuService.list(menu, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Menu> findById(@PathVariable Long id) {
        return new R<>(menuService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Menu menu) {
        menuService.add(menu);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Menu menu) {
        menuService.update(menu);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        menuService.delete(id);
        return new R();
    }
}
