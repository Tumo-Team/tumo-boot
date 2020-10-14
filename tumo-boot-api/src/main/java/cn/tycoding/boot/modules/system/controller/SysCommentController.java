package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.SysComment;
import cn.tycoding.boot.modules.system.service.SysCommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 评论表(SysComment)表控制层
 *
 * @author tycoding
 * @since 2020-10-13 11:13:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysComment")
public class SysCommentController extends BaseController {

    private final SysCommentService sysCommentService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list")
    public R<List<SysComment>> list(@RequestBody SysComment sysComment) {
        return new R<>(sysCommentService.list(sysComment));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody SysComment sysComment, QueryPage queryPage) {
        return new R<>(super.getData(sysCommentService.list(sysComment, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public R<SysComment> findById(@PathVariable Long id) {
        return new R<>(sysCommentService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody SysComment sysComment) {
        sysCommentService.add(sysComment);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody SysComment sysComment) {
        sysCommentService.update(sysComment);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysCommentService.delete(id);
        return new R();
    }
}
