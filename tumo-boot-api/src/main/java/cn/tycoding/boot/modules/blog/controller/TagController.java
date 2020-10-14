package cn.tycoding.boot.modules.blog.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.blog.entity.Tag;
import cn.tycoding.boot.modules.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 标签表(Tag)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:50:49
 */
@RestController
@AllArgsConstructor
@Api(value = "标签表接口", tags = "标签表接口" )
@RequestMapping("/tag" )
public class TagController extends BaseController {

    private final TagService tagService;

    @PostMapping("/filter/list" )
    @ApiOperation(value = "条件查询" )
    public R<List<Tag>> list(@RequestBody Tag tag) {
        return new R<>(tagService.list(tag));
    }

    @PostMapping("/list" )
    @ApiOperation(value = "分页、条件查询" )
    public R<Map<String, Object>> list(@RequestBody Tag tag, QueryPage queryPage) {
        return new R<>(super.getData(tagService.list(tag, queryPage)));
    }

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据ID查询" )
    public R<Tag> findById(@PathVariable Long id) {
        return new R<>(tagService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增" )
    public R add(@RequestBody Tag tag) {
        tagService.add(tag);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改" )
    public R update(@RequestBody Tag tag) {
        tagService.update(tag);
        return new R();
    }

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "根据ID删除" )
    public R delete(@PathVariable Long id) {
        tagService.delete(id);
        return new R();
    }
}
