package cn.tycoding.boot.modules.blog.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.ApiConstant;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.blog.entity.Category;
import cn.tycoding.boot.modules.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 分类表(Category)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:49:19
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiConstant.API_BLOG_PREFIX + "/category")
@Api(value = "分类表接口", tags = "分类表接口")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Category>> list(@RequestBody Category category) {
        return new R<>(categoryService.list(category));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Category category, QueryPage queryPage) {
        return new R<>(super.getData(categoryService.list(category, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Category> findById(@PathVariable Long id) {
        return new R<>(categoryService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Category category) {
        categoryService.add(category);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Category category) {
        categoryService.update(category);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new R();
    }
}
