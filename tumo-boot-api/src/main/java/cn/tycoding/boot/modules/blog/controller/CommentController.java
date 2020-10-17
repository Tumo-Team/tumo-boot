package cn.tycoding.boot.modules.blog.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.AuthConstant;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.blog.entity.Comment;
import cn.tycoding.boot.modules.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 评论表(Comment)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:50:24
 */
@RestController
@AllArgsConstructor
@RequestMapping(AuthConstant.API_PREFIX + "/comment")
@Api(value = "评论表接口", tags = "评论表接口")
public class CommentController extends BaseController {

    private final CommentService commentService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Comment>> list(@RequestBody Comment comment) {
        return new R<>(commentService.list(comment));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Comment comment, QueryPage queryPage) {
        return new R<>(super.getData(commentService.list(comment, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Comment> findById(@PathVariable Long id) {
        return new R<>(commentService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Comment comment) {
        commentService.update(comment);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        commentService.delete(id);
        return new R();
    }
}
