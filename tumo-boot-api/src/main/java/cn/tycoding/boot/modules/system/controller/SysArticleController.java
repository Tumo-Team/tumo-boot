package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.SysArticle;
import cn.tycoding.boot.modules.system.service.SysArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章表(SysArticle)表控制层
 *
 * @author tycoding
 * @since 2020-10-13 11:11:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysArticle")
public class SysArticleController extends BaseController {

    private final SysArticleService sysArticleService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list")
    public R<List<SysArticle>> list(@RequestBody SysArticle sysArticle) {
        return new R<>(sysArticleService.list(sysArticle));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody SysArticle sysArticle, QueryPage queryPage) {
        return new R<>(super.getData(sysArticleService.list(sysArticle, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public R<SysArticle> findById(@PathVariable Long id) {
        return new R<>(sysArticleService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody SysArticle sysArticle) {
        sysArticleService.add(sysArticle);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody SysArticle sysArticle) {
        sysArticleService.update(sysArticle);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysArticleService.delete(id);
        return new R();
    }
}
