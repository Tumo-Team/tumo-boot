package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.SysCategory;
import cn.tycoding.boot.modules.system.service.SysCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 分类表(SysCategory)表控制层
 *
 * @author tycoding
 * @since 2020-10-13 11:12:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysCategory")
public class SysCategoryController extends BaseController {

    private final SysCategoryService sysCategoryService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list")
    public R<List<SysCategory>> list(@RequestBody SysCategory sysCategory) {
        return new R<>(sysCategoryService.list(sysCategory));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody SysCategory sysCategory, QueryPage queryPage) {
        return new R<>(super.getData(sysCategoryService.list(sysCategory, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public R<SysCategory> findById(@PathVariable Long id) {
        return new R<>(sysCategoryService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody SysCategory sysCategory) {
        sysCategoryService.add(sysCategory);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody SysCategory sysCategory) {
        sysCategoryService.update(sysCategory);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysCategoryService.delete(id);
        return new R();
    }
}
