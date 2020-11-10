package cn.tycoding.boot.modules.blog.controller;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章表(Article)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:48:05
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiConstant.API_BLOG_PREFIX + "/article")
@Api(value = "文章表接口", tags = "文章表接口")
public class ArticleController extends BaseController {

    private final ArticleService articleService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Article>> list(@RequestBody Article article) {
        return new R<>(articleService.list(article));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Article article, QueryPage queryPage) {
        return new R<>(super.getData(articleService.list(article, queryPage)));
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param article id:当前修改对象的ID
     *                name:需要校验的名称
     * @return true 当前名称可以用 false 当前名称已存在
     */
    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody Article article) {
        return new R<>(articleService.checkName(article));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Article> findById(@PathVariable Long id) {
        return new R<>(articleService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Article article) {
        articleService.add(article);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Article article) {
        articleService.update(article);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        articleService.delete(id);
        return new R();
    }
}
