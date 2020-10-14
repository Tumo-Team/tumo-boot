package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.Menu;
import cn.tycoding.boot.modules.system.service.MenuService;
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
@RequestMapping("/menu" )
public class MenuController extends BaseController {

    private final MenuService menuService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list" )
    public R<List<Menu>> list(@RequestBody Menu menu) {
        return new R<>(menuService.list(menu));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list" )
    public R<Map<String, Object>> list(@RequestBody Menu menu, QueryPage queryPage) {
        return new R<>(super.getData(menuService.list(menu, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}" )
    public R<Menu> findById(@PathVariable Long id) {
        return new R<>(menuService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody Menu menu) {
        menuService.add(menu);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody Menu menu) {
        menuService.update(menu);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable Long id) {
        menuService.delete(id);
        return new R();
    }
}
